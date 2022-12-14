package learn.productReviews.data.mappers;

import learn.productReviews.models.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {

    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt("product_review_id"));
        review.setAppUserId(resultSet.getInt("app_user_id"));
        review.setProductId(resultSet.getInt("product_id"));
        review.setDate(resultSet.getDate("date").toLocalDate());
        review.setContent(resultSet.getString("review"));
        return review;
    }
}
