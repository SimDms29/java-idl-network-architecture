package facade;

import reseau.Reseau;
import vues.Observeur;
import vues.Sujet;

import java.util.HashSet;
import java.util.Set;

/**
 * face de notre projet.
 */

public class Facade implements Sujet {

  private Mediateur med;
  private Set<Observeur> obs;

  /**
   * constructeur de la classe.
   */
  public Facade() {

    this.med = new Mediateur();
    this.obs = new HashSet<>();

  }

  /**
   * creerreseau.
   *
   * @param nom     du reseau.
   * @param adresse du reseau.
   */
  public boolean creerReseau(String nom, int[] adresse, int nbM, boolean manage) {

    return med.creer(nom, adresse, nbM, manage);

  }

  /**
   * creer reseau avec json.
   *
   * @param nomfichier d'où sont tirées les infos.
   */
  public boolean creerReseauViaJson(String nomfichier) {

    return med.creerJ(nomfichier);
  }

  /**
   * modifier reseau.
   *
   * @param nom     du reseau.
   * @param adresse du reseau.
   * @param nbM     de machine.
   */
  public void modifierReseau(String nom, int[] adresse, int nbM, boolean manage) {

    med.modifier(nom, adresse, nbM, manage);
  }

  /**
   * suppr.
   *
   */
  public void supprimerReseau() {

    med.supprimer();

  }

  /**
   * Affichage Reseau V1.
   *
   */
  public void afficherReseauV1() {

    med.afficherReseauListe();
  }

  /**
   * Affichage Reseau V2.
   *
   */
  public void afficherReseauV2() {
    
    med.afficherReseauSwitch();
  }
  
  /**
   * selection du courant.
   *
   * @param nom du reseau.
   */
  public void selectioncourant(String nom) {
    
    med.selectioncourant(nom);
  }
  
  /**
   * getteur de courant.
   *
   * @return le courant.
   */
  public Reseau getcourant() {
    
    return med.getcourant();
  }

  @Override
  public void attacher(Observeur obs) {

    this.obs.add(obs);

  }

  @Override
  public void detacher(Observeur obs) {

    this.obs.remove(obs);

  }

  @Override
  public void notifier() {

    for (Observeur obs : this.obs) {
      obs.update();
    }

  }

  @Override
  public Reseau getEtat() {

    return med.getcourant();
  }

  /**
   * setteur.
   *
   * @param s la collection.
   */
  public void setReseau(Set<Reseau> s) {

    med.setReseau(s);


  }

  /**
   * getteur.
   *
   * @return une collection.
   */
  public Set<Reseau> getReseau() {

    return med.getReseau();
  }

}
