package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.EmployeeService;
import com.etiya.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.employees.EmployeeListResponse;
import com.etiya.northwind.core.utilities.results.DataResult;
import com.etiya.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getall")
    public DataResult<List<EmployeeListResponse>> getAll(){
        return this.employeeService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest){
       return this.employeeService.add(createEmployeeRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest){
       return this.employeeService.update(updateEmployeeRequest);
    }

    @DeleteMapping("/delete/{employeeId}")
    public Result delete(int employeeId){
        return this.employeeService.delete(employeeId);
    }

    @GetMapping("/getbyid/{employeeId}")
    public DataResult<EmployeeListResponse> getById(int employeeId){
        return this.employeeService.getById(employeeId);
    }

    @GetMapping("/getByPage/{pageNumber}/{employeeAmountInPage}")
    public PageDataResponse<EmployeeListResponse> getByPage(int pageNumber, int employeeAmountInPage){
        return this.employeeService.getByPage(pageNumber,employeeAmountInPage);
    }

    @GetMapping("/getByPageWithSorting/{pageNumber}/{employeeAmountInPage}/{fieldName}/{isAsc}")
    public PageDataResponse<EmployeeListResponse> getByPageWithSorting(int pageNumber, int employeeAmountInPage, String fieldName, boolean isAsc){
        return this.employeeService.getByPageWithSorting(pageNumber,employeeAmountInPage,fieldName,isAsc);
    }
}
