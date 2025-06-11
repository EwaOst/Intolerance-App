package com.intolerance_app.service;

import com.intolerance_app.enums.HistamineLevelEnum;
import com.intolerance_app.exception.ProductNotFoundException;
import com.intolerance_app.model.IntoleranceModel;
import com.intolerance_app.model.ProductModel;
import com.intolerance_app.model.UserModel;
import com.intolerance_app.repository.ProductRepository;
import com.intolerance_app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductModel createProduct(ProductModel newProduct) {
        ProductModel saved = productRepository.saveAndFlush(newProduct);
        return productRepository.findById(saved.getId()).orElseThrow();
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

    public List<ProductModel> getFilteredProducts(Long userId, Boolean filterIntolerances, String histamineLevel) {
        List<ProductModel> allProducts = productRepository.findAll();

        // Filtrowanie po nietolerancjach użytkownika
        if (Boolean.TRUE.equals(filterIntolerances)) {
            UserModel user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Set<IntoleranceModel> userIntolerances = user.getIntolerances();

            allProducts = allProducts.stream()
                    .filter(product -> Collections.disjoint(product.getIntolerances(), userIntolerances))
                    .collect(Collectors.toList());
        }

        // Filtrowanie po poziomie histaminy
        if (histamineLevel != null) {
            HistamineLevelEnum level = HistamineLevelEnum.valueOf(histamineLevel.toUpperCase());
            allProducts = allProducts.stream()
                    .filter(product -> product.getHistamineLevelEnum() == level)
                    .collect(Collectors.toList());
        }

        return allProducts;
    }
}
