package com.academy.aegrofarm;


import com.academy.aegrofarm.controller.ProductionController;
import com.academy.aegrofarm.entity.Glebe;
import com.academy.aegrofarm.entity.Production;
import com.academy.aegrofarm.service.GlebeService;
import com.academy.aegrofarm.service.ProductionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductionController.class)
public class ProductionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ProductionService productionService;

    @MockBean
    private GlebeService glebeService;

    private Glebe createAValidGlebe() {

        Glebe glebe = new Glebe();
        glebe.setId("testId");
        glebe.setName("Talhão de teste");
        glebe.setProductions(new ArrayList<>());

        return glebe;
    }

    private Production createAValidProduction() {

        Production production = new Production();
        production.setId("productionTestId");
        production.setProduction(new BigDecimal("50"));

        return production;
    }

    @Test
    void addProduction_allGood_shouldPass() throws Exception {

        Production validProduction = createAValidProduction();
        Glebe validGlebe = createAValidGlebe();

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(post("/api/v1/glebes/" + validGlebe.getId() + "/production/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validProduction)))
                .andExpect(status().isCreated());

    }

    @Test
    void updateProduction_allGood_shouldPass() throws Exception {

        Glebe validGlebe = createAValidGlebe();
        Production validProduction = createAValidProduction();

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(put("/api/v1/glebes/" + validGlebe.getId()
                        + "/production/" + validProduction.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validProduction)))
                .andExpect(status().isOk());

    }

    @Test
    void deleteProduction_allGood_shouldPass() throws Exception {

        Glebe validGlebe = createAValidGlebe();
        Production validProduction = createAValidProduction();

        ObjectMapper mapper = new ObjectMapper();

        mvc.perform(delete("/api/v1/glebes/" + validGlebe.getId()
                        + "/production/" + validProduction.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(validProduction)))
                .andExpect(status().isOk());

    }

}
