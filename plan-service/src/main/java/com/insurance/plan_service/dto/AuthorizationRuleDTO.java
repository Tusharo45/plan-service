package com.insurance.plan_service.dto;

import lombok.Data;

@Data
public class AuthorizationRuleDTO {

    private String serviceType;

    private Boolean priorAuthRequired;
}