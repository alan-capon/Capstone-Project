package reviews.data.mappers;

import org.springframework.jdbc.core.RowMapper;
import reviews.models.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {

    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt("product_review_id"));
        review.setDate(resultSet.getDate("date").toLocalDate());
        review.setContent(resultSet.getString("review"));
        review.setProductId(resultSet.getInt("product_id"));
        review.setAppUserId(resultSet.getInt("app_user_id"));
        review.setUsername(resultSet.getString("username"));
        review.setFirstName(resultSet.getString("first_name"));
        review.setLastName(resultSet.getString("last_name"));
        return review;
    }
}
