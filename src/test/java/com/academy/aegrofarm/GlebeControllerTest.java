package com.academy.aegrofarm;

import com.academy.aegrofarm.controller.GlebeController;
import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.entity.Production;
import com.academy.aegrofarm.service.FarmService;
import com.academy.aegrofarm.service.GlebeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GlebeController.class)
public class GlebeControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private GlebeService glebeService;

    @MockBean
    private FarmService farmService;

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
    void addGlebe_allGood_shouldPass() throws Exception {

        Glebe validGlebe = createAValidGlebe();
        Farm validFarm = createAValidFarm();

        Mockito.when(farmService.addFarm(validFarm)).thenReturn(validFarm);
        Mockito.when(glebeService.addGlebe(validGlebe.getId(), validGlebe)).thenReturn(validGlebe);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(post("/api/v1/farms/" + validFarm.getId() + "/glebe/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validGlebe)))
                .andExpect(status().isCreated());

    }

    @Test
    void updateGlebe_allGood_shouldPass() throws Exception {

        Farm validFarm = createAValidFarm();
        Glebe validGlebe = createAValidGlebe();

        Mockito.when(farmService.updateFarm(validFarm.getId(), validFarm)).thenReturn(validFarm);
        Mockito.when(glebeService.updateGlebe(validFarm.getId(), validGlebe.getId(), validGlebe)).thenReturn(validGlebe);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(put("/api/v1/farms/" + validFarm.getId()
                        + "/glebe/" + validGlebe.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validGlebe)))
                .andExpect(status().isOk());

    }

    @Test
    void deleteGlebe_allGood_shouldPass() throws Exception {

        Farm validFarm = createAValidFarm();
        Glebe validGlebe = createAValidGlebe();

        Mockito.when(farmService.addFarm(validFarm)).thenReturn(validFarm);
        Mockito.when(glebeService.addGlebe(validFarm.getId(), validGlebe)).thenReturn(validGlebe);
        Mockito.when(glebeService.deleteGlebe(validFarm.getId(), validGlebe.getId())).thenReturn(true);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(delete("/api/v1/farms/" + validFarm.getId()
                        + "/glebe/" + validGlebe.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validGlebe)))
                .andExpect(status().isOk());

    }

}
