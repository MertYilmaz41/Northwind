package com.etiya.northwind.business.requests.productRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    @NotNull
    @Positive
    private int productId;

    @NotBlank
    private String productName;

    @NotNull
    @PositiveOrZero
    private double unitPrice;

    @NotNull
    @PositiveOrZero
    private int unitsInStock;

    @NotNull
    @Positive
    private int categoryId;

    @NotNull
    @PositiveOrZero
    private int discontinued;
}
