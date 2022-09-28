package learn.productReviews.data;

import learn.productReviews.data.mappers.ReviewMapper;
import learn.productReviews.models.AppUser;
import learn.productReviews.models.Product;
import learn.productReviews.models.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
    public List<Review> findAll() throws DataAccessException{
        final  String sql = """
                select product_review_id, app_user_id, product_id, date, review
                from product_reviews;
                """;

        return jdbcTemplate.query(sql, new ReviewMapper());
    }

    @Override
    public Review findById(int id) throws DataAccessException{
        return null;
    }

    @Override
    public List<Review> findByUser(AppUser user) throws DataAccessException{
        return null;
    }

    @Override
    public List<Review> findByProduct(Product product) throws DataAccessException{
        return null;
    }

    @Override
    public List<Review> findByProduct(int productId) throws DataAccessException{
        final  String sql = """
                select product_review_id, app_user_id, product_id, date, review
                from product_reviews
                where product_id = ?;
                """;
        return jdbcTemplate.query(sql, new ReviewMapper(), productId);
    }



    @Override
    public Review add(Review review) throws DataAccessException{

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
    public boolean update(Review review) throws DataAccessException{
        final String sql = """
                update product_reviews set
                review = ?
                where product_review-id = ?;
                """;

        return jdbcTemplate.update(sql, review.getContent(), review.getId()) > 0;
    }

    @Override
    public boolean deleteById(int reviewId) throws DataAccessException{
        return jdbcTemplate.update("delete from product_review where product_review_id = ?", reviewId) > 0;
    }
}
