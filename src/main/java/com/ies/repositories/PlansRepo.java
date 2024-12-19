package com.ies.repositories;

import com.ies.entities.PlansEntitiy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PlansRepo extends JpaRepository<PlansEntitiy, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE PlanEntity p SET p.activeSw=:status WHERE p.planId=:planId")
    Integer updatePlansStatus(Integer planId, String status);
}
