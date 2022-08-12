package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.requests.productRequests.CreateProductRequest;
import com.etiya.northwind.business.requests.productRequests.UpdateProductRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.products.ProductListResponse;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.*;
import com.etiya.northwind.dataAccess.abstracts.ProductRepository;
import com.etiya.northwind.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService {
    private ProductRepository productRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public ProductManager(ProductRepository productRepository, ModelMapperService modelMapperService) {
        this.productRepository = productRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CreateProductRequest createProductRequest) {
        checkIfCategoryLimitExceeds(createProductRequest.getCategoryId());
        checkIfProductNameExists(createProductRequest.getProductName());
        Product product = this.modelMapperService.forRequest().map(createProductRequest, Product.class);
        productRepository.save(product);
        return new SuccessResult("Product added");
    }

    @Override
    public Result update(UpdateProductRequest updateProductRequest) {
        Product product = this.modelMapperService.forRequest().map(updateProductRequest, Product.class);
        productRepository.save(product);
        return new SuccessResult("Product updated");
    }

    @Override
    public Result delete(int productId) {
        if (this.productRepository.existsById(productId)){
            this.productRepository.deleteById(productId);
            return new SuccessResult("Product deleted");
        }else{
           return new ErrorResult();
        }

    }

    @Override
    public DataResult<List<ProductListResponse>> getAll() {
        List<Product> result = this.productRepository.findAll();
        List<ProductListResponse> response = result.stream()
                .map(product -> this.modelMapperService.forResponse()
                        .map(product, ProductListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ProductListResponse>>(response);

    }

    @Override
    public DataResult<ProductListResponse> getById(int productId) {
        ProductListResponse response = new ProductListResponse();
        if (this.productRepository.existsById(productId)){
            Product product = this.productRepository.findById(productId).get();
            response = this.modelMapperService.forResponse().map(product, ProductListResponse.class);
            return new SuccessDataResult<ProductListResponse>();
        }
        else{
            return new ErrorDataResult<ProductListResponse>();
        }

    }

    @Override
    public  PageDataResponse<ProductListResponse> getByPage(int pageNumber, int productAmountInPage) {
        Pageable pageable = PageRequest.of(pageNumber-1,productAmountInPage);
        Page<Product> pages = this.productRepository.findAllProducts(pageable);
        List<ProductListResponse> response =
                pages.getContent().stream().map(product -> this.modelMapperService.forResponse().map(product, ProductListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<ProductListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }

    @Override
    public PageDataResponse<ProductListResponse> getByPageWithSorting(int pageNumber, int productAmountInPage, String fieldName, boolean isAsc) {
        Pageable pageable;
        if (isAsc){
            pageable = PageRequest.of(pageNumber-1,productAmountInPage, Sort.by(fieldName).ascending());
        }else {
            pageable = PageRequest.of(pageNumber-1,productAmountInPage, Sort.by(fieldName).descending());
        }
        Page<Product> pages = this.productRepository.findAllProducts(pageable);
        List<ProductListResponse> response =
                pages.getContent().stream().map(product -> this.modelMapperService.forResponse().map(product, ProductListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<ProductListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }



        private void checkIfCategoryLimitExceeds(int categoryId)
        {
            List<Product> result = this.productRepository.findByCategory_CategoryId(categoryId);
            if(result.size()>15)
            {
                throw new BusinessException("Category limit exceeded");
            }
        }
        private void checkIfProductNameExists(String productName)
        {
            Product product = this.productRepository.findByProductName(productName);
            if (product!=null)
            {
                throw new BusinessException("Product name exists");
            }


        }

    }

