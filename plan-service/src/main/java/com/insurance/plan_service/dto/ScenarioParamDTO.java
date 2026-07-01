package com.insurance.plan_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioParamDTO {

    private String benefitCode;

    private String changedParam;

    private Object oldValue;

    private Object newValue;

}