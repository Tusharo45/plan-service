package com.insurance.plan_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.plan_service.dto.AIScenarioResponseDTO;
import com.insurance.plan_service.dto.GenerateScenarioRequestDTO;
import com.insurance.plan_service.dto.OpenRouterMessageDTO;
import com.insurance.plan_service.dto.OpenRouterRequestDTO;
import com.insurance.plan_service.dto.OpenRouterResponseDTO;
import com.insurance.plan_service.dto.ScenarioParamDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AIScenarioGenerator {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${openrouter.model}")
    private String model;

    public AIScenarioResponseDTO generateScenarios(
        GenerateScenarioRequestDTO request) {

    String prompt = buildPrompt(request);

    OpenRouterRequestDTO aiRequest =
            OpenRouterRequestDTO.builder()
                    .model(model)
                    .messages(List.of(
                            OpenRouterMessageDTO.builder()
                                    .role("user")
                                    .content(prompt)
                                    .build()))
                    .build();

    OpenRouterResponseDTO response =
            restClient.post()
                    .uri("/chat/completions")
                    .header(HttpHeaders.AUTHORIZATION,
                            "Bearer " + apiKey)
                    .header("HTTP-Referer",
                            "http://localhost:8080")
                    .header("X-Title",
                            "Insurance Plan Service")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(aiRequest)
                    .retrieve()
                    .body(OpenRouterResponseDTO.class);

    if (response == null
            || response.getChoices() == null
            || response.getChoices().isEmpty()) {

        throw new RuntimeException("No response received from OpenRouter");
    }

    try {

        String aiResponse = response.getChoices()
                .get(0)
                .getMessage()
                .getContent();

        // Print the complete AI response
        System.out.println("\n========== AI RESPONSE ==========");
        System.out.println(aiResponse);
        System.out.println("=================================\n");

        // Remove markdown if AI returns ```json
       aiResponse = aiResponse
        .replace("```json", "")
        .replace("```", "")
        .trim();

System.out.println("===== CLEAN JSON =====");
System.out.println(aiResponse);
System.out.println("======================");
try {

    AIScenarioResponseDTO dto =
            objectMapper.readValue(aiResponse, AIScenarioResponseDTO.class);

    System.out.println("DTO parsed successfully!");
    return dto;

} catch (Exception ex) {

    System.out.println("========== MAPPING ERROR ==========");
    ex.printStackTrace();
    System.out.println("===================================");

    throw new RuntimeException(ex);
}
    } catch (Exception e) {

    e.printStackTrace();

    throw new RuntimeException(e);
}
}


    private String buildPrompt(
            GenerateScenarioRequestDTO request) {

        StringBuilder prompt = new StringBuilder();

        prompt.append("""
You are an expert Health Insurance Consultant.

The user modified an insurance plan.

Generate EXACTLY TWO alternative scenarios.

Return ONLY valid JSON.

Do not use markdown.

Do not explain anything.

The JSON MUST exactly match this schema:

{
  "scenarios":[
    {
      "label":"",
      "aiRationale":"",
      "params":[
        {
          "benefitCode":"",
          "changedParam":"",
          "oldValue":"",
          "newValue":""
        }
      ]
    }
  ]
}

Use EXACT property names:

benefitCode
changedParam
oldValue
newValue
label
aiRationale
params

User Changes:

""");

        for (ScenarioParamDTO edit : request.getUserEdits()) {

            prompt.append("Benefit Code: ")
                    .append(edit.getBenefitCode())
                    .append("\n");

            prompt.append("Changed Parameter: ")
                    .append(edit.getChangedParam())
                    .append("\n");

            prompt.append("Old Value: ")
                    .append(edit.getOldValue())
                    .append("\n");

            prompt.append("New Value: ")
                    .append(edit.getNewValue())
                    .append("\n\n");
        }

        return prompt.toString();
    }
}