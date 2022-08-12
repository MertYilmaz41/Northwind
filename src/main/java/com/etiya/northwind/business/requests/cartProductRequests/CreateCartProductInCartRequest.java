package com.etiya.northwind.business.requests.cartProductRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartProductInCartRequest {

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
