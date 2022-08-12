package com.etiya.northwind.business.responses.cartProducts;

import com.etiya.northwind.entities.concretes.Cart;
import com.etiya.northwind.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductListResponse {

    private int cartProductId;
    private int cartId;
    private int productId;
    private double unitPrice;
    private int quantity;
}
