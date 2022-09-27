package learn.productReviews.domain;

import learn.productReviews.data.DataAccessException;
import learn.productReviews.data.ReviewRepository;
import learn.productReviews.models.AppUser;
import learn.productReviews.models.Product;
import learn.productReviews.models.Review;

import java.util.List;

public class ReviewService {

    private final ReviewRepository repository;
    private static final int MAX_CONTENT_LENGTH = 1000;


    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> findAll() throws DataAccessException {

        return repository.findAll();
    }

    public Review findById(int id) throws DataAccessException {

        return repository.findById(id);
    }

    public List<Review> findByUser(AppUser user) throws DataAccessException {

        return repository.findByUser(user);
    }

    public List<Review> findByProduct(Product product) throws DataAccessException {

        return repository.findByProduct(product);
    }

    public Result<Review> add(Review review) throws DataAccessException {

        Result<Review> result = validate(review);

        if (review != null && review.getId() > 0){
            result.addErrorMessage("Review ID should not be set", ResultType.INVALID);
        }

        if (result.isSuccess()){
            result.setPayload(repository.add(review));
        }
        return result;
    }

    public Result<Review> update(Review review) throws DataAccessException {

        Result<Review> result = validate(review);

        if (review.getId() <= 0){
            result.addErrorMessage("Review ID is required.", ResultType.INVALID);
        }

        if (result.isSuccess()){
            if (repository.update(review)){
                result.setPayload(review);
            } else {
                result.addErrorMessage("Review %s was not found.", ResultType.NOT_FOUND, review.getId());
            }
        }
        return result;
    }

    public Result<Review> deleteById(int id) throws DataAccessException {

        Result<Review> result = new Result<>();
        if (!repository.deleteById(id)){
            result.addErrorMessage("Review %s was not found.", ResultType.NOT_FOUND, id);
        }
        return result;
    }

    private Result<Review> validate(Review review){

        Result<Review> result = new Result<>();
        if(review == null){
            result.addErrorMessage("Review cannot be null.", ResultType.INVALID);
            return result;
        }

        // checks if user exists
        if(review.getAppUserId() <= 0){
            result.addErrorMessage("User is required.", ResultType.INVALID);
        }

        if (review.getProductId() <= 0){
            result.addErrorMessage("Product is required.", ResultType.INVALID);
        }

        if (review.getDate() == null){
            result.addErrorMessage("Date is required.", ResultType.INVALID);
        }

        if (review.getContent() == null || review.getContent().isBlank()){
            result.addErrorMessage("Review content is required.", ResultType.INVALID);
        }

        if (review.getContent().length() > MAX_CONTENT_LENGTH){
            result.addErrorMessage("Review content must be less than %s characters",
                    ResultType.INVALID, MAX_CONTENT_LENGTH);
        }

        return result;
    }
}
