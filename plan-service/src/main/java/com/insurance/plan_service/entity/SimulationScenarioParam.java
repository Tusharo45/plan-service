package com.insurance.plan_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "simulation_scenario_param")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulationScenarioParam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id")
    private SimulationScenario scenario;

    @Column(name = "benefit_code")
    private String benefitCode;

    @Column(name = "changed_param")
    private String changedParam;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

}