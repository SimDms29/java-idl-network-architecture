package architecture;

import reseau.Machine;
import reseau.Reseau;
import reseau.Switch;

/**
 * un visiteur.
 */

public class VisiteurSwitch implements Visiteurs{
  
  @Override
  public String afficher(Machine m) { 
    
    String s = "Machine : " + m.getNom() +
        ", de numéro IP : " + m.getNumeroIp();
    
    s += "\n";

    return s;
    
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
        
    str += "Nom du reseau : " + r.getNom();
    str += "\n";
    
    str += "Nous allons maintenant afficher les Switch pour "
        + "chaque Switch nous afficherons les machines qui lui sont connectées";
    str += "\n";
    
    for (Switch s : r.getSwitch()) {
      
      str += "\n";
      str += "Nouveau Switch ----------------------";
      str += "\n";
      str += s.applique(this);
      
      for (Machine m : r.getMachine()) {
        
        if (m.getSwitch().equals(s)) {
          
          str += "\n";
          str += "Machine liée à ce Switch : ";
          str += m.applique(this);
          
        }
      }
    }
    
    return str;
    
  }

}
