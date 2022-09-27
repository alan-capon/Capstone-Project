package learn.productReviews.data;

import learn.productReviews.models.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll() throws DataAccessException;

    Product findById(int id) throws DataAccessException;

    Product findByName(String name) throws DataAccessException;

    Product add(Product product) throws DataAccessException;

    boolean update(Product product) throws DataAccessException;

    boolean deleteById(int id) throws DataAccessException;
}
