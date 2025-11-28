package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import reseau.Switch;

/**
 * Classe de test pour les getters, setters et toString de Switch.
 */
public class SwitchTest {
  
  private Switch switchTest;
  private Integer numeroIpInitial;
  private Boolean estManageInitial;
  
  @Before
  public void setUp() {
    numeroIpInitial = 10;
    estManageInitial = true;
    switchTest = new Switch(numeroIpInitial, estManageInitial);
  }
  
  @Test
  public void testGetNumeroIp() {
    assertEquals(numeroIpInitial, switchTest.getNumeroIp());
  }
  
  @Test
  public void testSetNumeroIp() {
    Integer nouveauNumeroIp = 25;
    switchTest.setNumeroIp(nouveauNumeroIp);
    assertEquals(nouveauNumeroIp, switchTest.getNumeroIp());
  }
  
  @Test
  public void testGetEstManage() {
    assertEquals(estManageInitial, switchTest.getEstManage());
  }
  
  @Test
  public void testSetEstManage() {
    Boolean nouvelleValeur = false;
    switchTest.setEstManage(nouvelleValeur);
    assertEquals(nouvelleValeur, switchTest.getEstManage());
  }
  
  @Test
  public void testToString() {
    String resultatAttendu = "Switch de numéro Ip : 10";
    assertEquals(resultatAttendu, switchTest.toString());
  }
  
  @Test
  public void testToStringApresModification() {
    switchTest.setNumeroIp(50);
    String resultatAttendu = "Switch de numéro Ip : 50";
    assertEquals(resultatAttendu, switchTest.toString());
  }
  
  @Test
  public void testToStringAvecNumeroIpNull() {
    Switch switchAvecNull = new Switch(null, true);
    String resultatAttendu = "Switch de numéro Ip : null";
    assertEquals(resultatAttendu, switchAvecNull.toString());
  }
}