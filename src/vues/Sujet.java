package vues;

/**
 * interface sujet.
 */
public interface Sujet {
  
  Observeur observeurs = null;
  
  /**
   * attacher la vue;
   *
   * @param obs en param.
   */
  public void attacher(Observeur obs);
  
  
  /**
   * detacher la vue;
   *
   * @param obs en param.
   */
  public void detacher(Observeur obs);
  
  
  /**
   * notifier;
   */
  public void notifier();
  
  
  /**
   * get etat;
   *
   * @return sujet pour avoir l'etat.
   */
  public Sujet getEtat();

}
