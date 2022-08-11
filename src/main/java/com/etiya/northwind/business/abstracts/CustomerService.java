package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.northwind.business.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;


import java.util.List;

public interface CustomerService {
    Result add(CreateCustomerRequest createCustomerRequest);
    Result update(UpdateCustomerRequest updateCustomerRequest);
    Result delete(String customerId);
    DataResult<List<CustomerListResponse>> getAll();
    DataResult<CustomerListResponse> getById(String customerId);

    PageDataResponse<CustomerListResponse> getByPage(int pageNumber, int customerAmountInPage);

    PageDataResponse<CustomerListResponse> getByPageWithSorting(int pageNumber, int customerAmountInPage, String fieldName, boolean isAsc);
}
