package com.academy.aegrofarm.controller;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/farms")
public class FarmController {

    @Autowired
    private final FarmService farmService;

    public FarmController(FarmService farmService) {
        this.farmService = farmService;
    }

    @GetMapping
    public List<Farm> getFarms() {
        return farmService.getFarms();
    }

    @GetMapping("/{id}")
    public Farm getFarmById(@PathVariable String id){
        return farmService.getFarmById(id);
    }

    @PostMapping
    public ResponseEntity addFarm(@RequestBody Farm farm){
        farmService.addFarm(farm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFarm(@PathVariable("id") String id,@RequestBody Farm farm){
        Farm updatedFarm = farmService.updateFarm(id, farm);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}/productivity")
    public ResponseEntity updateProductivity(@PathVariable("id") String id){
        farmService.updateProductivity(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFarm(@PathVariable("id") String id){
        farmService.deleteFarm(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
