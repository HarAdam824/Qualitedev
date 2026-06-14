package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class StatistiqueTests {

    StatistiqueImpl stats;

    @BeforeEach
    void initialiser() {
        stats = new StatistiqueImpl();
    }

    @Test
    void moyenneDuneSeuleVoiture() throws ArithmeticException {
        stats.ajouter(new Voiture("Ford", 15000));
        Echantillon e = stats.prixMoyen();
        assertEquals(15000, e.getPrixMoyen());
        assertEquals(1, e.getNombreDeVoitures());
    }

    @Test
    void moyenneDeDeuxVoitures() throws ArithmeticException {
        stats.ajouter(new Voiture("Seat", 8000));
        stats.ajouter(new Voiture("Opel", 12000));
        Echantillon e = stats.prixMoyen();
        assertEquals(10000, e.getPrixMoyen());
        assertEquals(2, e.getNombreDeVoitures());
    }

    @Test
    void erreurSiAucuneVoiture() {
        assertThrows(ArithmeticException.class, () -> stats.prixMoyen());
    }
}