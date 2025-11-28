package vues;

/**
 * Interfce d'un observeur pour un sujet.
 */
public interface Observeur {
  
  /**
   * mets a jour l'obs quand le sujet a ete modifié.
   */
  public void update();

}
