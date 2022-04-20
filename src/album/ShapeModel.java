package album;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Lauryn Fluellen
 * CS 5004 HW8
 * The type Shape model.
 */
public class ShapeModel {
  /**
   * The Shapes.
   */
  HashMap<String, IShape> shapes = new HashMap<>();
  //Set<String> keySet = shapes.keySet();
  //List<String> listKeys
  //        = new ArrayList<String>(keySet);

  /**
   * The Snapshots.
   */
  HashMap<LocalDateTime, Snapshot> snapshots = new HashMap<>();
  /**
   * The Log.
   */
  List<String> log = new ArrayList<>();

  /**
   * Create new shape shape.
   * @param name       the name of the shape
   * @param type       the type of the shape
   * @param point      the point of the shape
   * @param r          the r of the shape
   * @param g          the g of the shape
   * @param b          the b of the shape
   * @param horizontal the horizontal of the shape
   * @param vertical   the vertical of the shape
   * @return the shape
   * @throws IllegalArgumentException the illegal argument exception when the name or type
   *    is null or empty
   */
//Expect user to create shape or take type of shape from user to create it
  public void createShape(String name, String type, Point point, int r, int g, int b,
                          int horizontal, int vertical) throws IllegalArgumentException {
    if (Objects.equals(name, "") || name == null || Objects.equals(type, "")
            || type == null) {
      throw new IllegalArgumentException("Shape name and type must not be empty or null");
    }
    //create and add the concrete shapes if passed in
    if (type.equalsIgnoreCase("rectangle")) {
      addShape(name, new Rectangle(name, "rectangle", point, new Color(r, g, b), horizontal,
              vertical));

    } else if (type.equalsIgnoreCase("Oval")) {
      addShape(name, new Oval(name, "oval", point, new Color(r, g, b), horizontal, vertical));
  }
}

  /**
   * Add shape to albumn.
   * @param name  the name of the shape
   * @param shape the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
//adds the new shape to the hashmap
  public void addShape(String name, IShape shape) throws IllegalArgumentException {
    //check to make sure the shape doesnt already exist & throw exception if name exists
    if (shapes.containsKey(name)) {
      throw new IllegalArgumentException("name already exists");
    }
    //if name not already in list add to list
    shapes.put(name, shape);
    //add this instance to the log
    if (shape.getType().equalsIgnoreCase("oval")) {
      log.add("Create " + shape.getColor().toString() + " " + shape.getType() + " "
              + shape.getName() + " with center at " + shape.getPoint().toString() + ", radius "
              + shape.getHorizontal() + " and " + shape.getVertical() + "\n");
    } else {
      log.add("Create " + shape.getColor().toString() + " " + shape.getType() + " with corner at "
              + shape.getPoint().toString() + ", width " + shape.getHorizontal()
              + " and height " + shape.getVertical() + "\n");
    }
  }

  /**
   * Move the shape to a new point.
   * @param name  the name of the shape
   * @param point the new point
   */
//Get the name of the shape from the hashmap and then modify the shape
  public void move(String name, Point point) {
    boolean success = false;
    for (Map.Entry<String, IShape> entry : shapes.entrySet()) {
      if (entry.getKey().equalsIgnoreCase(name)) {
        entry.getValue().move(point);
        log.add(entry.getValue().getName() + " moves " + "to " + point.toString() + "\n");
      }
        if (!success) {
          throw new IllegalArgumentException("name is not in hashmap");
        }
      }
    }

  /**
   * Change color of the shape.
   * @param name  the name of the shape
   * @param color the color we are changing to
   * @throws IllegalArgumentException the illegal argument exception
   */
//Get the name of the shape from the hashmap and then modify the shape
  public void changeColor(String name, Color color) throws IllegalArgumentException {
    boolean success = false;
    for (Map.Entry<String, IShape> entry : shapes.entrySet()) {
      if (entry.getKey().equalsIgnoreCase(name)) {
        //add this instance to the log
        log.add(entry.getValue().getName() + " changes from " + entry.getValue().getColor()
                + " to " + color.toString() + "\n");
        entry.getValue().changeColor(color);
        success = true;
      }
    }
    if (!success) {
      throw new IllegalArgumentException("name is not in hashmap");
    }
  }

