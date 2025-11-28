package reseau;

import architecture.ElementsVisites;
import architecture.Visiteurs;

/**
 * La classe Machine.
 */
public class Machine implements ElementsVisites{
  /*
   *
   */
  private Integer numeroIp;
  /*
   *
   */
  private String nom;
  
  /**
   * le siwtch.
   */
  private Switch leSwitch;

  /**
   * Machine.
   *
   * @param numeroIp .
   * @param nom      .
   */
  public Machine(Integer numeroIp, String nom, Switch leswitch) {
    this.numeroIp = numeroIp;
    this.nom = nom;
    this.leSwitch = leswitch;
  }

  /**
   * getter.
   *
   * @return le numéro IP de la machine.
   */
  public Integer getNumeroIp() {
    return numeroIp;
  }

  /**
   * set le numéro IP.
   *
   */
  public void setNumeroIp(Integer numeroIp) {
    this.numeroIp = numeroIp;
  }
  
  
  /**
   * getter.
   *
   * @return le switch de la machine.
   */
  public Switch getSwitch() {
    
    return this.leSwitch;
  }

  /**
   * getter.
   *
   * @return le nom de la machine.
   */
  public String getNom() {
    return nom;
  }

  /**
   * set le nom.
   *
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  @Override
  public String applique(Visiteurs visiteur) {
    
    return visiteur.afficher(this);  
    
  }
}