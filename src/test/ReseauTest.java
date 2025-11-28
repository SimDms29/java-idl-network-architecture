package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import reseau.Machine;
import reseau.Reseau;
import reseau.Switch;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe de test pour les getters et setters de Reseau.
 */
public class ReseauTest {
  
  private Reseau reseau;
  private String nomInitial;
  private int[] adresseInitiale;
  private Integer passerelleInitiale;
  private Integer masqueInitial;
  private Switch[] switchsInitiaux;
  private Set<Machine> machinesInitiales;
  
  @Before
  public void setUp() {
    nomInitial = "Reseau Test";
    adresseInitiale = new int[]{192, 168, 1, 0};
    passerelleInitiale = 1;
    masqueInitial = 24;
    switchsInitiaux = new Switch[2];
    machinesInitiales = new HashSet<>();
    
    reseau = new Reseau(nomInitial, adresseInitiale, passerelleInitiale, 
                        masqueInitial, switchsInitiaux, machinesInitiales);
  }
  
  @Test
  public void testGetNom() {
    assertEquals(nomInitial, reseau.getNom());
  }
  
  @Test
  public void testSetNom() {
    String nouveauNom = "Nouveau Reseau";
    reseau.setNom(nouveauNom);
    assertEquals(nouveauNom, reseau.getNom());
  }
  
  @Test
  public void testGetAdresse() {
    assertArrayEquals(adresseInitiale, reseau.getAdresse());
  }
  
  @Test
  public void testSetAdresse() {
    int[] nouvelleAdresse = new int[]{10, 0, 0, 0};
    reseau.setAdresse(nouvelleAdresse);
    assertArrayEquals(nouvelleAdresse, reseau.getAdresse());
  }
  
  @Test
  public void testGetPasserelle() {
    assertEquals(passerelleInitiale, reseau.getPasserelle());
  }
  
  @Test
  public void testSetPasserelle() {
    Integer nouvellePasserelle = 254;
    reseau.setPasserelle(nouvellePasserelle);
    assertEquals(nouvellePasserelle, reseau.getPasserelle());
  }
  
  @Test
  public void testGetMasquerSousReseau() {
    assertEquals(masqueInitial, reseau.getMasquerSousReseau());
  }
  
  @Test
  public void testSetMasquerSousReseau() {
    Integer nouveauMasque = 16;
    reseau.setMasquerSousReseau(nouveauMasque);
    assertEquals(nouveauMasque, reseau.getMasquerSousReseau());
  }
  
  @Test
  public void testGetMachine() {
    assertEquals(machinesInitiales, reseau.getMachine());
  }
  
  @Test
  public void testSetMachine() {
    Set<Machine> nouvellesMachines = new HashSet<>();
    reseau.setMachine(nouvellesMachines);
    assertEquals(nouvellesMachines, reseau.getMachine());
  }
  
  @Test
  public void testGetSwitch() {
    assertArrayEquals(switchsInitiaux, reseau.getSwitch());
  }
}