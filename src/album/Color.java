package album;

/**
 * Lauryn Fluellen
 * CS 5004 HW8
 * The class Color.
 */
public class Color {
  private int r;
  private int g;
  private int b;

  /**
   * Instantiates a new Color object.
   * @param r the r
   * @param g the g
   * @param b the b
   */
  public Color(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets r value.
   * @return the r
   */
  public int getR() {
    return r;
  }

  /**
   * Gets b value.
   * @return the b
   */
  public int getB() {
    return b;
  }

  /**
   * Gets g value.
   * @return the g
   */
  public int getG() {
    return g;
  }

  public String toString() {
    return "(" + r + "," + g + "," + b + ")";
  }
}
