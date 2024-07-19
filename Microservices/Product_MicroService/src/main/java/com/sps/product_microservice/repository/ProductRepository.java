package com.sps.product_microservice.repository;

import com.sps.product_microservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE "
           + "(:categoryId IS NULL OR p.category.id=:categoryId)")
    Page<Product> findProducts(@Param("categoryId") Optional<Long> categoryId, Pageable pageable);

    // Find the Products using Category_Id
    List<Product> findByCategory_Id(long categoryId);

}
