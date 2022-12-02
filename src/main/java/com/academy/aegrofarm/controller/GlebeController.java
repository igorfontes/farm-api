package com.academy.aegrofarm.controller;

import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.service.GlebeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class GlebeController {

    @Autowired
    private final GlebeService glebeService;

    public GlebeController(GlebeService glebeService) {
        this.glebeService = glebeService;
    }

    @GetMapping("/glebes")
    public List<Glebe> getGlebes(){
        return glebeService.getGlebes();
    }

    @GetMapping("/glebe/{id}")
    public Glebe getGlebeById(@PathVariable String id){
        return glebeService.getGlebeById(id);
    }

    @PostMapping("/farms/{farmId}/glebe/")
    public ResponseEntity addGlebe(@PathVariable("farmId") String farmId, @RequestBody Glebe glebe){
        glebeService.addGlebe(farmId, glebe);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/farms/{farmId}/glebe/{glebeId}")
    public ResponseEntity updateGlebe(@PathVariable("farmId") String farmId,
                                   @PathVariable("glebeId") String glebeId,
                                   @RequestBody Glebe glebe){
        Glebe updatedGlebe = glebeService.updateGlebe(farmId, glebeId, glebe);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/farms/{farmId}/glebe/{glebeId}")
    public ResponseEntity deleteGlebe(@PathVariable("farmId") String farmId,
                                      @PathVariable("glebeId") String glebeId){
        glebeService.deleteGlebe(farmId, glebeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
