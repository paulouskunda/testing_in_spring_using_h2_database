package com.paulous.repository;

import com.paulous.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
    Optional<UsersEntity> findByUsername(String username);
}
