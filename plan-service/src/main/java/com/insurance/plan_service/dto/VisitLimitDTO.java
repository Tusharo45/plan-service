package com.insurance.plan_service.dto;

import lombok.Data;

@Data
public class VisitLimitDTO {

    private String serviceType;

    private Integer limit;

    private String unit;
}