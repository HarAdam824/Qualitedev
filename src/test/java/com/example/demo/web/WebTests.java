package com.example.demo.web;

import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Test
    void envoyerUneVoiture() throws Exception {
        mockMvc.perform(post("/voiture")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"marque\":\"Honda\",\"prix\":9000}"))
            .andExpect(status().isOk());
        verify(statistiqueImpl).ajouter(any());
    }

    @Test
    void recupererStatistiques() throws Exception {
        when(statistiqueImpl.prixMoyen()).thenReturn(new Echantillon(3, 11000));
        mockMvc.perform(get("/statistique"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.prixMoyen").value(11000))
            .andExpect(jsonPath("$.nombreDeVoitures").value(3));
    }

    @Test
    void erreurSiPasDeVoiture() throws Exception {
        when(statistiqueImpl.prixMoyen()).thenThrow(new ArithmeticException());
        mockMvc.perform(get("/statistique"))
            .andExpect(status().isBadRequest());
    }
}