package com.soechi.test.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.soechi.test.models.entities.Units;
import com.soechi.test.models.repos.UnitsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UnitsServices {
    
    @Autowired
    private UnitsRepo unitsRepo;

    public Units save(Units units) {
        return unitsRepo.save(units);
    }

    public Units findById(Long id) {
        Optional<Units> temp = unitsRepo.findById(id);
        if(!temp.isPresent()) {
            return null;
        }
        return temp.get();
    }

    public Iterable<Units> findAll() {
        return unitsRepo.findAll();
    }

    public void remove(Long id) {
        unitsRepo.deleteById(id);
    }

    public Units findByNameUnit(String nameUnit) {
        return unitsRepo.findByNameUnit(nameUnit);
    }
}
