package reviews.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reviews.domain.Result;
import reviews.domain.ResultType;
import reviews.domain.ReviewService;
import reviews.models.Review;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public List<Review> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public List<Review> findByProduct(@PathVariable int productId){
        return service.findByProduct(productId);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Review review) {
        Result<Review> result = service.add(review);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Review review) {
        if (id != review.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
        }

        Result<Review> result = service.update(review);
        if (!result.isSuccess()) {
            if (result.getResultType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
            } else {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST); // 400
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Result<Review> result = service.deleteById(id);
        if (result.getResultType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }
}
