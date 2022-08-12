package com.etiya.northwind.business.requests.customerRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {

    @NotNull
    @NotBlank
    private String customerId;

    @NotNull
    @NotBlank
    private String companyName;

    @NotNull
    @NotBlank
    private String contactName;

    @NotNull
    @NotBlank
    private String contactTitle;

    private int cityId;
    private int countryId;
}
