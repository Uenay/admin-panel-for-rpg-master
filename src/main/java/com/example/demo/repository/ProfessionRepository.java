package com.example.demo.repository;

import com.example.demo.entity.Player;
import com.example.demo.entity.ProfessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionRepository extends JpaRepository<ProfessionEntity, Long> {
    ProfessionEntity findByName(String name);
}
