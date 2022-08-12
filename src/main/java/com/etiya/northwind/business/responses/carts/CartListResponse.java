package com.etiya.northwind.business.responses.carts;

import com.etiya.northwind.entities.concretes.CartProduct;
import com.etiya.northwind.entities.concretes.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartListResponse {

    private int cartId;
    private String customerId;

}
