package com.ies.repositories;

import com.ies.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.activeSw=:status WHERE u.userId=:userId")
    public Integer updateAccountStatus(Integer userId, String status);

    UserEntity findByEmail(String email);

    UserEntity findByEmailAndPazzword(String email, String password);
}
