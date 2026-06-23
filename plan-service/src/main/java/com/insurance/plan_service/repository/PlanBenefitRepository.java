package com.insurance.plan_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.plan_service.entity.PlanBenefit;

@Repository
public interface PlanBenefitRepository
        extends JpaRepository<PlanBenefit, Long> {

    List<PlanBenefit> findByPlanPlanId(Long planId);
}