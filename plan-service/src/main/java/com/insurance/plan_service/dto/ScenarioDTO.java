package com.insurance.plan_service.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioDTO {

    private Long scenarioId;

    private String label;

    private Boolean isUserDefined;

    private Long parentScenarioId;

    private String aiRationale;

    private List<ScenarioParamDTO> params;

}