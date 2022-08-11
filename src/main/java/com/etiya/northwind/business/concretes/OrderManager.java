package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.requests.orderDetailRequests.CreateOrderDetailInOrderRequest;
import com.etiya.northwind.business.requests.orderRequests.CreateOrderRequest;
import com.etiya.northwind.business.requests.orderRequests.OrderRequest;
import com.etiya.northwind.business.requests.orderRequests.UpdateOrderRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.*;
import com.etiya.northwind.dataAccess.abstracts.*;
import com.etiya.northwind.entities.concretes.Order;
import com.etiya.northwind.entities.concretes.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {
    private OrderRepository orderRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private ModelMapperService modelMapperService;


    @Autowired
    public OrderManager(OrderRepository orderRepository, ModelMapperService modelMapperService, OrderDetailsRepository orderDetailsRepository) {
        this.orderRepository = orderRepository;
        this.modelMapperService = modelMapperService;
        this.orderDetailsRepository = orderDetailsRepository;
    }


    @Override
    public Result add(CreateOrderRequest createOrderRequest) {
        Order order = this.modelMapperService.forRequest().map(createOrderRequest, Order.class);
        orderRepository.save(order);
        saveOrderDetails(createOrderRequest, order);
        return new SuccessResult();
    }

    @Override
    public Result update(UpdateOrderRequest updateOrderRequest) {
        Order order = this.modelMapperService.forRequest().map(updateOrderRequest, Order.class);
        orderRepository.save(order);
        saveOrderDetails(updateOrderRequest, order);
        return new SuccessResult();
    }

    @Override
    public Result delete(int orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return new SuccessResult();
        } else {
            return new ErrorResult();
        }
    }

    @Override
    public DataResult<List<OrderListResponse>> getAll() {
        List<Order> result = this.orderRepository.findAll();
        List<OrderListResponse> response =
                result.stream().map(order -> this.modelMapperService.forResponse().map(order, OrderListResponse.class)).collect(Collectors.toList());

        for (int i = 0; i < result.size(); i++) {
            response.get(i).setEmployeeName(result.get(i).getEmployee().getFirstName() + " " + result.get(i).getEmployee().getLastName());
        }
        return new SuccessDataResult<List<OrderListResponse>>(response);
    }

    @Override
    public DataResult<OrderListResponse> getById(int orderId) {
        OrderListResponse response = new OrderListResponse();
        if (this.orderRepository.existsById(orderId)) {
            Order order = this.orderRepository.findById(orderId).get();
            response = this.modelMapperService.forResponse().map(order, OrderListResponse.class);
            response.setEmployeeName(order.getEmployee().getFirstName() + " " + order.getEmployee().getLastName());
            return new SuccessDataResult<OrderListResponse>(response);
        } else {
            return new ErrorDataResult<OrderListResponse>(response);
        }

    }

    @Override
    public PageDataResponse<OrderListResponse> getByPage(int pageNumber, int orderAmountInPage) {
        Pageable pageable = PageRequest.of(pageNumber - 1, orderAmountInPage);
        Page<Order> pages = this.orderRepository.findAllOrders(pageable);
        List<OrderListResponse> response =
                pages.getContent().stream().map(order -> this.modelMapperService.forResponse().map(order, OrderListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<OrderListResponse>(response, pages.getTotalPages(), pages.getTotalElements(), pageNumber);
    }

    @Override
    public PageDataResponse<OrderListResponse> getByPageWithSorting(int pageNumber, int orderAmountInPage, String fieldName, boolean isAsc) {
        Pageable pageable;
        if (isAsc) {
            pageable = PageRequest.of(pageNumber - 1, orderAmountInPage, Sort.by(fieldName).ascending());
        } else {
            pageable = PageRequest.of(pageNumber - 1, orderAmountInPage, Sort.by(fieldName).descending());
        }
        Page<Order> pages = this.orderRepository.findAllOrders(pageable);
        List<OrderListResponse> response =
                pages.getContent().stream().map(order -> this.modelMapperService.forResponse().map(order, OrderListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<OrderListResponse>(response, pages.getTotalPages(), pages.getTotalElements(), pageNumber);
    }

    private void saveOrderDetails(OrderRequest orderRequest, Order order) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (CreateOrderDetailInOrderRequest detailRequest : orderRequest.getOrderDetailRequests()) {
            OrderDetails orderDetails = this.modelMapperService.forRequest().map(detailRequest, OrderDetails.class);
            orderDetails.setOrderId(order.getOrderId());
            orderDetails.setOrder(order);
            orderDetailsRepository.save(orderDetails);
            orderDetailsList.add(orderDetails);
        }
        order.setOrderDetails(orderDetailsList);
    }
}

