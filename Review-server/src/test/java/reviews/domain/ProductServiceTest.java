package reviews.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import reviews.data.ProductRepository;
import reviews.models.Product;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService service;

    @MockBean
    ProductRepository repository;

    private static final String TEST_STRING_555_CHARACTERS =
            "Mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm\n" +
                    "Oooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo" +
                    "nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn" +
                    "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" +
                    "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" +
                    "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" +
                    "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc" +
                    "cccccccccaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n";

    @Test
    void shouldAdd() throws DataAccessException {

        Product expected = new Product(0, "New Product", "");

        when(repository.add(expected)).thenReturn(expected);

        Result<Product> result = service.add(expected);

        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());

    }

    @Test
    void shouldNotAddExistingProduct() throws DataAccessException {

        Product existing = new Product(1, "existing product", "");
        Product expected = new Product(0, "existing product", "");
        List<Product> products = new ArrayList<>();
        products.add(existing);

        when(repository.findAll()).thenReturn(products);
        when(repository.add(expected)).thenReturn(null);
        Result<Product> result = service.add(expected);

        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotAddWhenProductIdIsSet() throws DataAccessException {

        Product expected = new Product(1, "New Product", "");

        when(repository.add(expected)).thenReturn(expected);

        Result<Product> result = service.add(expected);

        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

    }

    @Test
    void shouldNotAddNullProduct() throws DataAccessException {

        Product expected = null;

        when(repository.add(expected)).thenReturn(null);
        Result<Product> result = service.add(expected);

        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotAddProductWithNullOrBlankName() throws DataAccessException {

        Product expected = new Product(0, null, "");

        when(repository.add(expected)).thenReturn(null);
        Result<Product> result = service.add(expected);

        assertFalse(result.isSuccess());
        assertNull(result.getPayload());

        expected.setName(" ");
        result = service.add(expected);
        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldNotAddProductWhenNameIsTooLong() throws DataAccessException {

        Product expected = new Product(0, "", "");
        expected.setName(TEST_STRING_555_CHARACTERS);

        when(repository.add(expected)).thenReturn(null);
        Result<Product> result = service.add(expected);

        assertFalse(result.isSuccess());
        assertNull(result.getPayload());
    }

    @Test
    void shouldUpdate() throws DataAccessException {

        Product expected = new Product(1, "product", "");

        when(repository.update(expected)).thenReturn(true);

        Result<Product> result = service.update(expected);

        assertTrue(result.isSuccess());
    }

//    @Test
//    void shouldDeleteById() throws DataAccessException {
//
//        when(repository.deleteById(1)).thenReturn(true);
//
//        Result<Product> result = service.deleteById(1);
//
//        assertTrue(result.isSuccess());
//    }

//    @Test
//    void shouldNotDeleteNonExistentProduct() throws DataAccessException {
//
//        Result<Product> result = service.deleteById(1024);
//
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getErrorMessages().size());
//        assertTrue(result.getErrorMessages().get(0).contains("Product 1024 was not found."));
//    }

}