package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.Category;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select a from Product a")
    Page<Product> findAllProducts(Pageable pageable);

    //List<Product> findProductsByCategoryId(int categoryId);

    List<Product> findByCategory_CategoryId(int categoryId);

    Product findByProductName(String productName);

}
