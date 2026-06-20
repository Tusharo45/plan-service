package com.insurance.plan_service.dto;

import lombok.Data;

@Data
public class CopayDTO {

    private String serviceType;

    private Double amount;

    private String unit;
}