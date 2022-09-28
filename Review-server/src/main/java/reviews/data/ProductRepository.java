package reviews.data;

import reviews.models.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    Product findByName(String name);

    Product findById(int productId);

    Product add(Product product);

    boolean update(Product product);
}
