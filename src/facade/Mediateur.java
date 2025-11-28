package facade;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import architecture.VisiteurAffichageListeReseau;
import architecture.VisiteurSwitch;
import factory.Factory;
import factory.FactoryJson;
import factory.VerificationReseau;
import reseau.Machine;
import reseau.Reseau;
import reseau.Switch;
import vues.Observeur;
import vues.Sujet;

/**
 * Classe mediateur.
 */
public class Mediateur {

  private Set<Reseau> lesReseaux;
  private Factory factorystandard;
  private FactoryJson factoryjson;
  private VisiteurAffichageListeReseau visiteurliste;
  private VisiteurSwitch visiteurswitch;
  private Reseau reseaucourant;

  /**
   * Constructeur.
   */
  public Mediateur() {

    this.lesReseaux = new HashSet<>();
    this.factorystandard = new Factory();
    this.factoryjson = new FactoryJson();
    
    this.visiteurliste = new VisiteurAffichageListeReseau();  
    this.visiteurswitch = new VisiteurSwitch();                

  }

  /**
   * Créer.
   *
   * @param nom     du reseau.
   * @param adresse du reseau.
   */
  public boolean creer(String nom, int[] adresse, int nb, boolean manage) {
    
    // On évite ici un nombre négatif de machine et un nombre trop grand.
    
    if (nb < 1 || nb > 100) {
      
      throw new IllegalArgumentException("Impossible de créer un reseau avec nb machine < 0 "
          + "ou nb machine trop grand");
    }
    
    Reseau r = factorystandard.reseauStandard(nom, adresse, nb, manage);
    VerificationReseau v = new VerificationReseau(r);
    
    String result = v.verifnom(this.lesReseaux);
    
    if (!(result == "")) {
      
      System.out.println(result);
      return false;
    }
    
    
    if (!(v.verif().isEmpty())) {
      System.out.println(v.verif().toString());
      return false;
    }
    this.lesReseaux.add(r);
    return true;
  }

  /**
   * CréerJ.
   *
   * @param nomfichier json.
   */
  public boolean creerJ(String nomfichier) {

    Set<Reseau> r = factoryjson.reseauDepuisJson(nomfichier);
    
    
    
    for (Reseau res : r) {
      
    
      VerificationReseau v = new VerificationReseau(res);
      
      String result = v.verifnom(this.lesReseaux);
      
      if (!(result == "")) {
        
        System.out.println(result);
        return false;
      }
  
      if (!(v.verif().isEmpty())) {
        System.out.println(v.verif().toString());
  
        return false;
      }
  
      this.lesReseaux.add(res);
      
    }
    return true;

  }

  /**
   * supprimer.
   *
   * @return un boolean.
   */
  public boolean supprimer() {

    return (this.lesReseaux.remove(reseaucourant));

  }

  /**
   * Lire .
   *
   * @return une chaine.
   */

  public String lire() {

    String res = "";

    

    res += reseaucourant.getNom() + " ,";
    res += Arrays.toString(reseaucourant.getAdresse()) + " ,";

    Set<Machine> m = reseaucourant.getMachine();

    res += " nombre de machine : ";

    res += m.size() + " ,";

    Switch[] s = reseaucourant.getSwitch();

    res += " nombre de switch : ";

    res += s.length + " .";

     

    return res;

  }

  /**
   * Modifier .
   *
   * @param nom du reseau et adresse.
   * @return un boolean.
   */

  public boolean modifier(String nom, int[] adresse, int nb, boolean manage) {

    for (Reseau r : this.lesReseaux) {

      if (r.getNom().equals(nom)) {

        selectioncourant(nom);
        supprimer();
        creer(nom, adresse, nb, manage);

        return true;

      } else {

        return false;
      }

    }

    return false;

  }

  /**
   * affichage.
   *
   */

  public void afficherReseauListe() {

   

    System.out.println(visiteurliste.afficher(reseaucourant));;


  }

  /**
   * affichage.
   *
   * @param nom du reseauy.
   */

  public void afficherReseauSwitch() {



    System.out.println(visiteurswitch.afficher(reseaucourant));

    

  }
  
  /**
   * selection du reseau courant.
   *
   * @param nom du reseau.
   */
  public void selectioncourant(String nom) {
    
    for (Reseau r : this.lesReseaux) {

      if (r.getNom().equals(nom)) {

        reseaucourant = r;

      }

    }
  }
  
  /**
   * getteur du courant.
   * 
   */
  public Reseau getcourant() {
     
    return reseaucourant;
    
  }
  
  
  
  /**
   * setteur.
   *
   * @param s collection.
   */
  public void setReseau(Set<Reseau> s) {
    
    this.lesReseaux = s;
    
    boolean bool = false;
    
    for (Reseau r : this.lesReseaux) {

      if (r.equals(reseaucourant)) {

        bool = true;
      }

    }
    
    if (bool == false) {
      
      for (Reseau r : this.lesReseaux) {
        
        if (r != null) {
          
          reseaucourant = r;
          break;
          
        }
      }
    }
  }
  
  public Set<Reseau> getReseau(){
    
    return this.lesReseaux;
  }


}
