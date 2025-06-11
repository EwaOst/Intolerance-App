package com.intolerance_app.controller;

import com.intolerance_app.model.ProductModel;
import com.intolerance_app.service.ProductService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@Valid @RequestBody ProductModel productModel) {
        ProductModel newProduct = productService.createProduct(productModel);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long id, @RequestBody ProductModel product) {
        ProductModel updateProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updateProduct);
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent()
                .build();
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getFilteredProducts(
            @RequestParam(required = false) Boolean filterIntolerances,
            @RequestParam(required = false) String histamineLevel,
            @RequestParam Long userId) {

        List<ProductModel> products = productService.getFilteredProducts(userId, filterIntolerances, histamineLevel);
        return ResponseEntity.ok(products);
    }

}
