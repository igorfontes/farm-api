package com.academy.aegrofarm;

import com.academy.aegrofarm.controller.FarmController;
import com.academy.aegrofarm.entity.Farm;
import com.academy.aegrofarm.service.FarmService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FarmController.class)
class FarmControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private FarmService farmService;

    private Farm createAValidFarm() {

        Farm farm = new Farm();
        farm.setId("testId");
        farm.setName("Fazenda de teste");
        farm.setGlebes(new ArrayList<>());

        return farm;
    }

    @Test
    void addFarm_allGood_shouldPass() throws Exception {

        Farm validFarm = createAValidFarm();

        Mockito.when(farmService.addFarm(validFarm)).thenReturn(validFarm);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(post("/api/v1/farms")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validFarm)))
                        .andExpect(status().isCreated());

    }

    @Test
    void updateFarm_allGood_shouldPass() throws Exception {

        Farm validFarm = createAValidFarm();

        Mockito.when(farmService.updateFarm(validFarm.getId(), validFarm)).thenReturn(validFarm);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(put("/api/v1/farms/" + validFarm.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validFarm)))
                .andExpect(status().isOk());

    }

    @Test
    void deleteFarm_allGood_shouldPass() throws Exception {

        Farm validFarm = createAValidFarm();

        Mockito.when(farmService.deleteFarm(validFarm.getId())).thenReturn(true);

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(delete("/api/v1/farms/" + validFarm.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validFarm)))
                .andExpect(status().isOk());

    }

}
