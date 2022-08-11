package com.etiya.northwind.business.requests.orderRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest extends OrderRequest {

    @NotNull
    @Positive
    private int orderId;

    @NotNull
    @NotBlank
    private String customerId;

    @NotNull
    @Positive
    private int employeeId;

    @NotNull
    private LocalDate orderDate;
}
