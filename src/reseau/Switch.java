package reseau;

import architecture.ElementsVisites;
import architecture.Visiteurs;

/**
 * switch.
 */

public class Switch implements ElementsVisites{
  /*
   * 
   */
  private Integer numeroIp;
  /*
   * 
   */
  private Boolean estManage;
  

  /**
   * Switch.
   *
   * @param numeroIp oui.
   * @param estManage oui.
   */
  public Switch(Integer numeroIp, Boolean estManage) {
    this.numeroIp = numeroIp;
    this.estManage = estManage;
  }

  public Integer getNumeroIp() {
    return numeroIp;
  }

  public void setNumeroIp(Integer numeroIp) {
    this.numeroIp = numeroIp;
  }

  public Boolean getEstManage() {
    return estManage;
  }

  public void setEstManage(Boolean estManage) {
    this.estManage = estManage;
  }

  
  @Override
  
  public String toString() {
    
    String res = "" ;
    
    res += "Switch de numéro Ip : " + this.numeroIp;
    
    return res;
    
  }

  @Override
  public String applique(Visiteurs visiteur) {
    
    return visiteur.afficher(this);
    
  }

 

}