  /**
   * Gets shape from the shapes hashmap.
   * @param name the name of the shape
   * @return the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  public IShape getShape(String name) throws IllegalArgumentException {
    for (Map.Entry<String, IShape> entry : shapes.entrySet()) {
      if (entry.getKey().equalsIgnoreCase(name)) {
        return entry.getValue();
      }
    }
    return null;
  }

  /**
   * Resize the shape.
   * @param name       the name of the shape
   * @param horizontal the horizontal of the shape
   * @param vertical   the vertical of the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
//Get the name of the shape from the hashmap and then modify the shape
  public void resize(String name, int horizontal, int vertical)
          throws IllegalArgumentException {
    //loop through shapes hashmap to get value and modify shape
    boolean success = false;
    for (Map.Entry<String, IShape> entry : shapes.entrySet()) {
      if (entry.getKey().equalsIgnoreCase(name)) {
        //add this instance to the log
        log.add(entry.getValue().getName() + "changes width from "
                + entry.getValue().getHorizontal()+ " to " + horizontal + "\n");
        log.add(entry.getValue().getName() + "changes height from "
                + entry.getValue().getVertical() + " to " + vertical + "\n");
        entry.getValue().resizeHorizontal(horizontal);
        entry.getValue().resizeVertical(vertical);
        success = true;
      }
    }
    if (!success) {
      throw new IllegalArgumentException("name is not in hashmap");
    }
  }

  /**
   * Remove the shape from the albumn.
   * @param name the name of the shape
   */
//Get the name of the shape from the hashmap and then modify the shape
  public void remove(String name) {
    for (Map.Entry<String, IShape> entry : shapes.entrySet()) {
      if (entry.getKey().equalsIgnoreCase(name)) {
        log.add(name + " removed\n");
        shapes.remove(entry.getKey().equalsIgnoreCase(name));
      }
    }
  }

  /**
   * Take a snapshot.
   * @param description the description of the shape
   * @return the snapshot
   */
  public Snapshot snapShot(String description) {
    //create local time into the snapshot (google local time in java)
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    //create snapshot object using shapes list
    HashMap<String, IShape> copyShape = new HashMap<>();
    for (Map.Entry<String, IShape> entry : shapes.entrySet()) {
      copyShape.put(entry.getKey(), entry.getValue().copy());
    }
    Snapshot snapshot = new Snapshot(now, now.format(format), description, copyShape);
    //if statement to prevent duplicate shape placement
    snapshots.put(now, snapshot);
    //add this instance to the log
    log.add("Take a Snapshot\n");
    return snapshot;
  }

  public Snapshot snapShot() {
    //create local time into the snapshot (google local time in java)
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    //create snapshot object using shapes list
    HashMap<String, IShape> copyShape = new HashMap<>();
    for (Map.Entry<String, IShape> entry : shapes.entrySet()) {
      copyShape.put(entry.getKey(), entry.getValue().copy());
    }
    Snapshot snapshot = new Snapshot(now, now.format(format), copyShape);
    //if statement to prevent duplicate shape placement
    snapshots.put(now, snapshot);
    //add this instance to the log
    log.add("Take a Snapshot\n");
    return snapshot;
  }

  /**
   * Gets snapshots.
   * @return the snapshots
   */
  public HashMap<LocalDateTime, Snapshot> getSnapshots() {
    //return hashmap
    return snapshots;
  }

  /**
   * Display snapshot string.
   * @return the string
   */
  public String displaySnapshot() {
    //return all snapshots in the hashmap
    StringBuilder snapshotString = new StringBuilder();
    //loop through hashmap and return value tostring
    for (Map.Entry<LocalDateTime, Snapshot> set : snapshots.entrySet()) {
      snapshotString.append(set.getValue().toString());
    }
    return snapshotString.toString();
  }

  /**
   * Gets description of the shape.
   * @return the description of the shape
   */
  public String getDescription() {
    StringBuilder description = new StringBuilder();
    //loop through hashmap and return value tostring
    for (IShape shape : shapes.values()) {
      description.append(shape.toString()).append("\n");
    }
    return description.toString();
  }

  /**
   * Gets log of all commands run.
   * @return the log
   */
  public String getLog() {
    //create log of all the commands run and return
    StringBuilder logString = new StringBuilder();
    for (String logs : log) {
      logString.append(logs);
    }
    return logString.toString();
  }
}
