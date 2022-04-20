import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import album.Color;
import album.IShape;
import album.Oval;
import album.Point;
import album.Rectangle;

/**
 * The type Shape test.
 */
public class IShapeTest {
  /**
   * The Oval.
   */
  IShape oval;
  /**
   * The Rectangle.
   */
  IShape rectangle;
  /**
   * The Point.
   */
  Point point;
  /**
   * The Point 2.
   */
  Point point2;

  /**
   * Test set up.
   */
  @Before
  public void testSetUp() {
    point = new Point(3, 4);
    point2 = new Point(10, 22);
    oval = new Oval("Oval", "oval", point, new Color(4, 5, 6), 11,
            12);
    rectangle = new Rectangle("Rectangle", "rec", point2, new Color(9, 5, 2),
            5, 12);
  }

  /**
   * Test bad set up.
   */
  @Test(expected = IllegalArgumentException.class)
    public void testBadSetUp() {
    point2 = new Point(10, 22);
    IShape oval = new Oval("", "oval", point2, new Color(4, 5, 6),
            10, 2);
    IShape oval2 = new Oval(null, "oval", point2, new Color(4, 5, 6),
            10, 2);
    IShape rectangle = new Rectangle("Rectangle", "", point2,
            new Color(4, 5, 6), 10, 2);
    IShape rectangle2 = new Rectangle("Rectangle", null, point2,
            new Color(4, 5, 6), 10, 2);
  }

  /**
   * Test get name.
   */
  @Test
  public void testGetName() {
    assertEquals("Oval", oval.getName());
    assertEquals("Rectangle", rectangle.getName());
  }

  /**
   * Test get type.
   */
  @Test
  public void testGetType() {
    assertEquals("oval", oval.getType());
    assertEquals("rec", rectangle.getType());
  }

  /**
   * Test get color.
   */
  @Test
  public void testGetColor() {
    assertEquals("(4,5,6)", oval.getColor().toString());
    assertEquals("(9,5,2)", rectangle.getColor().toString());
  }

  /**
   * Test get point.
   */
  @Test
  public void testGetPoint() {
    assertEquals("(3,4)", oval.getPoint().toString());
    assertEquals("(10,22)", rectangle.getPoint().toString());
  }

  /**
   * Test move.
   */
  @Test
  public void testMove() {
    Point recPoint = new Point(9, -1);
    Point ovalPoint = new Point(-23, 2);
    rectangle.move(recPoint);
    oval.move(ovalPoint);
    assertEquals("(9,-1)", rectangle.getPoint().toString());
    assertEquals("(-23,2)", oval.getPoint().toString());
  }

  /**
   * Test change color.
   */
  @Test
  public void testChangeColor() {
    rectangle.changeColor(new Color(3, 4, 6));
    oval.changeColor(new Color(7, 8, 9));
    assertEquals("(7,8,9)", oval.getColor().toString());
    assertEquals("(3,4,6)", rectangle.getColor().toString());
  }

  /**
   * Test get horizontal.
   */
  @Test
  public void testGetHorizontal() {
    assertEquals(5.4, rectangle.getHorizontal(), 0.001);
    assertEquals(11.0, oval.getHorizontal(), 0.001);
  }

  /**
   * Test get vertical.
   */
  @Test
  public void testGetVertical() {
    assertEquals(12.5, rectangle.getVertical(), 0.001);
    assertEquals(12.5, oval.getVertical(), 0.001);
  }

  /**
   * Test resize horizontal.
   */
  @Test
  public void testResizeHorizontal() {
    rectangle.resizeHorizontal(13);
    oval.resizeHorizontal(-2);
    assertEquals(13.2, rectangle.getHorizontal(), 0.001);
    assertEquals(-2.3, oval.getHorizontal(), 0.001);
  }

  /**
   * Test resize vertical.
   */
  @Test
  public void testResizeVertical() {
    rectangle.resizeVertical(4);
    oval.resizeVertical(-8);
    assertEquals(4.8, rectangle.getVertical(), 0.001);
    assertEquals(-8.3, oval.getVertical(), 0.001);
  }
}