package com.ies.repositories;

import com.ies.entities.PlansEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlansRepo extends JpaRepository<Integer, PlansEntitiy> {
}
