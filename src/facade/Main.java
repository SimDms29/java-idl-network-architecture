package facade;

import reseau.Reseau;

/**
 * Classe de test pour la Facade.
 */
public class Main {
  
  /**
   * main.
   *
   * @param args e java.
   */
  public static void main(String[] args) {
    System.out.println("=== Test de la Facade ===\n");
    
    // Création de la facade
    Facade facade = new Facade();
    
    // Test 1: Créer un réseau
    System.out.println("--- Test 1: Création d'un réseau ---");
    int[] adresse1 = {192, 168, 1, 0};
    boolean resultat1 = facade.creerReseau("Reseau-Principal", adresse1, 10, true);
    
    
    
    System.out.println("Réseau créé: " + resultat1);
    System.out.println();
    
    // Test 2: Créer un deuxième réseau
    System.out.println("--- Test 2: Création d'un second réseau ---");
    int[] adresse2 = {10, 0, 0, 0};
    boolean resultat2 = facade.creerReseau("Reseau-Secondaire", adresse2, 5, false);
    System.out.println("Réseau créé: " + resultat2);
    System.out.println();
    
    // Test 3: Sélectionner un réseau courant
    System.out.println("--- Test 3: Sélection du réseau courant ---");
    facade.selectioncourant("Reseau-Principal");
    Reseau courant = facade.getcourant();
    System.out.println("Réseau courant: " + (courant != null ? courant : "null"));
    System.out.println();
    
    // Test 4: Afficher les réseaux (Vue 1)
    System.out.println("--- Test 4: Affichage Vue Liste ---");
    facade.afficherReseauV1();
    System.out.println();
    
    // Test 5: Afficher les réseaux (Vue 2)
    System.out.println("--- Test 5: Affichage Vue Switch ---");
    facade.afficherReseauV2();
    System.out.println();
    
    // Test 6: Modifier un réseau
    System.out.println("--- Test 6: Modification du réseau ---");
    int[] nouvelleAdresse = {192, 168, 2, 0};
    facade.modifierReseau("Reseau-Principal", nouvelleAdresse, 15, true);
    System.out.println("Réseau modifié");
    facade.notifier();
    System.out.println();
    
    // Test 7: Créer un réseau via JSON
    System.out.println("--- Test 7: Création via JSON ---");
    boolean resultatJson = facade.creerReseauViaJson("config_reseau.json");
    System.out.println("Réseau créé via JSON: " + resultatJson);
    if (resultatJson) {
      facade.notifier();
    }
    System.out.println();
    
    // Test 8: Obtenir tous les réseaux
    System.out.println("--- Test 8: Récupération des réseaux ---");
    System.out.println("Nombre de réseaux: " + facade.getReseau().size());
    System.out.println();
    
   
    // Test 9: Supprimer un réseau
    System.out.println("--- Test 9: Suppression du réseau ---");
    facade.supprimerReseau();
    System.out.println("Réseau supprimé");
    System.out.println();
    
    
  }
}