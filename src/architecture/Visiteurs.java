package architecture;

import reseau.Machine;
import reseau.Reseau;
import reseau.Switch;

/**
 *Interface visiteurs.
 */
public interface Visiteurs {
  
  /**
   * Afficher machine
   *
   * @param m qu'on visite.
   */
  
  String afficher(Machine m);
  
  /**
   * afficher reseau
   *
   * @param s le switch.
   */
  String afficher(Switch s);
  
  /**
   * afficher reseau.
   *
   * @param r le reseau
   */
  String afficher(Reseau r);

}
