package com.academy.aegrofarm;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.entity.Production;
import com.academy.aegrofarm.repository.FarmRepository;
import com.academy.aegrofarm.repository.GlebeRepository;
import com.academy.aegrofarm.service.GlebeService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GlebeServiceTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private GlebeRepository glebeRepository;

    @InjectMocks
    private GlebeService glebeService;

    private Farm createAValidFarm() {

        Farm farm = new Farm();
        farm.setId("testId");
        farm.setName("Fazenda de teste");
        farm.setGlebes(new ArrayList<>());

        return farm;
    }

    private Glebe createAValidGlebe() {

        Glebe glebe = new Glebe();
        glebe.setId("glebeTestId");
        glebe.setName("Talhão de teste");
        glebe.setArea(new BigDecimal("340.0056"));
        List<Production> productions = new ArrayList<>();
        glebe.setProductions(productions);

        return glebe;
    }

    @Test
    void getGlebes_allGood_shouldPass() {
        Glebe validGlebe = createAValidGlebe();
        List<Glebe> glebes = new ArrayList<>();
        glebes.add(validGlebe);

        Mockito.when(glebeRepository.findAll()).thenReturn(glebes);

        List<Glebe> addedGlebe = glebeService.getGlebes();

        Assert.assertEquals(addedGlebe, glebes);
    }

    @Test
    void addGlebe_allGood_shouldPass() {

        Farm validFarm = createAValidFarm();
        Glebe validGlebe = createAValidGlebe();

        Mockito.when(glebeRepository.insert(validGlebe)).thenReturn(validGlebe);

        Mockito.when(farmRepository.findById(validFarm.getId())).thenReturn(Optional.of(validFarm));

        Glebe addedGlebe = glebeService.addGlebe(validFarm.getId(), validGlebe);

        Assert.assertEquals(addedGlebe, validGlebe);

    }

    @Test
    void updateGlebe_allGood_shouldPass() {

        Glebe validGlebe = createAValidGlebe();
        Farm validFarm = createAValidFarm();

        Mockito.when(farmRepository.existsById(validFarm.getId())).thenReturn(true);
        Mockito.when(glebeRepository.existsById(validGlebe.getId())).thenReturn(true);
        Mockito.when(glebeRepository.save(validGlebe)).thenReturn(validGlebe);

        Glebe addedGlebe = glebeService.updateGlebe(validFarm.getId(), validGlebe.getId(), validGlebe);

        Assert.assertEquals(addedGlebe, validGlebe);

    }

    @Test
    void deleteGlebe_allGood_shouldPass() {

        Glebe validGlebe = createAValidGlebe();
        Farm validFarm = createAValidFarm();

        Mockito.when(farmRepository.findById(validFarm.getId())).thenReturn(Optional.of(validFarm));
        Mockito.when(farmRepository.existsById(validFarm.getId())).thenReturn(true);
        boolean existsGlebe = glebeService.deleteGlebe(validFarm.getId(), validGlebe.getId());

        Assertions.assertFalse(existsGlebe);

    }

}
