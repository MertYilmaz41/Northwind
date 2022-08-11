package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.Category;
import com.etiya.northwind.entities.concretes.OrderDetails;
import com.etiya.northwind.entities.concretes.OrderDetailsId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {
    @Query("select a from OrderDetails a")
    Page<OrderDetails> findAllOrderDetails(Pageable pageable);

    @Query("select o from OrderDetails o inner join Product p on o.product.productId = p.productId where p.productId= :productId")
    List<OrderDetails> findOrderDetailsByProductId(int productId);
}
