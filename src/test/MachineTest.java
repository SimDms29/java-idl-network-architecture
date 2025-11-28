package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import reseau.Machine;
import reseau.Switch;

/**
 * Tests JUnit simples pour la classe Machine.
 */
public class MachineTest {
  
  private Machine machine;
  private Switch switchTest;
  
  @Before
  public void setUp() {
    switchTest = new Switch(1, true);
    machine = new Machine(10, "Machine-Test", switchTest);
  }
  
  @Test
  public void testGetNumeroIp() {
    assertEquals(Integer.valueOf(10), machine.getNumeroIp());
  }
  
  @Test
  public void testSetNumeroIp() {
    machine.setNumeroIp(20);
    assertEquals(Integer.valueOf(20), machine.getNumeroIp());
  }
  
  @Test
  public void testGetNom() {
    assertEquals("Machine-Test", machine.getNom());
  }
  
  @Test
  public void testSetNom() {
    machine.setNom("Nouveau-Nom");
    assertEquals("Nouveau-Nom", machine.getNom());
  }
  
  @Test
  public void testGetSwitch() {
    assertNotNull(machine.getSwitch());
    assertEquals(switchTest, machine.getSwitch());
  }
  
  @Test
  public void testConstructeur() {
    Machine m = new Machine(50, "Machine2", switchTest);
    assertEquals(Integer.valueOf(50), m.getNumeroIp());
    assertEquals("Machine2", m.getNom());
    assertEquals(switchTest, m.getSwitch());
  }
}