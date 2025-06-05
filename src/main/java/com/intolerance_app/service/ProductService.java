package com.intolerance_app.service;


import com.intolerance_app.model.ProductModel;
import io.micrometer.observation.ObservationFilter;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    public static List<ProductModel> getAllProducts() {
    }

    public ProductModel createProduct(@Valid ProductModel productModel) {
    }

    public ProductModel updateProduct(Long id, ProductModel product) {
    }

    public Optional<ProductModel> getProductById(Long id) {
    }

    public void deleteProduct(Long id) {
    }
}
