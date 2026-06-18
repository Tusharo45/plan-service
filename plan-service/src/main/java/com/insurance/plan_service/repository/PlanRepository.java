package com.insurance.plan_service.repository;

import com.insurance.plan_service.entity.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, String> {

    Page<Plan> findByLineOfBusinessAndPlanTypeAndStatus(
            String lineOfBusiness,
            String planType,
            String status,
            Pageable pageable);
}