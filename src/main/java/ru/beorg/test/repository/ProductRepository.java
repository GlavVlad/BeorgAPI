package ru.beorg.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.beorg.test.entity.Product;

import java.util.Optional;

/**
 * Created by Vlad on 05.02.2017.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findOneByName(String name);
}
