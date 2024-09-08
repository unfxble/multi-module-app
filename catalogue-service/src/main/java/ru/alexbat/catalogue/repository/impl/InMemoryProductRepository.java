package ru.alexbat.catalogue.repository.impl;

import org.springframework.stereotype.Repository;
import ru.alexbat.catalogue.entity.Product;
import ru.alexbat.catalogue.repository.ProductRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    private final List<Product> products = Collections.synchronizedList(new ArrayList<>());

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public Product save(Product product) {
        product.setId(products.stream()
                .max(Comparator.comparingLong(Product::getId))
                .map(Product::getId)
                .orElse(0L) + 1);
        products.add(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return products.stream()
                .filter(product -> Objects.equals(productId, product.getId()))
                .findFirst();
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(product -> Objects.equals(product.getId(), id));
    }
}
