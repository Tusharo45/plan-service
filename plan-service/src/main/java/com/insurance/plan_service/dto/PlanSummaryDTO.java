package com.insurance.plan_service.dto;

import lombok.Data;

@Data
public class PlanSummaryDTO {

    private String planId;

    private String planName;

    private String lineOfBusiness;

    private String planType;

    private String status;
}