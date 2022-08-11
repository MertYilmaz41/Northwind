package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.orderDetailRequests.CreateOrderDetailInOrderRequest;
import com.etiya.northwind.business.requests.orderDetailRequests.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetailRequests.UpdateOrderDetailRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailsListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.entities.concretes.OrderDetailsId;

import java.util.List;

public interface OrderDetailsService {
    Result add(CreateOrderDetailRequest createOrderDetailRequest);
    Result update(UpdateOrderDetailRequest updateOrderDetailRequest);
    Result delete(int orderId, int productId);
    DataResult<List<OrderDetailsListResponse>> getAll();
    DataResult<OrderDetailsListResponse> getById(OrderDetailsId orderDetailsId);

    PageDataResponse<OrderDetailsListResponse> getByPage(int pageNumber, int orderDetailsAmountInPage);

    PageDataResponse<OrderDetailsListResponse> getByPageWithSorting(int pageNumber, int orderDetailsAmountInPage, String fieldName, boolean isAsc);

    DataResult<List<OrderDetailsListResponse>> getOrderDetailsByProductId(int productId);
}
