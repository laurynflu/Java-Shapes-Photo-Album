package album;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Lauryn Fluellen
 * CS 5004 HW8
 * The type Snapshot.
 */
public class Snapshot implements ISnapShot{
  private LocalDateTime ID;
  private String timestamp;
  private String description;
  private LinkedHashMap<String, IShape> snapshot;

  /**
   * Instantiates a new Snapshot.
   *
   * @param ID          the id for the snapshot
   * @param timestamp   the timestamp for the snapshot
   * @param description the description for the snapshot
   * @param snapshot    the snapshot hashmap
   */
  public Snapshot(LocalDateTime ID, String timestamp, String description, LinkedHashMap<String,
            IShape> snapshot){
    this.snapshot = snapshot;
    this.ID = ID;
    this.timestamp = timestamp;
    this.description = description;
  }

  /**
   * Instantiates a new Snapshot.
   *
   * @param ID        the id
   * @param timestamp the timestamp
   * @param snapshot  the snapshot
   */
  public Snapshot(LocalDateTime ID, String timestamp, LinkedHashMap<String, IShape> snapshot){
    this.snapshot = snapshot;
    this.ID = ID;
    this.timestamp = timestamp;
  }

  public LocalDateTime getID() {
    return this.ID;
  }

  public String getTimestamp() {
    return this.timestamp;
  }

  public String getDescription() {
    return this.description;
  }

  public LinkedHashMap<String, IShape> getSnapshot() {
    return this.snapshot;
  }

  public String getShape() {
    //build string to pass into the toString to get all the shape information
    StringBuilder shapeString = new StringBuilder();
    for (Map.Entry<String, IShape> set : snapshot.entrySet()) {
      shapeString.append(set.getValue().toString());
    }
    return shapeString.toString();
  }

  public List<IShape> getIShape() {
    List<IShape> shapeList = new ArrayList<>();
    for (Map.Entry<String, IShape> set : snapshot.entrySet()) {
      shapeList.add(set.getValue());
    }
    return shapeList;
  }

  public String toString() {
    return "Printing Snapshots\n" + "Snapshot ID: " + getID().toString() + "\nTimestamp: " + getTimestamp()
            + "\nDescription: " + getDescription() + "\nShape Information: \n" + getShape();
  }
}
