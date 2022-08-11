package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.productRequests.CreateProductRequest;
import com.etiya.northwind.business.requests.productRequests.UpdateProductRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;

import javax.xml.crypto.Data;
import java.util.List;

public interface ProductService {
    Result add(CreateProductRequest createProductRequest);
    Result update(UpdateProductRequest updateProductRequest);
    Result delete(int productId);
    DataResult<List<ProductListResponse>> getAll();
    DataResult<ProductListResponse> getById(int productId);

    PageDataResponse<ProductListResponse> getByPage(int pageNumber, int orderAmountInPage);

    PageDataResponse<ProductListResponse> getByPageWithSorting(int pageNumber, int orderAmountInPage, String fieldName, boolean isAsc);


}
