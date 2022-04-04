package com.soechi.test.models.repos;

import com.soechi.test.models.entities.Units;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitsRepo extends JpaRepository<Units, Long> {
    
    public Units findByNameUnit(String nameUnit);
}
