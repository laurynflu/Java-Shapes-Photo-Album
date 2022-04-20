package album;

/**
 * Lauryn Fluellen
 * CS 5004 HW8
 * The type Rectangle.
 */
public class Rectangle extends AbstractShape {

  /**
   * Instantiates a new Rectangle.
   * @param name       the name of the rectangle
   * @param type       the type of the rectangle
   * @param minCorner  the min corner of the rectangle
   * @param color      the color of the rectangle
   * @param horizontal the horizontal of the rectangle
   * @param vertical   the vertical of the rectangle
   */
  public Rectangle(String name, String type, Point minCorner, Color color, int horizontal,
                   int vertical) {
    super(name, type, minCorner, color, horizontal, vertical);
  }

  public Rectangle copy() {
    return new Rectangle(super.getName(), super.getType(), super.getPoint(), super.getColor(),
            super.getHorizontal(), super.getVertical());
  }

  public String toString() {
    return "Name: " + getName() + "\nType: " + getType() + "\nMin corner: "
            + getPoint() + ", " + "Width: " + this.getHorizontal() + "," + " Height: "
            + this.getVertical() + "," + " Color: " + this.getColor().toString() + "\n";
  }
}
