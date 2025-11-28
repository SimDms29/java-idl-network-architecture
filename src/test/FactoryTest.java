package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import architecture.*;
import factory.FactoryJson;
import reseau.Reseau;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Tests JUnit pour la Factory.
 */
public class FactoryTest {

  private FactoryJson factory;

  /**
   * fonction setupp.
   */
  @BeforeEach
  public void setUp() {
    factory = new FactoryJson();
  }

  @Test
  public void testReseauStandard() {
    int[] adresse = { 192, 168, 1, 0 };
    Reseau reseau = factory.reseauStandard("Test Reseau", adresse, 5);

    assertNotNull(reseau, "Le réseau ne doit pas être null");
    assertEquals(5, factory.nbMachines(), "Le nombre de machines doit être 5");
    assertEquals(1, reseau.getPasserelle(), "La passerelle doit être égale à 1");
    assertEquals(24, reseau.getMasquerSousReseau(), "Le masque doit être égale 24");
  }

  @Test
  public void testReseauDepuisJson() {
    Set<Reseau> reseaux = factory.reseauDepuisJson("reseau.json");

    assertNotNull(reseaux, "Les réseaux chargés depuis JSON ne doivent pas être null");
    assertFalse(reseaux.isEmpty(), "La liste des réseaux ne doit pas être vide");
    
    // Vérifier chaque réseau
    for (Reseau reseau : reseaux) {
      assertNotNull(reseau, "Un réseau ne doit pas être null");
      assertEquals(1, reseau.getPasserelle(), "La passerelle doit être égale à 1");
      assertEquals(24, reseau.getMasquerSousReseau(), "Le masque doit être égal à 24");
    }
    
    // Vérifier le nombre total de réseaux si nécessaire
    assertEquals(3, reseaux.size(), "Il doit y avoir 3 réseaux");
  }

  @Test
  public void testReseauDepuisJsonInexistant() {
    
    Set<Reseau> reseaux = new HashSet<>();
        
    reseaux = factory.reseauDepuisJson("fichier_inexistant.json");

    assertEquals(true, reseaux == null , "Le réseau doit être null si le fichier n'existe pas");
  }
}