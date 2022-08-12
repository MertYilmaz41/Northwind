package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductRepository extends JpaRepository<CartProduct,Integer> {

}
