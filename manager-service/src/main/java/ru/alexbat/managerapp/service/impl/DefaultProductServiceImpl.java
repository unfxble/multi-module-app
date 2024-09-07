package ru.alexbat.managerapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alexbat.managerapp.controller.payload.NewProductPayload;
import ru.alexbat.managerapp.entity.Product;
import ru.alexbat.managerapp.repository.ProductRepository;
import ru.alexbat.managerapp.service.ProductService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(NewProductPayload payload) {
        return productRepository.save(Product.builder()
                .title(payload.title())
                .details(payload.details())
                .build());
    }

    @Override
    public Optional<Product> findProduct(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public void updateProduct(Long id, String title, String details) {
        productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDetails(details);
                }, () -> {
                    throw new NoSuchElementException("Product not found");
                });
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
