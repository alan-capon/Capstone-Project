package reviews.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import reviews.models.Review;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReviewJdbcTemplateRepositoryTest {

    @Autowired
    ReviewJdbcTemplateRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    static boolean hasSetup = false;

    @BeforeEach
    void setup() {
        if (!hasSetup) {
            hasSetup = true;
            jdbcTemplate.update("call set_known_good_state();");
        }
    }

    @Test
    void shouldFindAll() {
        List<Review> result = repository.findAll();
        assertNotNull(result);
        assertTrue(result.size() >= 2);
    }

    @Test
    void shouldFindByProduct() {
        List<Review> result = repository.findByProduct(1);
        assertNotNull(result);
        assertTrue(result.size() == 1);
    }

    @Test
    void shouldFindById() {
        Review review = repository.findById(2);
        assertNotNull(review);
        assertEquals(review.getId(), 2);
    }

    @Test
    void shouldCreate() {
        Review review = new Review();
        review.setAppUserId(1);
        review.setProductId(1);
        review.setDate(LocalDate.of(2022, 8, 22));
        review.setContent("Very comfortable");
        Review result = repository.add(review);
        assertNotNull(result);
        assertEquals(3, result.getId());
    }

    @Test
    void shouldUpdate() {
        Review review = new Review();
        review.setId(2);
        review.setAppUserId(1);
        review.setProductId(2);
        review.setDate(LocalDate.of(2022,9,22));
        review.setContent("Worst headphones");

        assertTrue(repository.update(review));
    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteById(2));
    }
}