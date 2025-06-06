package com.intolerance_app.service;

import com.intolerance_app.exception.ProductNotFoundException;
import com.intolerance_app.model.ProductModel;
import com.intolerance_app.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductModel createProduct(ProductModel product) {
        return productRepository.save(product);
    }

    public ProductModel updateProduct(Long id, ProductModel product) {
        ProductModel existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));

        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductDescription((product.getProductDescription()));

        return productRepository.save(existingProduct);
    }

    public Optional<ProductModel> getProductById(Long id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product;
        } else {
            throw new EntityNotFoundException("Product not found");
        }

    }

    public void deleteProduct(Long id) {
        if(!productRepository.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
