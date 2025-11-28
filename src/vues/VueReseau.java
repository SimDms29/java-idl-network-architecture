package vues;

import java.util.HashSet;
import java.util.Set;

import facade.Facade;
import reseau.Machine;
import reseau.Reseau;

/**
 * classe vue.
 */
public class VueReseau implements Observeur {
  
  private Reseau reseau;
  private Facade facade;
  
  /**
   * Constructeur.
   *
   * @param reseau de la vue.
   */
  public VueReseau(Reseau reseau, Facade fac) {
    
    this.reseau = reseau;
    this.reseau.attacher(this);
    this.facade = fac;
    
  }

  @Override
  public void update() {
    
    Reseau r = facade.getEtat();
    
    facade.selectioncourant(r.getNom());
    System.out.println("");
    System.out.println("UPDATE ! --------------");
    facade.afficherReseauV1();
    
  }
  
  /**
   * Ajoute une machine au sujet reseau.
   *
   * @param ma , collection de machines.
   */
  public void ajouterMachine(Machine ma) {
    
    Set<Machine> m = new HashSet<>();
    m = this.reseau.getMachine();
    m.add(ma);
    this.reseau.setMachine(m);
    this.reseau.notifier();
  }
  
  /**
   * Supprimer une machine au sujet reseau.
   *
   * @param ma , collection de machines.
   */
  public void supprimerMachine(Machine ma) {
    
    Set<Machine> m = new HashSet<>();
    m = this.reseau.getMachine();
    m.remove(ma);
    this.reseau.setMachine(m);
    this.reseau.notifier();
  }
  
  /**
   * Modifier le nom du reseau.
   */
  public void modifierNom(String nom) {
    
    this.reseau.setNom(nom);
    this.reseau.notifier();
    
  }
  
  /**
   * Modifier adresse . 
   *
   * @param adresse du reseau.
   */
  public void modifierAdresse(int[] adresse) {
    
    this.reseau.setAdresse(adresse);
    this.reseau.notifier();
    
  }

}
