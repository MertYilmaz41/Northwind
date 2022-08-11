package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.CustomerService;
import com.etiya.northwind.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.northwind.business.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {
    private CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getall")
    public DataResult<List<CustomerListResponse>> getAll() {
        return this.customerService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {
       return this.customerService.add(createCustomerRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {
       return  this.customerService.update(updateCustomerRequest);
    }

    @DeleteMapping("/delete/{customerId}")
    public Result delete(@Valid @PathVariable String customerId) {
        return this.customerService.delete(customerId);
    }

    @GetMapping("/getbyid/{customerId}")
    public DataResult<CustomerListResponse> getById(@PathVariable String customerId) {
        return this.customerService.getById(customerId);
    }

    @GetMapping("/getByPage/{pageNumber}/{customerAmountInPage}")
    public PageDataResponse<CustomerListResponse> getByPage(int pageNumber, int customerAmountInPage){
        return this.customerService.getByPage(pageNumber,customerAmountInPage);
    }
    
    @GetMapping("/getByPageWithSorting/{pageNumber}/{customerAmountInPage}/{fieldName}/{isAsc}")
    public PageDataResponse<CustomerListResponse> getByPageWithSorting(int pageNumber, int customerAmountInPage, String fieldName, boolean isAsc) {
        return this.customerService.getByPageWithSorting(pageNumber, customerAmountInPage, fieldName, isAsc);
    }


}
