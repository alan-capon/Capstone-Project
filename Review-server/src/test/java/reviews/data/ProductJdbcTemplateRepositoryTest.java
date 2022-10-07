package reviews.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import reviews.models.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductJdbcTemplateRepositoryTest {

    @Autowired
    ProductJdbcTemplateRepository repository;

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
    void findAll() {
        List<Product> result = repository.findAll();
        assertNotNull(result);
        assertTrue(result.size() >= 2);
    }

    @Test
    void shouldFindByName() {
        Product result = repository.findByName("iPhone");
        assertNotNull(result);
    }

    @Test
    void shouldFindById() {
        Product result = repository.findById(1);
        assertNotNull(result);
    }

    @Test
    void shouldAdd() {
        Product product = new Product();
        product.setName("PS5");
        product.setDescription("Gaming console");
        Product result = repository.add(product);
        assertNotNull(result);

        assertEquals(3, product.getId());
    }

    @Test
    void shouldUpdate() {
        Product product = new Product();
        product.setId(2);
        product.setName("Sony Headphones");
        product.setDescription("Over the ear headphones");

        assertTrue(repository.update(product));
    }

}