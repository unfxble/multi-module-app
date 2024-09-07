package ru.alexbat.managerapp.service;

import ru.alexbat.managerapp.controller.payload.NewProductPayload;
import ru.alexbat.managerapp.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAllProducts();

    Product createProduct(NewProductPayload payload);

    Optional<Product> findProduct(Long productId);

    void updateProduct(Long id, String title, String details);

    void deleteProduct(Long id);
}
