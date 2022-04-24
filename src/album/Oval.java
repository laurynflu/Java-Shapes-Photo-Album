package album;

/**
 * Lauryn Fluellen
 * CS 5004 HW8
 * The type Oval.
 */
public class Oval extends AbstractShape {

  /**
   * Instantiates a new Oval.
   *
   * @param name       the name of the oval
   * @param type       the type of the oval
   * @param center     the center of the oval
   * @param color      the color of the oval
   * @param horizontal the x radius of the oval
   * @param vertical   the y radius of the oval
   */
  public Oval(String name, String type, Point center, Color color, int horizontal,
              int vertical) {
    super(name, type, center, color, horizontal, vertical);
  }

  public Rectangle copy() {
    return new Rectangle(super.getName(), super.getType(), super.getPoint(), super.getColor(),
            super.getHorizontal(), super.getVertical());
  }

  public String toString() {
    return "Name: " + getName() + "\nType: " + getType() + "\nCenter: "
            + getPoint() + "," + " X radius: " + this.getHorizontal() + "," + " Y radius: "
            + this.getVertical() + "," + " Color: " + this.getColor().toString() + "\n";
  }
}
