package com.etiya.northwind.business.requests.supplierRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSupplierRequest {

    @NotNull
    @Positive
    private int supplierId;

    @NotNull
    @NotBlank
    private String companyName;

    @NotNull
    @NotBlank
    private String contactName;

    @NotNull
    @NotBlank
    private String contactTitle;

}
