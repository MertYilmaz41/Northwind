package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CategoryService;
import com.etiya.northwind.business.requests.categoryRequests.CreateCategoryRequest;
import com.etiya.northwind.business.requests.categoryRequests.UpdateCategoryRequest;
import com.etiya.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.*;
import com.etiya.northwind.dataAccess.abstracts.CategoryRepository;
import com.etiya.northwind.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryManager implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository, ModelMapperService modelMapperService) {
        this.categoryRepository = categoryRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CreateCategoryRequest createCategoryRequest) {
        checkIfCategoryNameExists(createCategoryRequest.getCategoryName());
        Category category = this.modelMapperService.forRequest().map(createCategoryRequest, Category.class);
        this.categoryRepository.save(category);
        return  new SuccessResult();
    }

    @Override
    public Result update(UpdateCategoryRequest updateCategoryRequest) {
        Category category = this.modelMapperService.forRequest().map(updateCategoryRequest, Category.class);
        this.categoryRepository.save(category);
        return new SuccessResult();
    }

    @Override
    public Result delete(int categoryId) {

        if(this.categoryRepository.existsById(categoryId)){
            this.categoryRepository.deleteById(categoryId);
            return new SuccessResult();
        }
        else{
            return  new ErrorResult();
        }

    }

    @Override
    public DataResult<List<CategoryListResponse>> getAll() {
        List<Category> result = this.categoryRepository.findAll();
        List<CategoryListResponse> response =
                result.stream().map(category -> this.modelMapperService.forResponse().map(category, CategoryListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<CategoryListResponse>>(response);
    }

    @Override
    public DataResult<CategoryListResponse> getById(int categoryId) {
        
        CategoryListResponse response = new CategoryListResponse();
        if(this.categoryRepository.existsById(categoryId)){
          Category category = this.categoryRepository.findById(categoryId).get();
          response = modelMapperService.forResponse().map(category, CategoryListResponse.class);
          return new SuccessDataResult<CategoryListResponse>(response);
        }
        else{
            System.out.println("Geçersiz Kategori ID");
            return new ErrorDataResult<CategoryListResponse>(response);
        }

    }

    @Override
    public PageDataResponse<CategoryListResponse> getByPage(int pageNumber, int categoryAmountInPage) {
        Pageable pageable = PageRequest.of(pageNumber-1,categoryAmountInPage);
        Page<Category> pages = this.categoryRepository.findAllCategories(pageable);
        List<CategoryListResponse> response =
                pages.getContent().stream().map(category -> this.modelMapperService.forResponse().map(category, CategoryListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<CategoryListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }

    @Override
    public PageDataResponse<CategoryListResponse> getByPageWithSorting(int pageNumber, int categoryAmountInPage, String fieldName, boolean isAsc) {
        if (Arrays.stream(Category.class.getDeclaredFields()).noneMatch(field -> field.getName().equals(fieldName))){
            System.out.println("Field does not exist.");
            return new PageDataResponse<>(new ArrayList<CategoryListResponse>(), 0, 0, 0);
        }
        Pageable pageable;
        if (isAsc){
            pageable = PageRequest.of(pageNumber-1,categoryAmountInPage, Sort.by(fieldName).ascending());
        }else {
            pageable = PageRequest.of(pageNumber-1,categoryAmountInPage, Sort.by(fieldName).descending());
        }
        Page<Category> pages = this.categoryRepository.findAllCategories(pageable);
        List<CategoryListResponse> response =
                pages.getContent().stream().map(category -> this.modelMapperService.forResponse().map(category, CategoryListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<CategoryListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }

    private void checkIfCategoryNameExists(String categoryName)
    {
        Category category = this.categoryRepository.findByCategoryName(categoryName);
        if (category!=null)
        {
            throw new BusinessException("Category name exists");
        }
    }

}
