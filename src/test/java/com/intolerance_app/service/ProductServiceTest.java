package com.intolerance_app.service;

import com.intolerance_app.enums.HistamineLevelEnum;
import com.intolerance_app.model.ProductModel;
import com.intolerance_app.model.UserModel;
import com.intolerance_app.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private ProductModel createProductModel(Long id, String productName, String productDescription, HistamineLevelEnum histamineLevelEnum, UserModel user) {
        return new ProductModel(id, productName, productDescription, histamineLevelEnum, user);
    }

    @Test
    void getAllProducts() {
        ProductModel product1 = createProductModel(1L, "tomato",
                "contains a large amount of histamine", HistamineLevelEnum.HIGH, new UserModel());
        ProductModel product2 = createProductModel(2L, "cucumber",
                "contains no histamine", HistamineLevelEnum.LOW, new UserModel());

        List<ProductModel> expectedProducts = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<ProductModel> akctualProduct = productService.getAllProducts();

        assertNotNull(akctualProduct);
        assertEquals(2, akctualProduct.size());
        assertEquals(expectedProducts, akctualProduct);

    }


    @Test
    void createProduct() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void deleteProduct() {
    }
}