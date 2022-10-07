package reviews.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reviews.domain.ProductService;
import reviews.domain.Result;
import reviews.domain.ResultType;
import reviews.models.Product;


import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> findAll() {
        return service.findAll();
    }

    @GetMapping("/{name}")
    public Product findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Product product) {
        Result<Product> result = service.add(product);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Product product) {
        if (id != product.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
        }

        Result<Product> result = service.update(product);
        if (!result.isSuccess()) {
            if (result.getResultType() == ResultType.NOT_FOUND) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
            } else {
                return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST); // 400
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
    }

}
