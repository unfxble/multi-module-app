package ru.alexbat.catalogue.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.alexbat.catalogue.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Iterable<Product> findAllByTitleLikeIgnoreCase(String title);

    @Query(value = "SELECT p from Product p where p.title ilike :filter")
    Iterable<Product> findAllByFilterJpql(@Param("filter") String filter);

    @Query(value = "SELECT * from catalogue.t_product  where title ilike :filter", nativeQuery = true)
    Iterable<Product> findAllByFilterSql(@Param("filter") String filter);

    @Query(name = "Product.findAllByTitleLikeIgnoringCase")
    Iterable<Product> findAllByFilterNamedQuery(@Param("filter") String filter);
}


