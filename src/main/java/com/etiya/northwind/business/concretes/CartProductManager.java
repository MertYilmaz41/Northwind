package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CartProductService;
import com.etiya.northwind.business.requests.cartProductRequests.CreateCartProductRequest;
import com.etiya.northwind.business.requests.cartProductRequests.UpdateCartProductRequest;
import com.etiya.northwind.business.responses.cartProducts.CartProductListResponse;
import com.etiya.northwind.business.responses.carts.CartListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.*;
import com.etiya.northwind.dataAccess.abstracts.CartProductRepository;
import com.etiya.northwind.entities.concretes.Cart;
import com.etiya.northwind.entities.concretes.CartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartProductManager implements CartProductService {
    CartProductRepository cartProductRepository;
    ModelMapperService modelMapperService;

    @Autowired
    public CartProductManager(CartProductRepository cartProductRepository, ModelMapperService modelMapperService) {
        this.cartProductRepository = cartProductRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateCartProductRequest createCartProductRequest) {
        CartProduct cartProduct = this.modelMapperService.forRequest().map(createCartProductRequest, CartProduct.class);
        this.cartProductRepository.save(cartProduct);
        return  new SuccessResult();
    }

    @Override
    public Result update(UpdateCartProductRequest updateCartProductRequest) {
        CartProduct cartProduct = this.modelMapperService.forRequest().map(updateCartProductRequest, CartProduct.class);
        this.cartProductRepository.save(cartProduct);
        return new SuccessResult();
    }

    @Override
    public Result delete(int cartProductId) {
        if(this.cartProductRepository.existsById(cartProductId)){
            this.cartProductRepository.deleteById(cartProductId);
            return new SuccessResult();
        }
        else{
            return  new ErrorResult();
        }
    }

    @Override
    public DataResult<List<CartProductListResponse>> getAll() {
        List<CartProduct> result = this.cartProductRepository.findAll();
        List<CartProductListResponse> responses = result.stream().map(cartProduct -> this.modelMapperService.forResponse()
                .map(cartProduct,CartProductListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CartProductListResponse>>(responses);
    }

    @Override
    public DataResult<CartProductListResponse> getById(int cartProductId) {
        CartProductListResponse response = new CartProductListResponse();
        if(this.cartProductRepository.existsById(cartProductId)){
            CartProduct cartProduct = this.cartProductRepository.findById(cartProductId).get();
            response = modelMapperService.forResponse().map(cartProduct, CartProductListResponse.class);
            return new SuccessDataResult<CartProductListResponse>(response);
        }
        else{
            System.out.println("Geçersiz Sepet Id");
            return new ErrorDataResult<CartProductListResponse>(response);
        }
    }
}
