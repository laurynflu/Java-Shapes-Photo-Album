package album;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The interface Snap shot.
 */
public interface ISnapShot {

  /**
   * Gets snapshot ID.
   *
   * @return the id
   */
  LocalDateTime getID();

  /**
   * Gets snapshot timestamp.
   *
   * @return the timestamp
   */
  String getTimestamp();

  /**
   * Gets snapshot description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Gets snapshot shape.
   *
   * @return the shape
   */
  String getShape();

  /**
   * Gets snapshot.
   *
   * @return the snapshot
   */
  LinkedHashMap<String, IShape> getSnapshot();

  /**
   * Gets i shape.
   *
   * @return the i shape
   */
  List<IShape> getIShape();
}
