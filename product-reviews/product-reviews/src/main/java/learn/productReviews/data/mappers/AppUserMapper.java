package learn.productReviews.data.mappers;

import learn.productReviews.models.AppUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppUserMapper implements RowMapper<AppUser> {
    private List<String> roles = new ArrayList<>();

    public AppUserMapper() {
    }

    public AppUserMapper(List<String> roles) {
        this.roles = roles;
    }
    @Override
    public AppUser mapRow(ResultSet rs, int i) throws SQLException {
        return new AppUser(
                rs.getInt("app_user_id"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rs.getBoolean("disabled"),
                roles);
    }
}