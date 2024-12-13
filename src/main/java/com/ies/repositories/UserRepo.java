package com.ies.repositories;

import com.ies.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Integer, UserEntity> {
}
