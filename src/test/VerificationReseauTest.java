package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import factory.VerificationReseau;

import java.util.HashSet;
import java.util.Set;
import reseau.Machine;
import reseau.Reseau;
import reseau.Switch;

/**
 * Tests JUnit pour la classe VerificationReseau.
 */
public class VerificationReseauTest {
  
  private Reseau reseauValide;
  private Set<Reseau> reseauxExistants;
  
  @Before
  /**
   * setup.
   */
  public void setUp() {
    // Initialisation d'un réseau valide pour les tests
    int[] adresseValide = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m1 = new Machine(10, "Machine-Test", switches[0]);
    machines.add(m1);
    
    reseauValide = new Reseau("Reseau-Test", adresseValide, 1, 24, switches, machines);
    
    // Initialisation de la collection de réseaux existants
    reseauxExistants = new HashSet<>();
    int[] adresse1 = {10, 0, 0};
    Set<Machine> machines1 = new HashSet<>();
    Switch[] switches1 = new Switch[1];
    switches1[0] = new Switch(2, false);
    Machine m2 = new Machine(5, "Machine-Existante", switches1[0]);
    machines1.add(m2);
    Reseau r1 = new Reseau("Reseau-Existant-1", adresse1, 1, 24, switches1, machines1);
    reseauxExistants.add(r1);
  }
  
  // ===== Tests pour verifnom() =====
  
  @Test
  public void testVerifnomNomUnique() {
    VerificationReseau verif = new VerificationReseau(reseauValide);
    String resultat = verif.verifnom(reseauxExistants);
    assertEquals("", resultat);
  }
  
  @Test
  public void testVerifnomNomDejaPris() {
    int[] adresse = {172, 16, 0};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(15, "Machine-Dup", switches[0]);
    machines.add(m);
    Reseau reseauDuplique = new Reseau("Reseau-Existant-1", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseauDuplique);
    String resultat = verif.verifnom(reseauxExistants);
    assertEquals("Nom déjà pris", resultat);
  }
  
  @Test
  public void testVerifnomCollectionVide() {
    Set<Reseau> collectionVide = new HashSet<>();
    VerificationReseau verif = new VerificationReseau(reseauValide);
    String resultat = verif.verifnom(collectionVide);
    assertEquals("", resultat);
  }
  
  // ===== Tests pour vmasque() =====
  
