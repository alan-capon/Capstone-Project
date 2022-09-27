package reviews.data;

import reviews.models.Product;

public interface ProductRepository {
    Product findByName(String name);

    Product add(Product product);

    boolean update(Product product);
}
