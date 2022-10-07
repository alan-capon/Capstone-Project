package reviews.domain;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reviews.App;
import reviews.data.AppUserRepository;
import reviews.models.AppUser;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository repository;
    private final PasswordEncoder encoder;

    private static final int MAX_USERNAME_LENGTH = 255;
    private static final int MIN_PASSWORD_LENGTH = 8;

    public AppUserService(AppUserRepository repository,
                          PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username);
        if (appUser == null || !appUser.isEnabled()) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return appUser;
    }

    public AppUser findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Result<AppUser> create(AppUser appUser) {
        Result<AppUser> result = validate(appUser);

        if(!result.isSuccess()) {
            return result;
        }

        if (appUser.getAppUserId() != 0) {
            result.addErrorMessage("Id cannot be set", ResultType.INVALID);
            return result;
        }
//        validate(appUser.getUsername());
//        validatePassword(appUser.getPassword());

        String password = encoder.encode(appUser.getPassword());
        AppUser newUser = new AppUser(0, appUser.getUsername(), appUser.getFirstName(),
                appUser.getLastName(), appUser.getEmail(), password, false, List.of("User"));


        appUser = repository.create(newUser);
        result.setPayload(appUser);
        return result;
    }
    private Result<AppUser> validate(AppUser appUser) {
        Result<AppUser> result = new Result<>();

        if (appUser == null) {
            result.addErrorMessage("User required", ResultType.INVALID);
            return result;
        }

        if (appUser.getUsername() == null || appUser.getUsername().isBlank()) {
            result.addErrorMessage("Username required", ResultType.INVALID);
        }
        if (appUser.getUsername().length() > MAX_USERNAME_LENGTH) {
            String errorMsg = String.format("Username must be less than %s characters", MAX_USERNAME_LENGTH);
            result.addErrorMessage(errorMsg, ResultType.INVALID);
        }

        if (appUser.getFirstName() == null || appUser.getFirstName().isBlank()) {
            result.addErrorMessage("First name required", ResultType.INVALID);
        }

        if (appUser.getLastName() == null || appUser.getLastName().isBlank()) {
            result.addErrorMessage("Last name required", ResultType.INVALID);
        }

        if (appUser.getEmail() == null || appUser.getEmail().isBlank()) {
            result.addErrorMessage("Email required", ResultType.INVALID);
        }

        return result;
    }
    private void validatePassword(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            String errorMsg = String.format("password must be at least %S characters", MIN_PASSWORD_LENGTH);
            throw new ValidationException(errorMsg);
        }
        int digits = 0;
        int letters = 0;
        int others = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digits++;
            } else if (Character.isLetter(c)) {
                letters++;
            } else {
                others++;
            }
        }
        if (digits == 0 || letters == 0 || others == 0) {
            throw new ValidationException("password must contain a digit, a letter, and a non-digit/non-letter");
        }
    }
}
