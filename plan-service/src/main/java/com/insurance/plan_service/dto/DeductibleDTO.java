package com.insurance.plan_service.dto;

import lombok.Data;

@Data
public class DeductibleDTO {

    private Double individual;

    private Double family;

    private String unit;
}