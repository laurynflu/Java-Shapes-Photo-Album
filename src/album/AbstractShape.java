package album;

import java.util.Objects;

/**
 * The type Abstract shape.
 */
public abstract class AbstractShape implements IShape {
  private int horizontal;
  private int vertical;
  private String name;
  private String type;
  private Color color;
  private Point point;

  /**
   * Instantiates a new Abstract shape.
   *
   * @param name       the name of the shape
   * @param type       the type of shape
   * @param point      the point of the shape
   * @param color      the color of the shape
   * @param horizontal the horizontal of the shape
   * @param vertical   the vertical of the shape
   * @throws IllegalArgumentException the illegal argument exception for when the name or string    is empty or null
   */
  public AbstractShape(String name, String type, Point point, Color color, int horizontal,
                       int vertical) throws IllegalArgumentException {
    if (Objects.equals(name, "") || name == null || Objects.equals(type, "")
            || type == null) {
      throw new IllegalArgumentException("Shape name, color, and type must not be empty or null");
    }
    this.color = color;
    this.name = name;
    this.point = point;
    this.type = type;
    this.horizontal = horizontal;
    this.vertical = vertical;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getType() {
    return this.type;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  public int getHorizontal() {
    return this.horizontal;
  }

  public int getVertical() {
    return this.vertical;
  }

  public int resizeHorizontal(int d1) {
    this.horizontal = d1;
    return this.horizontal;
  }

  public int resizeVertical(int d2) {
    this.vertical = d2;
    return this.vertical;
  }

  @Override
  public void changeColor(Color color) {
    this.color = color;
  }

  @Override
  public Point move(Point point) {
    this.point = point;
    return this.point;
  }

  public Point getPoint() {
    return this.point;
  }
}
