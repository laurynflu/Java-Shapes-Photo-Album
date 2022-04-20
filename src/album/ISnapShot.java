package album;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * The interface Snap shot.
 */
public interface ISnapShot {

  /**
   * Gets snapshot ID.
   * @return the id
   */
  LocalDateTime getID();

  /**
   * Gets snapshot timestamp.
   * @return the timestamp
   */
  String getTimestamp();

  /**
   * Gets snapshot description.
   * @return the description
   */
  String getDescription();

  /**
   * Gets snapshot shape.
   * @return the shape
   */
  String getShape();

  /**
   * Gets snapshot.
   * @return the snapshot
   */
  HashMap<String, IShape> getSnapshot();

  List<IShape> getIShape();
}
