package com.academy.aegrofarm;

import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.exception.InvalidOperationException;
import com.academy.aegrofarm.repository.FarmRepository;
import com.academy.aegrofarm.repository.GlebeRepository;
import com.academy.aegrofarm.service.FarmService;
import org.junit.Assert;
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
class FarmServiceTest {

    @Mock
    private FarmRepository farmRepository;

    @Mock
    private GlebeRepository glebeRepository;

    @InjectMocks
    private FarmService farmService;

    private Farm createAValidFarm() {

        Farm farm = new Farm();
        farm.setId("testId");
        farm.setName("Fazenda de teste");

        List<Glebe> glebes = new ArrayList<>();
        Glebe glebeA = new Glebe();
        glebeA.setArea(new BigDecimal("20"));
        glebeA.setProductivity(new BigDecimal("200"));
        Glebe glebeB = new Glebe();
        glebeB.setArea(new BigDecimal("10"));
        glebeB.setProductivity(new BigDecimal("500"));
        glebes.add(glebeA);
        glebes.add(glebeB);

        farm.setGlebes(glebes);

        return farm;
    }

    @Test
    void getFarms_allGood_shouldPass() {

        Farm validFarm = createAValidFarm();
        List<Farm> farms = new ArrayList<>();
        farms.add(validFarm);

        Mockito.when(farmRepository.findAll()).thenReturn(farms);

        List<Farm> addedFarm = farmService.getFarms();

        Assert.assertEquals(addedFarm, farms);

    }

    @Test
    void addFarm_allGood_shouldPass() {

        Farm validFarm = createAValidFarm();

        Mockito.when(farmRepository.insert(validFarm)).thenReturn(validFarm);

        Farm addedFarm = farmService.addFarm(validFarm);

        Assert.assertEquals(addedFarm, validFarm);

    }

    @Test
    void updateFarm_allGood_shouldPass() {

        Farm validFarm = createAValidFarm();

        Mockito.when(farmRepository.save(validFarm)).thenReturn(validFarm);

        Farm addedFarm = farmService.updateFarm(validFarm.getId(), validFarm);

        Assert.assertEquals(addedFarm, validFarm);

    }

    @Test
    void deleteFarm_allGood_shouldPass() {

        Farm validFarm = createAValidFarm();

        Mockito.when(farmRepository.findById(validFarm.getId())).thenReturn(Optional.of(validFarm));
        Mockito.when(farmRepository.existsById(validFarm.getId())).thenReturn(false);

        farmService.deleteFarm(validFarm.getId());

    }

    @Test
    void calculateFarmProductivity_allGood_shouldPass() {
        Farm validFarm = createAValidFarm();

        BigDecimal productivity = farmService.calculateFarmProductivity(validFarm);

        Assert.assertEquals(productivity, new BigDecimal("300"));
    }

    @Test
    void calculateFarmProductivity_shouldReturnError() {
        Farm testFarm = createAValidFarm();
        Glebe invalidGlebe = new Glebe();
        invalidGlebe.setArea(BigDecimal.ZERO);
        List<Glebe> glebes = new ArrayList<>();
        glebes.add(invalidGlebe);
        testFarm.setGlebes(glebes);

        Assert.assertThrows(InvalidOperationException.class, () -> farmService.calculateFarmProductivity(testFarm));
    }

}
