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
public class OpenRouterRequestDTO {

    private String model;

    private List<OpenRouterMessageDTO> messages;

}