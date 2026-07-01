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
public class GenerateScenarioResponseDTO {

    private Long planId;

    private List<ScenarioDTO> scenarios;

}