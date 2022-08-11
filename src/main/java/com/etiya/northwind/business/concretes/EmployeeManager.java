package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.EmployeeService;
import com.etiya.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.employees.EmployeeListResponse;
import com.etiya.northwind.core.exceptions.BusinessException;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.core.utilities.results.*;
import com.etiya.northwind.dataAccess.abstracts.EmployeeRepository;
import com.etiya.northwind.entities.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository, ModelMapperService modelMapperService) {
        this.employeeRepository = employeeRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public Result add(CreateEmployeeRequest createEmployeeRequest) {
        checkIfEmployeeReportsToLimitExceeds(createEmployeeRequest.getReportToId());
        Employee employee = this.modelMapperService.forRequest().map(createEmployeeRequest, Employee.class);
        employeeRepository.save(employee);
        return new SuccessResult("employee.added");
    }

    @Override
    public Result update(UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = this.modelMapperService.forRequest().map(updateEmployeeRequest, Employee.class);
        employeeRepository.save(employee);
        return new SuccessResult("employee.updated");
    }

    @Override
    public Result delete(int employeeId) {

        if(employeeRepository.existsById(employeeId)){
            employeeRepository.deleteById(employeeId);
            return new SuccessResult();
        }
        else{
            return new ErrorResult();
        }
    }

    @Override
    public DataResult<List<EmployeeListResponse>> getAll() {
        List<Employee> result = this.employeeRepository.findAll();
        List<EmployeeListResponse> response =
                result.stream().map(employee -> this.modelMapperService.forResponse().map(employee, EmployeeListResponse.class)).collect(Collectors.toList());

        return new SuccessDataResult<List<EmployeeListResponse>>(response);
    }

    @Override
    public DataResult<EmployeeListResponse> getById(int employeeId) {
        EmployeeListResponse response = new EmployeeListResponse();
        if (this.employeeRepository.existsById(employeeId)){
            Employee employee = this.employeeRepository.findById(employeeId).get();
            response = this.modelMapperService.forResponse().map(employee, EmployeeListResponse.class);
            return new SuccessDataResult<EmployeeListResponse>(response);
        }
        else{
           return new ErrorDataResult<EmployeeListResponse>(response);
        }

    }

    @Override
    public PageDataResponse<EmployeeListResponse> getByPage(int pageNumber, int employeeAmountInPage) {
        Pageable pageable = PageRequest.of(pageNumber-1,employeeAmountInPage);
        Page<Employee> pages = this.employeeRepository.findAllEmployees(pageable);
        List<EmployeeListResponse> response =
                pages.getContent().stream().map(employee -> this.modelMapperService.forResponse().map(employee, EmployeeListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<EmployeeListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }

    @Override
    public PageDataResponse<EmployeeListResponse> getByPageWithSorting(int pageNumber, int employeeAmountInPage, String fieldName, boolean isAsc) {
        Pageable pageable;
        if (isAsc){
            pageable = PageRequest.of(pageNumber-1,employeeAmountInPage, Sort.by(fieldName).ascending());
        }else {
            pageable = PageRequest.of(pageNumber-1,employeeAmountInPage, Sort.by(fieldName).descending());
        }
        Page<Employee> pages = this.employeeRepository.findAllEmployees(pageable);
        List<EmployeeListResponse> response =
                pages.getContent().stream().map(employee -> this.modelMapperService.forResponse().map(employee, EmployeeListResponse.class)).collect(Collectors.toList());

        return new PageDataResponse<EmployeeListResponse>(response,pages.getTotalPages(),pages.getTotalElements(), pageNumber);
    }

    private void checkIfEmployeeReportsToLimitExceeds(Integer reportsToId)
    {
        List<Employee> result = this.employeeRepository.findByReportsTo(reportsToId);
        if (result.size()>=10)
        {
            throw  new BusinessException("employee.limit.exceed");
        }
    }
}
