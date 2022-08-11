package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.OrderDetailsService;
import com.etiya.northwind.business.requests.orderDetailRequests.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetailRequests.UpdateOrderDetailRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailsListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orderDetails")
public class OrderDetailsController {
    private OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping("/getall")
    public DataResult<List<OrderDetailsListResponse>> getAll() {
        return this.orderDetailsService.getAll();
    }


    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateOrderDetailRequest createOrderDetailRequest) {
       return this.orderDetailsService.add(createOrderDetailRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateOrderDetailRequest updateOrderDetailRequest) {
       return this.orderDetailsService.update(updateOrderDetailRequest);
    }

    @DeleteMapping("/delete/{orderId}/{productId}")
    public Result delete(@Valid @PathVariable int orderId, @Valid @PathVariable int productId) {
        return this.orderDetailsService.delete(orderId, productId);
    }


    @GetMapping("/getOrderDetailsByProductId/{productId}")
    public DataResult<List<OrderDetailsListResponse>> getOrderDetailsByProductId(@PathVariable int productId) {
        return this.orderDetailsService.getOrderDetailsByProductId(productId);
    }

    @GetMapping("/getByPage/{pageNumber}/{orderDetailsAmountInPage}")
    public PageDataResponse<OrderDetailsListResponse> getByPage(int pageNumber, int orderDetailsAmountInPage) {
        return this.orderDetailsService.getByPage(pageNumber, orderDetailsAmountInPage);
    }

    @GetMapping("/getByPageWithSorting/{pageNumber}/{orderDetailsAmountInPage}/{fieldName}/{isAsc}")
    public PageDataResponse<OrderDetailsListResponse> getByPageWithSorting(int pageNumber, int orderDetailsAmountInPage, String fieldName, boolean isAsc) {
        return this.orderDetailsService.getByPageWithSorting(pageNumber, orderDetailsAmountInPage, fieldName, isAsc);
    }
}
