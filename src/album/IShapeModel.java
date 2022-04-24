package album;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

/**
 * The interface Shape model.
 */
public interface IShapeModel {
    /**
     * Create shape.
     *
     * @param name       the name
     * @param type       the type
     * @param point      the point
     * @param r          the r
     * @param g          the g
     * @param b          the b
     * @param horizontal the horizontal
     * @param vertical   the vertical
     * @throws IllegalArgumentException the illegal argument exception
     */
    void createShape(String name, String type, Point point, int r, int g, int b,
                            int horizontal, int vertical) throws IllegalArgumentException;

    /**
     * Add shape.
     *
     * @param name  the name
     * @param shape the shape
     * @throws IllegalArgumentException the illegal argument exception
     */
    void addShape(String name, IShape shape) throws IllegalArgumentException;

    /**
     * Move.
     *
     * @param name  the name
     * @param point the point
     */
    void move(String name, Point point);

    /**
     * Change color.
     *
     * @param name  the name
     * @param color the color
     * @throws IllegalArgumentException the illegal argument exception
     */
    void changeColor(String name, Color color) throws IllegalArgumentException;

    /**
     * Gets shape.
     *
     * @param name the name
     * @return the shape
     * @throws IllegalArgumentException the illegal argument exception
     */
    IShape getShape(String name) throws IllegalArgumentException;

    /**
     * Resize.
     *
     * @param name       the name
     * @param horizontal the horizontal
     * @param vertical   the vertical
     * @throws IllegalArgumentException the illegal argument exception
     */
    void resize(String name, int horizontal, int vertical)
            throws IllegalArgumentException;

    /**
     * Remove.
     *
     * @param name the name
     */
    void remove(String name);

    /**
     * Snap shot snapshot.
     *
     * @param description the description
     * @return the snapshot
     */
    Snapshot snapShot(String description);

    /**
     * Snap shot snapshot.
     *
     * @return the snapshot
     */
    Snapshot snapShot();

    /**
     * Gets snapshots.
     *
     * @return the snapshots
     */
    LinkedHashMap<LocalDateTime, Snapshot> getSnapshots();

    /**
     * Display snapshot string.
     *
     * @return the string
     */
    String displaySnapshot();

    /**
     * Gets description.
     *
     * @return the description
     */
    String getDescription();

    /**
     * Gets log.
     *
     * @return the log
     */
    String getLog();
}
