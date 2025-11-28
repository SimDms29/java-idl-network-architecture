package reseau;

import java.util.HashSet;
import java.util.Set;

import architecture.ElementsVisites;
import architecture.Visiteurs;
import vues.Observeur;
import vues.Sujet;

/**
 * La classe Reseau.
 */
public class Reseau implements ElementsVisites, Sujet{

  /**
   * Nom.
   */
  private String nom;
  
  /**
   * Adresse.
   */
  private int[] adresse;
  
  /**
   * Passerelle.
   */
  private Integer passerelle;
  
  /**
   * Masque du reseau.
   */
  private Integer masqueSousReseau;
  
  /**
   * Liste des switchs.
   */
  private Switch[] leSwitchs;
  
  /**
   * Liste des machines.
   */
  private Set<Machine> lesMachines;
  
  /**
   * set des observeurs.
   */
  private Set<Observeur> observeur;

  /**
   * Reseau.
   */
  public Reseau(String nom, int[] adresse, Integer passerelle, 
      Integer masquerSousReseau, Switch[] s, Set<Machine> m) {
    
    this.nom = nom;
    this.adresse = adresse;
    this.passerelle = passerelle;
    this.masqueSousReseau = masquerSousReseau;
    this.leSwitchs = s;
    this.lesMachines = m;
    this.observeur = new HashSet<>();
    
    
    
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public int[] getAdresse() {
    return adresse;
  }

  public void setAdresse(int[] adresse) {
    this.adresse = adresse;
  }

  public Integer getPasserelle() {
    return passerelle;
  }

  public void setPasserelle(Integer passerelle) {
    this.passerelle = passerelle;
  }
  
  public Set<Machine> getMachine() {
    return this.lesMachines;
  }
  
  public Switch[] getSwitch() {
    return this.leSwitchs;
    
  }
  
  public void setMachine(Set<Machine> m) {
    this.lesMachines = m;
  }
  
  public Integer getMasquerSousReseau() {
    return masqueSousReseau;
  }

  public void setMasquerSousReseau(Integer masquerSousReseau) {
    this.masqueSousReseau = masquerSousReseau;
  }

  @Override
  public String applique(Visiteurs visiteur) {

    return visiteur.afficher(this);
  }

  @Override
  public void attacher(Observeur obs) {
    
    this.observeur.add(obs);
    
  }

  @Override
  public void detacher(Observeur obs) {
    
    this.observeur.remove(obs);
    
  }

  @Override
  public void notifier() {
    
    for (Observeur obs : this.observeur) {
      obs.update();
    }
    
  }

  @Override
  public Reseau getEtat() {
    
    return this;
  }
}