package factory;
import com.google.gson.Gson;

import reseau.Machine;
import reseau.Reseau;
import reseau.Switch;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Constructeur de réseaux.
 */
public class FactoryJson {

  /**
   * Nombre de machines créées.
   */
  private int nbmachine;

  /**
   * Passerelle standard.
   */
  private int passerelle = 1;

  /**
   * Masque standard.
   */
  private int masque = 24;

  /**
   * Factory de reseau - méthode de la factory créée initialement.
   *
   * @param nom     nom du réseau.
   * @param adresse adresse IP.
   * @param nbM     nombre de machines.
   * @return le réseau créé.
   */
  public Reseau reseauStandard(String nom, int[] adresse, int nbM) {

    Switch s = new Switch(2, true);
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
   * setteur du masque.
   *
   * @param nb , int concernant le masque.
   */
  public void setMasque(int nb) {
    if (! (nb == 16 || nb == 24 || nb == 25)) {
      return;
    }
    this.masque = nb;
  }
  
  /**
   * getteur.
   *
   * @return l'entier qui correspond au masque.
   */
  public int getMasque() {
    
    return this.masque;
  }

  /**
   * Crée un réseau à partir d'un fichier JSON.
   *
   * @param cheminFichier à lire.
   * @return le réseau créé ou null en cas d'erreur.
   */
  public Set<Reseau> reseauDepuisJson(String cheminFichier) {
    try {
      // Charger le fichier JSON
      InputStream is = FactoryJson.class.getResourceAsStream(cheminFichier);
      if (is == null) {
        System.err.println("Fichier non trouvé: " + cheminFichier);
        return null;
      }

      // Parser le JSON directement en List de Map
      Gson gson = new Gson();
      Reader reader = new InputStreamReader(is);
      @SuppressWarnings("unchecked")
      List<Map<String, Object>> dataList = gson.fromJson(reader, List.class);
      reader.close();

      // Créer le Set pour stocker les réseaux
      Set<Reseau> reseaux = new HashSet<>();

      // Parcourir chaque réseau dans le JSON
      for (Map<String, Object> data : dataList) {
        // Extraire les données
        String nom = (String) data.get("__nom__");
        @SuppressWarnings("unchecked")
        List<Double> adresseList = (List<Double>) data.get("__adresse__");
        int[] adresse = new int[adresseList.size()];
        for (int i = 0; i < adresseList.size(); i++) {
          adresse[i] = adresseList.get(i).intValue();
        }
        int nombreMachines = ((Double) data.get("nombreMachines")).intValue();

        // Ajouter le réseau au Set
        reseaux.add(reseauStandard(nom, adresse, nombreMachines));
      }

      return reseaux;

    } catch (Exception e) {
      System.err.println("Erreur lors du chargement du JSON: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Cette méthode renvoie le nombre de machines.
   *
   * @return le nombre de machines créées.
   */
  public int nbMachines() {
    return this.nbmachine;
  }
}