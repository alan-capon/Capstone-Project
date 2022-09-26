package reviews.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import reviews.models.Review;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ReviewJdbcTemplateRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReviewJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Review add(Review review) {

        final String sql = """
                insert into product_reviews (app_user_id, product_id, date, review)
                values (?,?,?,?);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, review.getUser().getAppUserId());
            ps.setDate(2, Date.valueOf(review.getDate()));
            ps.setString(3, review.getContent());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        review.setId(keyHolder.getKey().intValue());
        return review;
    }

    public boolean update(Review review) {
        final String sql = """
                update product_reviews set
                review = ?
                where product_review-id = ?;
                """;

        return jdbcTemplate.update(sql, review.getContent(), review.getId()) > 0;
    }

    public boolean deleteById(int reviewId) {
        return jdbcTemplate.update("delete from product_review where product_review_id = ?", reviewId) > 0;
    }
}
