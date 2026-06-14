package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class VoitureTest {

    private Voiture voiture;

    @BeforeEach
    void init() {
        voiture = new Voiture("Renault", 12000);
    }

    @Test
    void verifierMarque() {
        assertEquals("Renault", voiture.getMarque());
    }

    @Test
    void verifierPrix() {
        assertEquals(12000, voiture.getPrix());
    }

    @Test
    void modifierMarque() {
        voiture.setMarque("Citroen");
        assertEquals("Citroen", voiture.getMarque());
    }

    @Test
    void modifierPrix() {
        voiture.setPrix(8500);
        assertEquals(8500, voiture.getPrix());
    }

    @Test
    void identifiantParDefaut() {
        assertEquals(0, voiture.getId());
    }

    @Test
    void modifierIdentifiant() {
        voiture.setId(42);
        assertEquals(42, voiture.getId());
    }

    @Test
    void constructeurVide() {
        Voiture v = new Voiture();
        assertNull(v.getMarque());
        assertEquals(0, v.getPrix());
    }

    @Test
    void affichageToString() {
        voiture.setId(3);
        String s = voiture.toString();
        assertTrue(s.contains("Renault"));
        assertTrue(s.contains("12000"));
        assertTrue(s.contains("3"));
    }
}