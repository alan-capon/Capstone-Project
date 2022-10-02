package learn.productReviews.data;

import learn.productReviews.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppUserRepository {
    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    List<AppUser> getFriends(int appUserId);

    @Transactional
    AppUser addFriend(AppUser currentUser, AppUser friend);

    @Transactional
    AppUser create(AppUser user);

    @Transactional
    void update(AppUser user);
}
