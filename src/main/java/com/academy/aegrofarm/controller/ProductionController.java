package com.academy.aegrofarm.controller;

import com.academy.aegrofarm.entity.Production;
import com.academy.aegrofarm.service.ProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ProductionController {

    @Autowired
    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping("/production/{id}")
    public Production getProductionById(@PathVariable String id){
        return productionService.getProductionById(id);
    }

    @PostMapping("/glebes/{glebeId}/production/")
    public ResponseEntity addProduction(@PathVariable("glebeId") String glebeId, @RequestBody Production production){
        productionService.addProduction(glebeId, production);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/glebes/{glebeId}/production/{productionId}")
    public ResponseEntity updateProduction(@PathVariable("glebeId") String glebeId,
                                      @PathVariable("productionId") String productionId,
                                      @RequestBody Production production){
        Production updatedProduction = productionService.updateProduction(glebeId, productionId, production);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/glebes/{glebeId}/production/{productionId}")
    public ResponseEntity deleteProduction(@PathVariable("glebeId") String glebeId,
                                      @PathVariable("productionId") String productionId){
        productionService.deleteProduction(glebeId, productionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
