package album;

import java.util.ArrayList;
import java.util.Iterator;
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
public class ShapeModel implements IShapeModel {
  /**
   * The Shapes.
   */
  LinkedHashMap<String, IShape> shapes = new LinkedHashMap<>();
  /**
   * The Key set.
   */
  Set<String> keySet = shapes.keySet();

  /**
   * The Snapshots.
   */
  LinkedHashMap<LocalDateTime, Snapshot> snapshots = new LinkedHashMap<>();
  /**
   * The Log.
   */
  List<String> log = new ArrayList<>();

  /**
   * Create new shape shape.
   *
   * @param name       the name of the shape
   * @param type       the type of the shape
   * @param point      the point of the shape
   * @param r          the r of the shape
   * @param g          the g of the shape
   * @param b          the b of the shape
   * @param horizontal the horizontal of the shape
   * @param vertical   the vertical of the shape
   * @return the shape
   * @throws IllegalArgumentException the illegal argument exception when the name or type    is null or empty
   */
  public void createShape(String name, String type, Point point, int r, int g, int b,
                          int horizontal, int vertical) throws IllegalArgumentException {
    if (Objects.equals(name, "") || name == null || Objects.equals(type, "")
            || type == null) {
      throw new IllegalArgumentException("Shape name and type must not be empty or null");
    }
    //create and add the concrete shapes if passed in and not null or empty
    if (type.equalsIgnoreCase("rectangle")) {
      addShape(name, new Rectangle(name, "rectangle", point, new Color(r, g, b), horizontal,
              vertical));

    } else if (type.equalsIgnoreCase("Oval")) {
      addShape(name, new Oval(name, "oval", point, new Color(r, g, b), horizontal, vertical));
  }
}

  /**
   * Add shape to albumn.
   *
   * @param name  the name of the shape
   * @param shape the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
//adds the new shape to the hashmap
  public void addShape(String name, IShape shape) throws IllegalArgumentException {
    //check to make sure the shape doesn't already exist & throw exception if name exists
    if (shapes.containsKey(name)) {
      throw new IllegalArgumentException("name already exists");
    }
    //if name not already in linkedhash add to linkedhash
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
   *
   * @param name  the name of the shape
   * @param point the new point
   */
  //Get the name of the shape from the hashmap and then modify the shape
  public void move(String name, Point point) {
    AtomicBoolean success = new AtomicBoolean(false);
    //go into linkedhash and find if name is equal to what is entered
    shapes.entrySet().forEach(entry -> {
      if (entry.getKey().equalsIgnoreCase(name)) {
        //if name is found log the move and move the shape to the new position
        log.add(entry.getValue().getName() + " moves " + "to " + point.toString() + "\n");
        entry.getValue().move(point);
        success.set(true);
      }});
    if (!success.get()) {
      throw new IllegalArgumentException("name is not in hashmap");
    }
  }

  /**
   * Change color of the shape.
   *
   * @param name  the name of the shape
   * @param color the color we are changing to
   * @throws IllegalArgumentException the illegal argument exception
   */
  //Get the name of the shape from the linkedhashmap and then modify the shape
  public void changeColor(String name, Color color) throws IllegalArgumentException {
    AtomicBoolean success = new AtomicBoolean(false);
    //go into linkedhash and find if name is equal to what is entered
    shapes.entrySet().forEach(entry -> {
      if (entry.getKey().equalsIgnoreCase(name)) {
        //add this instance to the log
        log.add(entry.getValue().getName() + " changes from " + entry.getValue().getColor()
                + " to " + color.toString() + "\n");
        //change shape color
        entry.getValue().changeColor(color);
        success.set(true);
      }
    });
    if (!success.get()) {
      throw new IllegalArgumentException("name is not in hashmap");
    }
  }

