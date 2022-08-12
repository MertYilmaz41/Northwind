package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.cartRequests.CreateCartRequest;
import com.etiya.northwind.business.requests.cartRequests.UpdateCartRequest;
import com.etiya.northwind.business.responses.carts.CartListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;

import java.util.List;

public interface CartService {

    Result add(CreateCartRequest createCartRequest);

    Result update(UpdateCartRequest updateCartRequest);

    Result delete(int cartId);

    DataResult<List<CartListResponse>> getAll();

    DataResult<CartListResponse> getById(int cartId);


}
