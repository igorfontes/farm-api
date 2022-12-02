package com.academy.aegrofarm.service;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.exception.ObjectNotFoundException;
import com.academy.aegrofarm.repository.FarmRepository;
import com.academy.aegrofarm.repository.GlebeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GlebeService {

    private final FarmRepository farmRepository;

    private final GlebeRepository glebeRepository;

    private final ProductionService productionService;

    public GlebeService(FarmRepository farmRepository, GlebeRepository glebeRepository, ProductionService productionService) {
        this.farmRepository = farmRepository;
        this.glebeRepository = glebeRepository;
        this.productionService = productionService;
    }


    public List<Glebe> getGlebes() {
        return glebeRepository.findAll();
    }

    public Glebe getGlebeById(String id) {
        Optional<Glebe> glebe = glebeRepository.findById(id);
        if(glebe.isEmpty()) {
            throw new ObjectNotFoundException("Talhão não encontrado!");
        }
        return glebe.get();
    }

    public Glebe addGlebe(String farmId, Glebe glebe) {

        glebeRepository.insert(glebe);

        Optional<Farm> optionalFarm = farmRepository.findById(farmId);

        if(optionalFarm.isEmpty()){
            throw new ObjectNotFoundException("Fazenda não encontrada para adicionar o novo talhão!");
        }

        Farm farmToAddGlebe = optionalFarm.get();

        farmToAddGlebe.getGlebes().add(glebe);

        farmRepository.save(farmToAddGlebe);

        return glebe;
    }

    public Glebe updateGlebe(String farmId, String glebeId, Glebe glebe) {
        if(!(farmRepository.existsById(farmId) && glebeRepository.existsById(glebeId))){
            throw new ObjectNotFoundException("Talhão não encontrado!");
        }
        glebe.setId(glebeId);
        return glebeRepository.save(glebe);
    }

    public boolean deleteGlebe(String farmId, String glebeId) {
        if(!farmRepository.existsById(farmId)){
            throw new ObjectNotFoundException("Essa fazenda não existe! Por favor, tente mais tarde!");
        }

        productionService.deleteProductionsFromGlebe(glebeId);
        Farm farm = farmRepository.findById(farmId).get();
        List<Glebe> glebes = farm.getGlebes();
        glebes.removeIf(it -> it.getId().equals(glebeId));
        farm.setGlebes(glebes);
        farmRepository.save(farm);
        glebeRepository.deleteById(glebeId);

        return  glebeRepository.existsById(glebeId);
    }


}
