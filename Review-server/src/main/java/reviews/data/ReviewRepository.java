package reviews.data;

import reviews.models.Review;

import java.util.List;

public interface ReviewRepository {

    List<Review> findAll();

    Review findById(int id);

    List<Review> findByProduct(int productId);

    List<Review> findByUser(int userId);
    Review add(Review review);

    boolean update(Review review);

    boolean deleteById(int reviewId);
}
