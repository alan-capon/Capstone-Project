package learn.productReviews.data;

import learn.productReviews.App;
import learn.productReviews.models.AppUser;
import learn.productReviews.models.Product;
import learn.productReviews.models.Review;

import java.util.List;

public interface ReviewRepository {

    List<Review> findAll() throws DataAccessException;

    Review findById(int id) throws DataAccessException;

    List<Review> findByUser(AppUser user) throws DataAccessException;

    List<Review> findByProduct(Product product) throws DataAccessException;

    List<Review> findByProduct(int productId) throws DataAccessException;

    Review add(Review review) throws DataAccessException;

    boolean update(Review review) throws DataAccessException;

    boolean deleteById(int id) throws DataAccessException;
}
