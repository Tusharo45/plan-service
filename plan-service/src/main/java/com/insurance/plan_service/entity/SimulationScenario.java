package com.insurance.plan_service.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "simulation_scenario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulationScenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scenario_id")
    private Long scenarioId;

    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "baseline_version")
    private String baselineVersion;

    private String label;

    @Column(name = "is_user_defined")
    private Boolean isUserDefined;

    @Column(name = "parent_scenario_id")
    private Long parentScenarioId;

    @Column(name = "ai_rationale")
    private String aiRationale;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "scenario",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<SimulationScenarioParam> params;

}