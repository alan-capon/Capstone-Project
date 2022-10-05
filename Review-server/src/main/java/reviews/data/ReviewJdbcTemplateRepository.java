package reviews.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import reviews.data.mappers.ReviewMapper;
import reviews.models.Review;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@Repository
public class ReviewJdbcTemplateRepository implements ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReviewJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Review> findAll() {
        final  String sql = """
                select product_review_id, app_user_id, product_id, date, review
                from product_reviews;
                """;

        return jdbcTemplate.query(sql, new ReviewMapper());
    }
    @Override
    public List<Review> findByProduct(int id) {
        final String sql = """
                select p.product_review_id, p.date, p.review, p.product_id,
                a.app_user_id, a.username, a.first_name, a.last_name
                from app_user a
                inner join product_reviews p on a.app_user_id = p.app_user_id
                where p.product_id = ?;
                """;
//        final  String sql = """
//                select product_review_id, app_user_id, product_id, date, review
//                from product_reviews
//                where product_id = ?;
//                """;
//        List<Review> reviews = jdbcTemplate.query(sql, new ReviewMapper(), id);
//
//        if(!reviews.isEmpty()) {
//            addUsers(reviews, id);
//        }

        return jdbcTemplate.query(sql, new ReviewMapper(), id);
    }
    @Override
    public List<Review> findByUser(int userId) {
        final String sql = """
                select product_review_id, app_user_id, product_id, date, review
                from product_reviews
                where app_user_id = ?;
                """;
        return jdbcTemplate.query(sql, new ReviewMapper(), userId);
    }

    @Override
    public Review add(Review review) {

        final String sql = """
                insert into product_reviews (app_user_id, product_id, date, review)
                values (?,?,?,?);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, review.getAppUserId());
            ps.setInt(2, review.getProductId());
            ps.setDate(3, Date.valueOf(review.getDate()));
            ps.setString(4, review.getContent());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        review.setId(keyHolder.getKey().intValue());
        return review;
    }

    @Override
    public boolean update(Review review) {
        final String sql = """
                update product_reviews set
                review = ?
                where product_review_id = ?;
                """;

        return jdbcTemplate.update(sql, review.getContent(), review.getId()) > 0;
    }

    @Override
    public boolean deleteById(int reviewId) {
        return jdbcTemplate.update("delete from product_reviews where product_review_id = ?", reviewId) > 0;
    }

}
