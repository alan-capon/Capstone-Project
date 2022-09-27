package reviews.data;

import reviews.models.Review;

import java.util.List;

public interface ReviewRepository {

    List<Review> findAll();

    List<Review> findByProduct(int productId);
    Review add(Review review);

    boolean update(Review review);

    boolean deleteById(int reviewId);
}
