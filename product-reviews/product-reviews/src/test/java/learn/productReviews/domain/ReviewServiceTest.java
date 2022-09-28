package learn.productReviews.domain;

import learn.productReviews.data.DataAccessException;
import learn.productReviews.data.ReviewRepository;
import learn.productReviews.models.Product;
import learn.productReviews.models.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ReviewServiceTest {

    @Autowired
    ReviewService service;

    @MockBean
    ReviewRepository repository;

    @Test
    void shouldAdd() throws DataAccessException {

        Review expected = new Review(0, 1, 1, LocalDate.now(), "test");

        when(repository.add(expected)).thenReturn(expected);

        Result<Review> result = service.add(expected);

        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
    }

    @Test
    void shouldUpdate() throws DataAccessException {

        Review expected = new Review(1, 1, 1, LocalDate.now(), "test");

        when(repository.update(expected)).thenReturn(true);

        Result<Review> result = service.update(expected);

        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
    }

    @Test
    void shouldDeleteById() throws DataAccessException {

        when(repository.deleteById(1)).thenReturn(true);
        Result<Review> result = service.deleteById(1);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotDeleteNonExistentReview() throws DataAccessException {

        Result<Review> result = service.deleteById(1024);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("Review 1024 was not found"));
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}