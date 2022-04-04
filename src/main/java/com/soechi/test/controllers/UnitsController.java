package com.soechi.test.controllers;

import com.soechi.test.dto.ResponseData;
import com.soechi.test.models.entities.Units;
import com.soechi.test.services.UnitsServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/units")
@CrossOrigin
public class UnitsController {
    
    @Autowired
    private UnitsServices unitsServices;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseData<Units>> create(@Validated @RequestBody Units units, @ApiIgnore Errors errors) {

        ResponseData<Units> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(unitsServices.save(units));
        return ResponseEntity.ok(responseData);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseData<Units>> update(@Validated @RequestBody Units units, @ApiIgnore Errors errors) {
        
        ResponseData<Units> responseData = new ResponseData<>();

        if(errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(unitsServices.save(units));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Units> findAll() {
        return unitsServices.findAll();
    }

    @GetMapping("/{id}")
    public Units findById(@PathVariable("id") Long id) {
        return unitsServices.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        unitsServices.remove(id);
    }
}
