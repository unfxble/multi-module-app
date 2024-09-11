package ru.alexbat.manager.client;

import ru.alexbat.manager.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRestClient {

    List<Product> findAllProducts(String filter);

    Product createProduct(String title, String details);

    Optional<Product> findProduct(Long productId);

    void updateProduct(Long productId, String title, String details);

    void deleteProduct(Long productId);
}