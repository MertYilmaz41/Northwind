package com.etiya.northwind.business.requests.cartProductRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartProductRequest {

    @NotNull
    @Positive
    private int cartProductId;

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