  @Test
  public void testVmasqueMasqueValide() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    
    VerificationReseau verif = new VerificationReseau(reseau);
    assertTrue(verif.vmasque());
  }
  
  @Test
  public void testVmasqueMasqueValide2() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 16, switches, machines);
    
    VerificationReseau verif = new VerificationReseau(reseau);
    assertTrue(verif.vmasque());
  }
  
  @Test
  public void testVmasqueMasqueValide3() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 25, switches, machines);
    
    VerificationReseau verif = new VerificationReseau(reseau);
    assertTrue(verif.vmasque());
  }
  
  @Test
  public void testVmasqueMasqueInvalide() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 99, switches, machines);
    
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vmasque());
  }
  
  @Test
  public void testVmasqueMasqueZero() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 0, switches, machines);
    
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vmasque());
  }
  
  // ===== Tests pour vpasserelle() =====
  
  @Test
  public void testVpasserellePasserelleValide() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    
    VerificationReseau verif = new VerificationReseau(reseau);
    assertTrue(verif.vpasserelle());
  }
  
  @Test
  public void testVpasserellePasserelleInvalide() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 2, 24, switches, machines);
    
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vpasserelle());
  }
  
  @Test
  public void testVpasserellePasserelleZero() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 0, 24, switches, machines);
    
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vpasserelle());
  }
  
  // ===== Tests pour vnumip() =====
  
  @Test
  public void testVnumipAucuneMachine() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertTrue(verif.vnumip());
  }
  
  @Test
  public void testVnumipNumeroIpValides() {
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[2];
    switches[0] = new Switch(30, true);
    switches[1] = new Switch(40, false);
    int[] adresse = {192, 168, 1};
    Machine m1 = new Machine(10, "Machine1", switches[0]);
    Machine m2 = new Machine(20, "Machine2", switches[1]);
    machines.add(m1);
    machines.add(m2);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertTrue(verif.vnumip());
  }
  
  @Test
  public void testVnumipNumeroIpDupliques() {
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m1 = new Machine(10, "Machine1", switches[0]);
    Machine m2 = new Machine(10, "Machine2", switches[0]);
    machines.add(m1);
    int[] adresse = {192, 168, 1};
    machines.add(m2);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vnumip());
  }
  
  @Test
  public void testVnumipNumeroIpTropGrand() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m1 = new Machine(256, "Machine1", switches[0]);
    machines.add(m1);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vnumip());
  }
  
  @Test
  public void testVnumipNumeroIpNegatif() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m1 = new Machine(-1, "Machine1", switches[0]);
    machines.add(m1);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vnumip());
  }
  
  @Test
  public void testVnumipAvecPlusieursSwitch() {
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[2];
    switches[0] = new Switch(50, true);
    switches[1] = new Switch(60, false);
    Machine m1 = new Machine(10, "Machine1", switches[0]);
    machines.add(m1);
    int[] adresse = {192, 168, 1};
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertTrue(verif.vnumip());
  }
  
  @Test
  public void testVnumipConflitMachineSwitch() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(10, true);
    Machine m1 = new Machine(10, "Machine1", switches[0]);
    machines.add(m1);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vnumip());
  }
  
  @Test
  public void testVnumipSwitchAvecIpInvalide() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(300, true);
    Machine m = new Machine(10, "Machine1", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vnumip());
  }
  
  @Test
  public void testVnumipSwitchDupliques() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[2];
    switches[0] = new Switch(50, true);
    switches[1] = new Switch(50, false);
    Machine m = new Machine(10, "Machine1", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vnumip());
  }
  
  // ===== Tests pour vadresse() =====
  
  @Test
  public void testVadresseAdresseValide() {
    int[] adresse = {192, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertTrue(verif.vadresse());
  }
  
  @Test
  public void testVadresseAdresseValideLimites() {
    int[] adresse = {0, 0, 0};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertTrue(verif.vadresse());
    
    int[] adresse2 = {255, 255, 255};
    Reseau reseau2 = new Reseau("Test2", adresse2, 1, 24, switches, machines);
    VerificationReseau verif2 = new VerificationReseau(reseau2);
    assertTrue(verif2.vadresse());
  }
  
  @Test
  public void testVadressePremierOctetNegatif() {
    int[] adresse = {-1, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vadresse());
  }
  
  @Test
  public void testVadressePremierOctetTropGrand() {
    int[] adresse = {256, 168, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vadresse());
  }
  
  @Test
  public void testVadresseDeuxiemeOctetInvalide() {
    int[] adresse = {192, -5, 1};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vadresse());
    
    int[] adresse2 = {192, 300, 1};
    Reseau reseau2 = new Reseau("Test2", adresse2, 1, 24, switches, machines);
    VerificationReseau verif2 = new VerificationReseau(reseau2);
    assertFalse(verif2.vadresse());
  }
  
  @Test
  public void testVadresseTroisiemeOctetInvalide() {
    int[] adresse = {192, 168, -10};
    Set<Machine> machines = new HashSet<>();
    Switch[] switches = new Switch[1];
    switches[0] = new Switch(2, true);
    Machine m = new Machine(10, "MachineTest", switches[0]);
    machines.add(m);
    Reseau reseau = new Reseau("Test", adresse, 1, 24, switches, machines);
    VerificationReseau verif = new VerificationReseau(reseau);
    assertFalse(verif.vadresse());
    
    int[] adresse2 = {192, 168, 500};
    Reseau reseau2 = new Reseau("Test2", adresse2, 1, 24, switches, machines);
    VerificationReseau verif2 = new VerificationReseau(reseau2);
    assertFalse(verif2.vadresse());
  }
}