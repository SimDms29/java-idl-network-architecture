package architecture;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import reseau.Machine;
import reseau.Reseau;
import reseau.Switch;

/**
 * un visiteur.
 */
public class VisiteurAffichageListeReseau implements Visiteurs {

  @Override
  public String afficher(Machine m) {
    
    String str = "";
    
    Switch sw = m.getSwitch();  
    String s = sw.toString();  

    str += "\n";
    str = "Machine : " + m.getNom() 
        + ", de numéro IP : " + m.getNumeroIp() +
        " et reliée au Switch : " + s;
    str += "\n";
      
    return str;
    
  }

  @Override
  public String afficher(Switch s) {
    
    return s.toString();
    
  }

  @Override
  public String afficher(Reseau r) {
        
    String str = "";
    
    str += "\n";
    str += "Nouvel affichage ! ----------------------- ";
    str += "\n";
    str += "\n";
    str += "Nom du reseau : " + r.getNom();
    str += "\n";
    str += "\n";
    str += "Caractéristiques : Adresse = " 
        + Arrays.toString(r.getAdresse()) 
        + ", Passerelle = " 
        + r.getPasserelle() 
        + ", Masque = " 
        + r.getMasquerSousReseau() 
        + ".";
    str += "\n";
    str += "\n";
    
    Set<Machine> m = new HashSet<>();
    m = r.getMachine();

    for (Machine ma : m) {
      
      str += ma.applique(this);
      str += "\n";
      
    }
    
    Switch[] s = r.getSwitch();

    for (Switch sw : s) {
      
      str += sw.applique(this);
      str += "\n";
      
    }
    
    return str;
    
  }

}
