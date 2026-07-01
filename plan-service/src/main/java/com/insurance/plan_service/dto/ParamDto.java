package com.insurance.plan_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParamDto {

    private String paramId;

    private String benefitCode;

    private String changedParam;

    private String label;

    private String category;

    private String dataType;

    private Object currentValue;

    private Object minValue;

    private Object maxValue;

    private String unit;

    private String limitPeriod;

    private Boolean editable;

}