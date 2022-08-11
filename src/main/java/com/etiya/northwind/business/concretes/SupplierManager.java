package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.SupplierService;
import com.etiya.northwind.business.requests.supplierRequests.CreateSupplierRequest;
import com.etiya.northwind.business.requests.supplierRequests.UpdateSupplierRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.suppliers.SupplierListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.*;
import com.etiya.northwind.dataAccess.abstracts.SupplierRepository;
import com.etiya.northwind.entities.concretes.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierManager implements SupplierService {
    private SupplierRepository supplierRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public SupplierManager(SupplierRepository supplierRepository, ModelMapperService modelMapperService) {
        this.supplierRepository = supplierRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CreateSupplierRequest createSupplierRequest) {
        Supplier supplier = this.modelMapperService.forRequest().map(createSupplierRequest, Supplier.class);
        supplierRepository.save(supplier);
        return new SuccessResult();
    }

    @Override
    public Result update(UpdateSupplierRequest updateSupplierRequest) {
        Supplier supplier = this.modelMapperService.forRequest().map(updateSupplierRequest, Supplier.class);
        supplierRepository.save(supplier);
        return new SuccessResult();
    }

    @Override
    public Result delete(int supplierId) {
        if (this.supplierRepository.existsById(supplierId)){
            this.supplierRepository.deleteById(supplierId);
            return new SuccessResult();
        }else{
           return new ErrorResult();
        }
    }

    @Override
    public DataResult<List<SupplierListResponse>> getAll() {
        List<Supplier> result = this.supplierRepository.findAll();
        List<SupplierListResponse> response = result.stream()
                .map(supplier -> this.modelMapperService.forResponse().map(supplier, SupplierListResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<SupplierListResponse>>(response);
    }

    @Override
    public DataResult<SupplierListResponse> getById(int supplierId) {
        SupplierListResponse response = new SupplierListResponse();
        if (this.supplierRepository.existsById(supplierId)){
            Supplier supplier = this.supplierRepository.findById(supplierId).get();
            response = this.modelMapperService.forResponse().map(supplier, SupplierListResponse.class);
            return new SuccessDataResult<SupplierListResponse>(response);
        }
        else{
            return new ErrorDataResult<SupplierListResponse>(response);
        }

    }

    @Override
    public PageDataResponse<SupplierListResponse> getByPage(int pageNumber, int supplierAmountInPage) {
        Pageable pageable = PageRequest.of(pageNumber-1,supplierAmountInPage);
        Page<Supplier> pages = this.supplierRepository.findAllSuppliers(pageable);
        List<SupplierListResponse> response =
                pages.getContent().stream().map(supplier -> this.modelMapperService.forResponse().map(supplier, SupplierListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<SupplierListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }

    @Override
    public PageDataResponse<SupplierListResponse> getByPageWithSorting(int pageNumber, int supplierAmountInPage, String fieldName, boolean isAsc) {
        Pageable pageable;
        if (isAsc){
            pageable = PageRequest.of(pageNumber-1,supplierAmountInPage, Sort.by(fieldName).ascending());
        }else {
            pageable = PageRequest.of(pageNumber-1,supplierAmountInPage, Sort.by(fieldName).descending());
        }
        Page<Supplier> pages = this.supplierRepository.findAllSuppliers(pageable);
        List<SupplierListResponse> response =
                pages.getContent().stream().map(supplier -> this.modelMapperService.forResponse().map(supplier, SupplierListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<SupplierListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }
}

