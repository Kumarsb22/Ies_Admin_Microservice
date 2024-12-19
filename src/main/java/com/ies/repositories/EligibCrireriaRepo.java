package com.ies.repositories;

import com.ies.entities.EligCritiria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EligibCrireriaRepo extends JpaRepository<EligCritiria,Integer> {
}
