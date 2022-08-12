package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.CartProductService;
import com.etiya.northwind.business.requests.cartProductRequests.CreateCartProductRequest;
import com.etiya.northwind.business.requests.cartProductRequests.UpdateCartProductRequest;
import com.etiya.northwind.business.requests.orderDetailRequests.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetailRequests.UpdateOrderDetailRequest;
import com.etiya.northwind.business.responses.cartProducts.CartProductListResponse;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailsListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cartProducts")
public class CartProductController {
    CartProductService cartProductService;

    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }

    @GetMapping("/getall")
    public DataResult<List<CartProductListResponse>> getAll() {
        return this.cartProductService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCartProductRequest createCartProductRequest) {
        return this.cartProductService.add(createCartProductRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateCartProductRequest updateCartProductRequest) {
        return this.cartProductService.update(updateCartProductRequest);
    }

    @DeleteMapping("/delete/{cartProductId}")
    public Result delete(@Valid @PathVariable int cartProductId) {
        return this.cartProductService.delete(cartProductId);
    }
}
