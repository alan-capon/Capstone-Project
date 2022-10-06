package learn.productReviews.domain;

import learn.productReviews.data.AppUserRepository;
import learn.productReviews.models.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public AppUser findById(int id) throws UsernameNotFoundException {
        AppUser appUser = repository.findById(id);
        if (appUser == null || !appUser.isEnabled()) {
            throw new UsernameNotFoundException(id + " not found");
        }
        return appUser;
    }

    public AppUser create(String username, String password) {
        validate(username);
        validatePassword(password);
        password = encoder.encode(password);
        AppUser appUser = new AppUser(0, username, password, false, List.of("User"));
        return repository.create(appUser);
    }

    public List<AppUser> getFriends(int appUserId){

        return repository.getFriends(appUserId);
    }

    public Result<AppUser> addFriend(AppUser currentUser, AppUser friend){

        Result<AppUser> result = new Result<>();

        if (currentUser != null && friend != null){
            if (!currentUser.getFriends().contains(friend)){
                result.setPayload(repository.addFriend(currentUser, friend));
            }
        }

        else {
            result.addErrorMessage("Users cannot be null", ResultType.INVALID);
        }
        return result;
    }

    private void validate(String username) {
        if (username == null || username.isBlank()) {
            throw new ValidationException("username is required");
        }
        if (username.length() > MAX_USERNAME_LENGTH) {
            String errorMsg = String.format("username must be less than %s characters", MAX_USERNAME_LENGTH);
            throw new ValidationException(errorMsg);
        }
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