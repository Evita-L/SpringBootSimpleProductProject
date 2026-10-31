package gr.demo.productapi.service;

import gr.demo.productapi.entity.Product;
import gr.demo.productapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public String deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            return "There was an issue with product " + id;
        }
        return "Product " + id + " was deleted succesfully";
    }
}
