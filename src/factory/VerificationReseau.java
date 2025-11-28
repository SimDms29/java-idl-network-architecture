package factory;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinTask;

import reseau.Machine;
import reseau.Reseau;
import reseau.Switch;

/**
 * classe Verification de Reseaux.
 */

public class VerificationReseau {

  /**
   * le reseau testé.
   */
  private Reseau reseautest;

  /**
   * Constructeur.
   *
   */

  public VerificationReseau(Reseau r) {

    this.reseautest = r;
    

  }
  
  /**
   * verification des noms.
   *
   * @param r collections des reseaux existajnt.
   * @return un string.
   */
  public String verifnom(Set<Reseau> r) {
    
    Set<String> s = new HashSet<>();
    
    for (Reseau res : r) {
      
      s.add(res.getNom());
      
    }
    
    String res = "";
    
    if (s.add(this.reseautest.getNom())) {
      
      return res;
    
    }
    
    return "Nom déjà pris";
  }

  /**
   * verif du masque.
   *
   * @return boolean.
   */
  public boolean vmasque() {

    return (this.reseautest.getMasquerSousReseau() == 24 ||
        this.reseautest.getMasquerSousReseau() == 16 || 
        this.reseautest.getMasquerSousReseau() == 25);

  }

  /**
   * verif de la passerelle.
   *
   * @return boolean.
   */
  public boolean vpasserelle() {

    return (this.reseautest.getPasserelle() == 1);
  }

  /**
   * verif du numero ip.
   *
   * @return boolean.
   */
  public boolean vnumip() {

    Set<Integer> numeroIp = new HashSet<>();

    Set<Machine> m = reseautest.getMachine();
    
    
    /*
     * Ici, si la collection est vide alors de toute évidence 
     * il n'y a pas de problème concernant les numéros IP.
     */
    
    if (m.isEmpty()) {
      
      return true; 
      
    }

    for (Machine ma : m) {

      if (!(numeroIp.add(ma.getNumeroIp()))) {
        return false;
      }

    }

    Switch[] s = reseautest.getSwitch();

    for (int j = 0; j < s.length; j++) {

      if (!(numeroIp.add(s[j].getNumeroIp()))) {
        return false;
      }

    }

    for (int i : numeroIp) {

      if (i > 255 || i < 0) {

        return false;

      }
    }

    return true;

  }

  /**
   * verif de l'adresse.
   *
   * @return boolean.
   */
  public boolean vadresse() {

    int[] a = reseautest.getAdresse().clone();

    if (a[0] > 255 || a[0] < 0) {
      return false;
    }
    if (a[1] > 255 || a[1] < 0) {
      return false;
    }
    if (a[2] > 255 || a[2] < 0) {
      return false;
    }

    return true;
  }
  
  /**
   * verif globale.
   *
   * @return boolean.
   */

  public Set<String> verif() {
    
    Set<String> erreurs = new HashSet<>();

    if ((vmasque() == false)) {
      erreurs.add("Masque incorrect !");
    }

    if ((vpasserelle() == false)) {
      erreurs.add("Passerelle incorrecte !");
    }

    if ((vnumip() == false)) {
      erreurs.add("Numero Ip incorrect !");

    }

    if ((vadresse() == false)) {
      erreurs.add("Adresse incorrecte !");
    }
    
    if (reseautest.getMachine().size() == 0 || reseautest.getSwitch().length == 0) {
      erreurs.add("Pas de machine !");
    }

    return erreurs;

  }

}
