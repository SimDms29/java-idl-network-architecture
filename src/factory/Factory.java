package factory;

import java.util.HashSet;
import java.util.Set;

import reseau.Machine;
import reseau.Reseau;
import reseau.Switch;

/**
 * Constructeur de réseaux.
 */
public class Factory {

  /**
   * Nombre de machines créées.
   */

  private int nbmachine;
  
  /**
   * passerelle standard.
   */
  private int passerelle = 1;
  
  /**
   * masque standard.
   */
  private int masque = 24;
  
  /**
   * Factory de reseau.
   *
   * @param nom     nom.
   * @param adresse adresse.
   */

  public Reseau reseauStandard(String nom, int[] adresse, int nbM, boolean estmanage) {
    
    Switch s = new Switch(2, estmanage);
    Switch[] tabs = new Switch[1];
    tabs[0] = s;
    String nomM = "Machine ";

    Set<Machine> m = new HashSet<>();

    for (int i = 3, j = 0; j < nbM; i++, j++) {
          
      Machine ma = new Machine(i, nomM + String.valueOf(i - 2), s);
      m.add(ma);
      nbmachine++; 
    }

    return new Reseau(nom, adresse, passerelle, masque, tabs, m);
    
  }
  
  /**
   * renvoie le masque. 
   *
   * @return le masque.
   */
  public int getMasque() {
    return this.masque;
  }
  
  public void setMasque(int nb) {
    this.masque = nb;
  }

  /**
   * Cette méthode renvoie le nombre de machine.
   *
   * @return le nombre de machine créées.
   */

  public int nbMachines() {
    return this.nbmachine;
  }
}
