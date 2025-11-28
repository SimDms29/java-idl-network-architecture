package vues;

import java.util.HashSet;
import java.util.Set;

import facade.Facade;
import facade.Mediateur;
import reseau.Machine;
import reseau.Reseau;

/**
 * classe vue.
 */
public class VueListeReseau implements Observeur {
  
  private Facade facade;
  
  /**
   * Constructeur.
   *
   * @param fac de la vue.
   */
  public VueListeReseau(Facade fac) {
    
    this.facade = fac;
    this.facade.attacher(this);
    
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
   * Ajouter un reseau à la collection de reseaux de notre sujet mediateur.
   *
   * @param res à ajouter.
   */
  public void ajouterReseau(Reseau res) {
    
    Set<Reseau> s = new HashSet<>();
    s = facade.getReseau();
    s.add(res);
    facade.setReseau(s);
    facade.notifier();
    
  }
  
  /**
   * Supprimer un reseau à la collection de reseaux de notre sujet mediateur.
   *
   * @param res à supprimer.
   */
  public void supprimerReseau(Reseau res) {
    
    Set<Reseau> s = new HashSet<>();
    s = facade.getReseau();
    s.remove(res);
    facade.setReseau(s);
    facade.notifier();
  }
  
  
  

}
