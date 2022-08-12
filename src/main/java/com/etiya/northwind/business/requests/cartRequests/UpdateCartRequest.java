package com.etiya.northwind.business.requests.cartRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartRequest extends CartRequest{

    @NotNull
    @Positive
    private int cartId;



    private String customerId;
}
