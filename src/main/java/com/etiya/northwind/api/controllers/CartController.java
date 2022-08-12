package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.CartService;
import com.etiya.northwind.business.requests.cartRequests.CreateCartRequest;
import com.etiya.northwind.business.requests.cartRequests.UpdateCartRequest;
import com.etiya.northwind.business.requests.orderRequests.CreateOrderRequest;
import com.etiya.northwind.business.requests.orderRequests.UpdateOrderRequest;
import com.etiya.northwind.business.responses.carts.CartListResponse;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/getall")
    public DataResult<List<CartListResponse>> getAll(){
        return this.cartService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCartRequest createCartRequest){
        return this.cartService.add(createCartRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateCartRequest updateCartRequest){
        return  this.cartService.update(updateCartRequest);
    }

    @DeleteMapping("/delete/{cartId}")
    public Result delete(@Valid @PathVariable int cartId){
        return   this.cartService.delete(cartId);
    }

    @GetMapping("/getbyid/{cartId}")
    public DataResult<CartListResponse> getById(@PathVariable int cartId){
        return this.cartService.getById(cartId);
    }
}