  /**
   * Gets shape from the shapes hashmap.
   *
   * @param name the name of the shape
   * @return the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  public IShape getShape(String name) throws IllegalArgumentException {
    for (String key : keySet) {
      if (key.equalsIgnoreCase(name)) {
        return shapes.get(key);
      }
    }
    return null;
  }

  /**
   * Resize the shape.
   *
   * @param name       the name of the shape
   * @param horizontal the horizontal of the shape
   * @param vertical   the vertical of the shape
   * @throws IllegalArgumentException the illegal argument exception
   */
  //Get the name of the shape from the hashmap and then modify the shape
  public void resize(String name, int horizontal, int vertical)
          throws IllegalArgumentException {
    AtomicBoolean success = new AtomicBoolean(false);
    //go into linkedhash and find if name is equal to what is entered
    shapes.entrySet().forEach(entry -> {
      if (entry.getKey().equalsIgnoreCase(name)) {
        //add this instance to the log
        log.add(entry.getValue().getName() + "changes width from "
                + entry.getValue().getHorizontal()+ " to " + horizontal + "\n");
        log.add(entry.getValue().getName() + "changes height from "
                + entry.getValue().getVertical() + " to " + vertical + "\n");
        //resize the shape with given horizontal and verticle
        entry.getValue().resizeHorizontal(horizontal);
        entry.getValue().resizeVertical(vertical);
        success.set(true);
      }
    });
    if (!success.get()) {
      throw new IllegalArgumentException("name is not in hashmap");
    }
  }

  /**
   * Remove the shape from the albumn.
   *
   * @param name the name of the shape
   */
  //Get the name of the shape from the hashmap and then modify the shape
  public void remove(String name) {
    //making an iterator for the shapes linkedhash
    Iterator<Map.Entry<String,IShape>> iter = shapes.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry<String,IShape> entry = iter.next();
      if(name.equalsIgnoreCase(entry.getKey())){
        //remove shape that matches name from the iterator
        iter.remove();
      }
    }
  }

  /**
   * Take a snapshot.
   *
   * @param description the description of the shape
   * @return the snapshot
   */
  public Snapshot snapShot(String description) {
    //create local time for ID in the snapshot
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    //create snapshot object using shapes list
    LinkedHashMap<String, IShape> copyShape = new LinkedHashMap<>();
    shapes.entrySet().forEach(entry -> {
      copyShape.put(entry.getKey(), entry.getValue().copy());
    });
    Snapshot snapshot = new Snapshot(now, now.format(format), description, copyShape);
    //if statement to prevent duplicate shape placement
    snapshots.put(now, snapshot);
    //add this instance to the log
    log.add("Take a Snapshot\n");
    return snapshot;
  }

  /**
   * Snap shot snapshot.
   *
   * @return the snapshot
   */
  public Snapshot snapShot() {
    //create local time for ID in the snapshot
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    DateTimeFormatter format2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    //create snapshot object using shapes list
    LinkedHashMap<String, IShape> copyShape = new LinkedHashMap<>();
    shapes.entrySet().forEach(entry -> {
      copyShape.put(entry.getKey(), entry.getValue().copy());
    });
    Snapshot snapshot = new Snapshot(now, now.format(format), copyShape);
    //if statement to prevent duplicate shape placement
    snapshots.put(now, snapshot);
    //add this instance to the log
    log.add("Take a Snapshot\n");
    return snapshot;
  }

  /**
   * Gets snapshots.
   *
   * @return the snapshots
   */
  public LinkedHashMap<LocalDateTime, Snapshot> getSnapshots() {
    //return linkedhashmap of snapshots
    return snapshots;
  }

  /**
   * Display snapshot string.
   *
   * @return the string
   */
  public String displaySnapshot() {
    //return all snapshots in the hashmap
    StringBuilder snapshotString = new StringBuilder();
    //loop through libkedhashmap and append values to stringbuilder to return
    snapshots.entrySet().forEach(entry -> {
      snapshotString.append(entry.getValue().toString());
    });
    return snapshotString.toString();
  }

  /**
   * Gets description of the shape.
   *
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
   *
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
