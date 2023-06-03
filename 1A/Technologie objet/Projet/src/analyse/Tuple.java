package analyse;

/**
 * Permet de stocker un couple d'integers.
 * On peut récuperer les valeurs avec x et y.
 */ 
public class Tuple { 

  /** la valeur x du début du motif */
  public final Integer x;
  /** la valeur y de la fin du motif */
  public final Integer y; 

  /** Constructeur.
   * @param x la valeur x du début du motif
   * @param y la valeur y de la fin du motif
   */
  public Tuple(Integer x, Integer y) { 
      this.x = x; 
      this.y = y; 
    }
  
  /** Retourne la valeur x du début du motif.
   * @return la valeur x du début du motif
   */
  public Integer getX() {
	    return this.x;
  }
  
  /** Retourne la valeur y de la fin du motif.
   * @return la valeur y de la fin du motif
   */
  public Integer getY() {
	  return this.y;
  }
}
