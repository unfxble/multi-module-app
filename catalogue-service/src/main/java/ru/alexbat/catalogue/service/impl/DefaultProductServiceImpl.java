package ru.alexbat.catalogue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.alexbat.catalogue.entity.Product;
import ru.alexbat.catalogue.repository.ProductRepository;
import ru.alexbat.catalogue.service.ProductService;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Iterable<Product> findAllProducts(String filter) {
        if (StringUtils.hasText(filter)) {
//            return productRepository.findAllByTitleLikeIgnoreCase(filter);
            return productRepository.findAllByFilterNamedQuery("%" + filter + "%");
        } else {
            return productRepository.findAll();
        }
    }

    @Override
    @Transactional
    public Product createProduct(String title, String details) {
        return productRepository.save(Product.builder()
            .title(title)
            .details(details)
            .build());
    }

    @Override
    public Optional<Product> findProduct(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
