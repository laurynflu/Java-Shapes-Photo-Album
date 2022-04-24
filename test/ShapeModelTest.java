import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import album.Color;
import album.IShape;
import album.Point;
import album.ShapeModel;
import album.Snapshot;

import static org.junit.Assert.*;

/**
 * The type Shape model test.
 */
public class ShapeModelTest {
    /**
     * The Point.
     */
    Point point;
    /**
     * The Point 2.
     */
    Point point2;
    /**
     * The Shape model.
     */
    ShapeModel shapeModel;

    /**
     * Sets up.
     */
    @Before
  public void setUp() {
    point = new Point(3, 4);
    point2 = new Point(10, 22);
    shapeModel = new ShapeModel();
  }

    /**
     * Test create shape.
     */
    @Test
  public void testCreateShape() {
    shapeModel.createShape("ov", "oval", point2, 2, 3, 4, 19,
            32);
    assertEquals("Name: ov\n"
            + "Type: oval\n"
            + "Center: (10,22), X radius: 19.0, Y radius: 32.0, Color: (2,3,4)\n\n",
            shapeModel.getDescription());
  }

    /**
     * Test create bad shape.
     */
    @Test(expected = IllegalArgumentException.class)
  public void testCreateBadShape() {
    shapeModel.createShape("", "oval", point2, 2, 3, 4, 19,
            32);
    shapeModel.createShape(null, "oval", point2, 2, 3, 4, 19,
            32);
    shapeModel.createShape("ov", "", point2, 2, 3, 4, 19,
            32);
    shapeModel.createShape("ov", null, point2, 2, 3, 4, 19,
            32);
  }

    /**
     * Test move.
     */
    @Test
  public void testMove() {
    shapeModel.createShape("ov", "oval", point2, 2, 3, 4, 19,
            32);
    shapeModel.move("ov", point);
    assertEquals("Name: ov\n" +
                    "Type: oval\n" +
                    "Center: (3,4), X radius: 19.0, Y radius: 32.0, Color: (2,3,4)\n\n",
            shapeModel.getDescription());
 }

    /**
     * Test change color.
     */
    @Test
  public void testChangeColor() {
    shapeModel.createShape("ov", "oval", point2, 2, 3, 4, 19,
            32);
    shapeModel.changeColor("ov", new Color(5, 6, 7));
    assertEquals("(5,6,7)", shapeModel.getShape("ov").getColor().toString());
  }

    /**
     * Test resize.
     */
    @Test
  public void testResize() {
    shapeModel.createShape("ov", "oval", point2, 2, 3, 4, 19,
            32);
    shapeModel.resize("ov", -3, 12);
    assertEquals("Name: ov\n"
            + "Type: oval\n"
            + "Center: (10,22), X radius: -3.0, Y radius: 12.0, Color: (2,3,4)\n" + "\n",
            shapeModel.getDescription());
  }

    /**
     * Test remove.
     */
    @Test
  public void testRemove() {
    shapeModel.createShape("ov", "oval", point2, 2, 3, 4, 19,
            32);
    shapeModel.createShape("rec", "rectangle", point, 5, 6, 9, 9,
            -32);
    shapeModel.remove("ov");
    assertEquals("Name: rec\n" +
                    "Type: rectangle\n" +
                    "Min corner: (3,4), Width: 9.0, Height: -32.0, Color: (5,6,9)\n\n",
            shapeModel.getDescription());
  }

    /**
     * Test get log.
     */
    @Test
  public void testGetLog() {
    shapeModel.createShape("ov", "oval", point2, 2, 3, 4, 19,
            32);
    shapeModel.move("ov", point2);
    shapeModel.changeColor("ov", new Color(5, 2, 1));
    assertEquals("Create (2,3,4) oval ov with center at (10,22), radius 19.0 and 32.0\n"
            + "ov moves to (10,22)\n" + "ov changes from (5,2,1) to (5,2,1)\n",
            shapeModel.getLog());
  }

    /**
     * Test snapshot.
     */
    @Test
  public void testSnapshot() {
    shapeModel.createShape("ov", "oval", point2, 2, 3, 4, 19,
            32);
    shapeModel.snapShot("ov");
    assertEquals("Create (2,3,4) oval ov with center at (10,22), radius 19.0 and 32.0\n" +
                    "Take a Snapshot",
            shapeModel.getLog());
  }
}