package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Product;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("unchecked")
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    List<Product> findAll();

    @Override
    Product save(Product product);

    @Override
    Optional<Product> findById(Long id);
    //Optionally - find by name

    @Override
    void deleteById(Long id);


}
