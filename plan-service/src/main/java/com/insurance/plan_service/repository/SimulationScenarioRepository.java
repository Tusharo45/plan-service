package com.insurance.plan_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.plan_service.entity.SimulationScenario;

@Repository
public interface SimulationScenarioRepository
        extends JpaRepository<SimulationScenario, Long> {

    List<SimulationScenario> findByPlanId(Long planId);

}