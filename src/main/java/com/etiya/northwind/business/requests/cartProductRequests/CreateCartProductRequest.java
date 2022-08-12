package com.etiya.northwind.business.requests.cartProductRequests;

import com.etiya.northwind.entities.concretes.Cart;
import com.etiya.northwind.entities.concretes.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartProductRequest {

    @NotNull
    @Positive
    private int cartId;

    @NotNull
    @Positive
    private int productId;

    @NotNull
    @Positive
    private double unitPrice;

    @NotNull
    @Positive
    private int quantity;
}
