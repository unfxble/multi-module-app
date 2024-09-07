package ru.alexbat.managerapp.repository;

import ru.alexbat.managerapp.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(Long productId);

    void deleteById(Long id);
}
