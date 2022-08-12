package com.etiya.northwind.business.requests.cartRequests;

import com.etiya.northwind.business.requests.cartProductRequests.CreateCartProductInCartRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CartRequest {
    @NotNull
    private List<CreateCartProductInCartRequest> cartProductRequests;
}
