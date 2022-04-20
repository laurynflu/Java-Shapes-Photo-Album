package album;

/**
 * The interface Shape.
 */
public interface IShape {
  /**
   * Gets name of shape.
   * @return the name
   */
  String getName();

  /**
   * Gets type of shape.
   * @return the type
   */
  String getType();

  /**
   * Gets color of the shape.
   * @return the color
   */
  Color getColor();

  /**
   * Gets point of the shape.
   * @return the point
   */
  Point getPoint();

  IShape copy();

  /**
   * Move point of the shape.
   * @param point the point
   * @return the point
   */
  Point move(Point point);

  /**
   * Change color of the shape.
   * @param color the color
   * @return the color
   */
  void changeColor(Color color);

  /**
   * Gets horizontal of the shape.
   * @return the horizontal
   */
  int getHorizontal();

  /**
   * Gets vertical of the shape.
   * @return the vertical
   */
  int getVertical();

  /**
   * Resize horizontal of the shape.
   * @param horizontal the horizontal
   * @return the double
   */
  int resizeHorizontal(int horizontal);

  /**
   * Resize vertical of the shape.
   * @param vertical the vertical
   * @return the double
   */
  int resizeVertical(int vertical);
}
