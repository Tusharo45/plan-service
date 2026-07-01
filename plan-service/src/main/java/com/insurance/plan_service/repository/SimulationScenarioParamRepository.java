package com.insurance.plan_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.plan_service.entity.SimulationScenario;
import com.insurance.plan_service.entity.SimulationScenarioParam;

@Repository
public interface SimulationScenarioParamRepository
        extends JpaRepository<SimulationScenarioParam, Long> {

    List<SimulationScenarioParam> findByScenario(
            SimulationScenario scenario);

}