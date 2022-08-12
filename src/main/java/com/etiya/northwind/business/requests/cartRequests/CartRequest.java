package com.etiya.northwind.business.requests.cartRequests;

import com.etiya.northwind.business.requests.cartProductRequests.CreateCartProductInCartRequest;
import com.etiya.northwind.business.requests.cartProductRequests.CreateCartProductRequest;
import com.etiya.northwind.business.requests.orderDetailRequests.CreateOrderDetailInOrderRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public abstract class CartRequest {
    @NotNull
    private List<CreateCartProductInCartRequest> cartProductRequests;
}
