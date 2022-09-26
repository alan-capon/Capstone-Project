package learn.productReviews.domain;

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

    public List<Review> findAll(){

        return repository.findAll();
    }

    public Review findById(int id){

        return repository.findById(id);
    }

    public List<Review> findByUser(AppUser user){

        return repository.findByUser(user);
    }

    public List<Review> findByProduct(Product product){

        return repository.findByProduct(product);
    }

    public Result<Review> add(Review review){

    }

    public Result<Review> update(Review review){

    }

    public Result<Review> deleteById(int id){

    }

    private Result<Review> validate(Review review){

        Result<Review> result = new Result<>();
        if(review == null){
            result.addErrorMessage("Review cannot be null.", ResultType.INVALID);
            return result;
        }

        // checks if user exists and is enabled
        if(review.getUser() == null || !review.getUser().isEnabled()){
            result.addErrorMessage("User is required.", ResultType.INVALID);
        }

        if (review.getProduct() == null){
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
