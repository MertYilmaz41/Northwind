package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CartService;
import com.etiya.northwind.business.requests.cartProductRequests.CreateCartProductInCartRequest;
import com.etiya.northwind.business.requests.cartRequests.CartRequest;
import com.etiya.northwind.business.requests.cartRequests.CreateCartRequest;
import com.etiya.northwind.business.requests.cartRequests.UpdateCartRequest;
import com.etiya.northwind.business.responses.carts.CartListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.*;
import com.etiya.northwind.dataAccess.abstracts.CartProductRepository;
import com.etiya.northwind.dataAccess.abstracts.CartRepository;
import com.etiya.northwind.entities.concretes.Cart;
import com.etiya.northwind.entities.concretes.CartProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartManager implements CartService {
    private CartRepository cartRepository;
    private ModelMapperService modelMapperService;
    private CartProductRepository cartProductRepository;

    @Autowired
    public CartManager(CartRepository cartRepository, ModelMapperService modelMapperService,CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.modelMapperService = modelMapperService;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public Result add(CreateCartRequest createCartRequest) {
        Cart cart = this.modelMapperService.forRequest().map(createCartRequest, Cart.class);
        cartRepository.save(cart);
        saveCartProducts(createCartRequest,cart);
        return  new SuccessResult();


    }

    @Override
    public Result update(UpdateCartRequest updateCartRequest) {
        Cart cart = this.modelMapperService.forRequest().map(updateCartRequest, Cart.class);
        this.cartRepository.save(cart);
        return new SuccessResult();
    }

    @Override
    public Result delete(int cartId) {
        if(this.cartRepository.existsById(cartId)){
            this.cartRepository.deleteById(cartId);
            return new SuccessResult();
        }
        else{
            return  new ErrorResult();
        }
    }

    @Override
    public DataResult<List<CartListResponse>> getAll() {
       List<Cart> result = this.cartRepository.findAll();
       List<CartListResponse> responses = result.stream().map(cart -> this.modelMapperService.forResponse()
               .map(cart,CartListResponse.class)).collect(Collectors.toList());
       return new SuccessDataResult<List<CartListResponse>>(responses);
    }

    @Override
    public DataResult<CartListResponse> getById(int cartId) {
        CartListResponse response = new CartListResponse();
        if(this.cartRepository.existsById(cartId)){
            Cart cart = this.cartRepository.findById(cartId).get();
            response = modelMapperService.forResponse().map(cart, CartListResponse.class);
            return new SuccessDataResult<CartListResponse>(response);
        }
        else{
            System.out.println("Geçersiz Sepet Id");
            return new ErrorDataResult<CartListResponse>(response);
        }
    }

    private void saveCartProducts(CartRequest cartRequest, Cart cart) {
        List<CartProduct> cartProductList = new ArrayList<>();
        for (CreateCartProductInCartRequest cartProductRequest : cartRequest.getCartProductRequests()) {
            CartProduct cartProduct = this.modelMapperService.forRequest().map(cartProductRequest, CartProduct.class);
            cartProduct.setCartProductId(cart.getCartId());
            cartProduct.setCart(cart);
            cartProductRepository.save(cartProduct);
            cartProductList.add(cartProduct);
        }
        cart.setCartProducts(cartProductList);
    }


}
