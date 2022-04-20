package album;

/**
 * Lauryn Fluellen
 * CS 5004 HW8
 * The type Point.
 */
public class Point {
  private int x, y;

  /**
   * Instantiates a new Point.
   * @param x the x
   * @param y the y
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets y.
   * @return the y
   */
  public int getY() {
    return this.y;
  }

  /**
   * Gets x.
   * @return the x
   */
  public int getX() {
    return this.x;
  }

  public String toString() {
    return "(" + getX() + "," + getY() + ")";
  }

}
