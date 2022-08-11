package com.etiya.northwind.business.requests.orderDetailRequests;

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
public class UpdateOrderDetailRequest {

    @NotNull
    @Positive
    private int productId;

    @NotNull
    @Positive
    private int employeeId;

    @NotNull
    @Positive
    private int orderId;

    @NotNull
    @PositiveOrZero
    private double unitPrice;

    @NotNull
    @PositiveOrZero
    private int quantity;

    @NotNull
    @PositiveOrZero
    private double discount;
}
