package reviews.data.mappers;

import org.springframework.jdbc.core.RowMapper;
import reviews.models.AppUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AppUserMapper implements RowMapper<AppUser> {

    private final List<String> roles;
    public AppUserMapper(List<String> roles) {
        this.roles = roles;
    }

    public AppUser mapRow(ResultSet rs, int i) throws SQLException {
        return new AppUser(
                rs.getInt("app_user_id"),
                rs.getString("username"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("password_hash"),
                rs.getBoolean("disabled"),
                roles);
    }
}
