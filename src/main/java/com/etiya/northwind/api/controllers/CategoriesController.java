package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.CategoryService;
import com.etiya.northwind.business.requests.categoryRequests.CreateCategoryRequest;
import com.etiya.northwind.business.requests.categoryRequests.UpdateCategoryRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getall")
    public DataResult<List<CategoryListResponse>> getAll(){
        return this.categoryService.getAll();
    }


    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateCategoryRequest createCategoryRequest){
       return this.categoryService.add(createCategoryRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest){
       return  this.categoryService.update(updateCategoryRequest);
    }

    @DeleteMapping("/delete/{categoryId}")
    public Result delete(@Valid @PathVariable int categoryId){
        return this.categoryService.delete(categoryId);
    }

    @GetMapping("/getbyid/{categoryId}")
    public  DataResult<CategoryListResponse> getById(@PathVariable int categoryId){
        return this.categoryService.getById(categoryId);
    }

    @GetMapping("/getByPage/{pageNumber}/{categoryAmountInPage}")
    public PageDataResponse<CategoryListResponse> getByPage(int pageNumber, int categoryAmountInPage){
        return this.categoryService.getByPage(pageNumber,categoryAmountInPage);
    }

    @GetMapping("/getByPageWithSorting/{pageNumber}/{categoryAmountInPage}/{fieldName}/{isAsc}")
    public PageDataResponse<CategoryListResponse> getByPageWithSorting(int pageNumber, int categoryAmountInPage, String fieldName, boolean isAsc){
        return this.categoryService.getByPageWithSorting(pageNumber,categoryAmountInPage,fieldName,isAsc);
    }


}