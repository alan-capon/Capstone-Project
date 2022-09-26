package learn.productReviews.domain;

import learn.productReviews.models.Product;

import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){

        return productRepository.findAll();
    }

    public Product findById(int id){

        return productRepository.findById(id);
    }

    public Product findByName(String name){

        return productRepository.findByName(name);
    }

    public Result<Product> add(Product product){

        Result<Product> result = new Result<>();

    }

    public Result<Product> deleteById(int id){

        Result<Product> result = new Result<>();
        if (!productRepository.deleteById(id)){
            result.addErrorMessage("Product %s was not found", ResultType.NOT_FOUND, id);
        }
        return result;
    }

    private Result<Product> validate(Product product){

        Result<Product> result = new Result<>();
        if(product == null){
            result.addErrorMessage("Product cannot be null", ResultType.INVALID);
            return result;
        }

        if(product.getName() == null || product.getName().isBlank()){
            result.addErrorMessage("Product name is required", ResultType.INVALID);
        }

        return result;
    }

}
