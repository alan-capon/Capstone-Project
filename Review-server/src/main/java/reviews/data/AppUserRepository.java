package reviews.data;

import reviews.models.AppUser;

public interface AppUserRepository {
    AppUser findByUsername(String username);

    AppUser create(AppUser user);

    void update(AppUser user);
}
