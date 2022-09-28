package learn.productReviews.domain;

import learn.productReviews.data.DataAccessException;
import learn.productReviews.data.ProductRepository;
import learn.productReviews.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProductServiceTest {

    @Autowired
    ProductService service;

    @MockBean
    ProductRepository repository;

    @Test
    void shouldAdd() {

    }

    @Test
    void shouldNotAddExistingProduct() {
    }

    @Test
    void shouldUpdate() throws DataAccessException {

        Product expected = new Product(1, "product", "");

        when(repository.update(expected)).thenReturn(true);

        Result<Product> result = service.update(expected);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldDeleteById() throws DataAccessException {

        when(repository.deleteById(1)).thenReturn(true);

        Result<Product> result = service.deleteById(1);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotDeleteNonExistentProduct() throws DataAccessException {

        Result<Product> result = service.deleteById(1024);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("Product 1024 was not found"));
    }

}