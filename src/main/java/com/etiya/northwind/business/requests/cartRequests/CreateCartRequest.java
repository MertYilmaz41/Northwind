package com.etiya.northwind.business.requests.cartRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartRequest extends CartRequest{

    private String customerId;
}
