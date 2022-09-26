package learn.productReviews.domain;

import learn.productReviews.models.Product;

import java.util.List;

public class ProductService {

    private final ProductRepository repository;
    private static final int MAX_PRODUCT_NAME_LENGTH = 255;
    private static final int MAX_PRODUCT_DESCRIPTION_LENGTH = 1000;

    public ProductService(ProductRepository productRepository) {
        this.repository = productRepository;
    }

    public List<Product> findAll(){

        return repository.findAll();
    }

    public Product findById(int id){

        return repository.findById(id);
    }

    public Product findByName(String name){

        return repository.findByName(name);
    }

    public Result<Product> add(Product product){

        Result<Product> result = new Result<>();

        if (product != null && product.getId() > 0){
            result.addErrorMessage("Product ID should not be set.", ResultType.INVALID);
        }

        if (result.isSuccess()){
            result.setPayload(repository.add(product));
        }

        return result;
    }

    public Result<Product> update(Product product){

        Result<Product> result = validate(product);

        if (product.getId() <= 0){
            result.addErrorMessage("Product ID is required.", ResultType.INVALID);
        }

        if (result.isSuccess()){
            if (repository.update(product)){
                result.setPayload(product);
            } else {
                result.addErrorMessage("Product %s was not found.", ResultType.NOT_FOUND, product.getId());
            }
        }
        return result;
    }

    public Result<Product> deleteById(int id){

        Result<Product> result = new Result<>();
        if (!repository.deleteById(id)){
            result.addErrorMessage("Product %s was not found.", ResultType.NOT_FOUND, id);
        }
        return result;
    }

    private Result<Product> validate(Product product){

        Result<Product> result = new Result<>();
        if (product == null){
            result.addErrorMessage("Product cannot be null.", ResultType.INVALID);
            return result;
        }

        if (product.getName() == null || product.getName().isBlank()){
            result.addErrorMessage("Product name is required.", ResultType.INVALID);
        }

        if (product.getName().length() > MAX_PRODUCT_NAME_LENGTH){
            result.addErrorMessage("Product name must be less than %s characters.",
                    ResultType.INVALID, MAX_PRODUCT_NAME_LENGTH);
        }

        if (product.getDescription().length() > MAX_PRODUCT_DESCRIPTION_LENGTH){
            result.addErrorMessage("Product description must be less than %s characters.",
                    ResultType.INVALID, MAX_PRODUCT_DESCRIPTION_LENGTH);
        }

        return result;
    }

}
