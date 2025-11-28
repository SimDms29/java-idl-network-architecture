package vues;

import java.util.HashSet;
import java.util.Set;

import facade.Facade;
import reseau.Machine;
import reseau.Reseau;
import reseau.Switch;
import vues.VueReseau;

/**
 * Test simple : vérification que les modifications via la vue 
 * sont bien visibles via la facade.
 */
public class MainTestVues {

  /**
   * Classe de test des vues.
   *
   * @param args java.
   */
  public static void main(String[] args) {
    
    // Création de la facade et d'un réseau
    Facade facade = new Facade();
    int[] adresse = {192, 168, 1, 0};
    facade.creerReseau("MonReseau", adresse, 2, true);
    
    // Affichage initial du réseau
    System.out.println("=== AFFICHAGE INITIAL ===");
    facade.selectioncourant("MonReseau");
    facade.afficherReseauV1();
    System.out.println();
    
    Switch [] sw =  null;
    // Récupération du réseau pour créer une vue
    Reseau monReseau = null;
    for (Reseau r : facade.getReseau()) {
      if (r.getNom().equals("MonReseau")) {
        monReseau = r;
        sw = r.getSwitch();
        break;
      }
    }
    
    Set<Reseau> set = new HashSet<>();
    
    set.add(monReseau);
    
    facade.setReseau(set);
    
    // Création de la vue
    VueReseau vue = new VueReseau(monReseau, facade);
    
    // TEST 1 : Ajout d'une machine via la vue
    System.out.println("--- J'ajoute une machine via la vue ---");
    
    Machine m1 = new Machine(10, "PC-001", sw[0]);
    vue.ajouterMachine(m1);
    
    System.out.println("\n=== AFFICHAGE APRES AJOUT DE PC-001 ===");
    facade.selectioncourant("MonReseau");
    facade.afficherReseauV1();
    System.out.println();
    
    // TEST 2 : Ajout d'une deuxième machine
    System.out.println("--- J'ajoute une deuxième machine via la vue ---");
    int[] adresseMachine2 = {192, 168, 1, 20};
    Machine m2 = new Machine(11, "PC-002", sw[0]);
    vue.ajouterMachine(m2);
    
    System.out.println("\n=== AFFICHAGE APRES AJOUT DE PC-002 ===");
    facade.selectioncourant("MonReseau");
    facade.afficherReseauV1();
    System.out.println();
    
    // TEST 3 : Suppression d'une machine via la vue
    System.out.println("--- Je supprime PC-001 via la vue ---");
    vue.supprimerMachine(m1);
    
    System.out.println("\n=== AFFICHAGE APRES SUPPRESSION DE PC-001 ===");
    facade.selectioncourant("MonReseau");
    facade.afficherReseauV1();
    System.out.println();
    
    // TEST 4 : Modification du nom via la vue
    System.out.println("--- Je modifie le nom du réseau via la vue ---");
    vue.modifierNom("ReseauRenomme");
    
    System.out.println("\n=== AFFICHAGE APRES MODIFICATION DU NOM ===");
    facade.selectioncourant("ReseauRenomme");
    facade.afficherReseauV1();
    System.out.println();
    
    // TEST 5 : Modification de l'adresse via la vue
    System.out.println("--- Je modifie l'adresse du réseau via la vue ---");
    int[] nouvelleAdresse = {10, 0, 0, 0};
    vue.modifierAdresse(nouvelleAdresse);
    
    System.out.println("\n=== AFFICHAGE APRES MODIFICATION DE L'ADRESSE ===");
    facade.selectioncourant("ReseauRenomme");
    facade.afficherReseauV1();
    System.out.println();
    
    // TEST 6 : Affichage avec la version 2
    System.out.println("=== AFFICHAGE FINAL AVEC VERSION 2 ===");
    facade.selectioncourant("ReseauRenomme");
    facade.afficherReseauV2();
    
  }
}