package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.categoryRequests.CreateCategoryRequest;
import com.etiya.northwind.business.requests.categoryRequests.UpdateCategoryRequest;
import com.etiya.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import com.etiya.northwind.entities.concretes.Category;


import java.util.List;

public interface CategoryService {

    Result add(CreateCategoryRequest createCategoryRequest);

    Result update(UpdateCategoryRequest updateCategoryRequest);

    Result delete(int categoryId);

    DataResult<List<CategoryListResponse>> getAll();

    DataResult<CategoryListResponse> getById(int categoryId);

    PageDataResponse<CategoryListResponse> getByPage(int pageNumber, int categoryAmountInPage);

    PageDataResponse<CategoryListResponse> getByPageWithSorting(int pageNumber, int categoryAmountInPage, String fieldName, boolean isAsc);




}
