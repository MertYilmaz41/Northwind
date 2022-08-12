package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.cartProductRequests.CreateCartProductRequest;
import com.etiya.northwind.business.requests.cartProductRequests.UpdateCartProductRequest;
import com.etiya.northwind.business.requests.cartRequests.CreateCartRequest;
import com.etiya.northwind.business.requests.cartRequests.UpdateCartRequest;
import com.etiya.northwind.business.responses.cartProducts.CartProductListResponse;
import com.etiya.northwind.business.responses.carts.CartListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;

import java.util.List;

public interface CartProductService {

    Result add(CreateCartProductRequest createCartProductRequest);

    Result update(UpdateCartProductRequest updateCartProductRequest);

    Result delete(int cartProductId);

    DataResult<List<CartProductListResponse>> getAll();

    DataResult<CartProductListResponse> getById(int cartProductId);
}
