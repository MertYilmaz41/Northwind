package com.etiya.northwind.business.requests.productRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotNull
    @Min(1)
    @Max(1000)
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
