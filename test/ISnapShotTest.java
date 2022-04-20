import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Before;
import org.junit.Test;

import album.Color;
import album.IShape;
import album.Oval;
import album.Point;
import album.Rectangle;
import album.Snapshot;


/**
 * The type Snap shot test.
 */
public class ISnapShotTest {
  /**
   * The Snapshot.
   */
  LinkedHashMap<String, IShape> snapshot;
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
   * The Selfie.
   */
  Snapshot selfie;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    snapshot = new LinkedHashMap<>();
    point = new Point(3, 4);
    point2 = new Point(10, 22);
    oval = new Oval("Oval", "oval", point, new Color(4, 5, 6), 11,
            12);
    rectangle = new Rectangle("Rectangle", "rec", point2, new Color(9, 5, 2),
            5, 12);
    snapshot.put("Rectangle", rectangle);
    snapshot.put("Oval", oval);
    LocalDateTime now = LocalDateTime.of(2022, 4, 10, 11, 8,
            30);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    selfie = new Snapshot(now, format.format(now), "first selfie", snapshot);
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
   * Test get id.
   */
  @Test
  public void testGetID() {
    LocalDateTime now = LocalDateTime.of(2022, 04, 10, 11, 8,
            30);
    assertEquals(now, selfie.getID());
  }

  /**
   * Test get timestamp.
   */
  @Test
  public void testGetTimestamp() {
    LocalDateTime now = LocalDateTime.of(2022, 04, 10, 11, 8,
            30);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    assertEquals(format.format(now), selfie.getTimestamp());
  }

  /**
   * Test get description.
   */
  @Test
  public void testGetDescription() {
    assertEquals("first selfie", selfie.getDescription());
  }

  /**
   * Test get shape.
   */
  @Test
  public void testGetShape() {
    assertEquals("Name: Oval\n" + "Type: oval\n"
                    + "Center: (3,4), X radius: 11.0, Y radius: 12.5, Color: (4,5,6)\n"
                    + "Name: Rectangle\n" + "Type: rec\n"
                    + "Min corner: (10,22), Width: 5.4, Height: 12.5, Color: (9,5,2)\n",
            selfie.getShape());
  }

  /**
   * Test get snapshot.
   */
  @Test
  public void TestGetSnapshot() {
    assertEquals("{Oval=Name: Oval\n" +
            "Type: oval\n" +
            "Center: (3,4), X radius: 11.0, Y radius: 12.5, Color: (4,5,6)\n" +
            ", Rectangle=Name: Rectangle\n" +
            "Type: rec\n" +
            "Min corner: (10,22), Width: 5.4, Height: 12.5, Color: (9,5,2)\n" +
            "}", selfie.getSnapshot().toString());
  }
}