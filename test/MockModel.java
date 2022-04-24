import album.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

/**
 * The type Mock model.
 */
public class MockModel implements IShapeModel {
    private StringBuilder log;
    private final String uniqueCode;
    private final IShape uniqueIshape;
    private final Snapshot uniqueSnap;

    private final LinkedHashMap<LocalDateTime, Snapshot> uniqueHash;


    /**
     * Instantiates a new Mock model.
     *
     * @param log          the log
     * @param uniqueCode   the unique code
     * @param uniqueIshape the unique ishape
     * @param uniqueHash   the unique hash
     * @param uniqueSnap   the unique snap
     */
    public MockModel(StringBuilder log, String uniqueCode, IShape uniqueIshape, LinkedHashMap<LocalDateTime,
            Snapshot> uniqueHash, Snapshot uniqueSnap) {
        this.log = log;
        this.uniqueCode = uniqueCode;
        this.uniqueIshape = uniqueIshape;
        this.uniqueHash = uniqueHash;
        this.uniqueSnap = uniqueSnap;

    }

    @Override
    public void createShape(String name, String type, Point point, int r, int g, int b, int horizontal, int vertical) throws IllegalArgumentException {
        log.append("Input: " + name + " " + type + " " + point + " " + r + " " + g + " " + b + " " + horizontal
                + " " + vertical + "\n");
    }

    @Override
    public void addShape(String name, IShape shape) throws IllegalArgumentException {
        log.append("Input: " + name + " " + shape + "\n");
    }

    @Override
    public void move(String name, Point point) {
        log.append("Input: " + name + " " + point + "\n");
    }

    @Override
    public void changeColor(String name, Color color) throws IllegalArgumentException {
        log.append("Input: " + name + " " + color + "\n");
    }

    @Override
    public IShape getShape(String name) throws IllegalArgumentException {
        log.append("Input: " + name + "\n");
        return uniqueIshape;
    }

    @Override
    public void resize(String name, int horizontal, int vertical) throws IllegalArgumentException {
        log.append("Input: " + name + " " + horizontal + " " + vertical + "\n");
    }

    @Override
    public void remove(String name) {
        log.append("Input: " + name + "\n");
    }

    @Override
    public Snapshot snapShot(String description) {
        log.append("Input: " + description + "\n");
        return null;
    }

    @Override
    public Snapshot snapShot() {
        return uniqueSnap;
    }

    @Override
    public LinkedHashMap<LocalDateTime, Snapshot> getSnapshots() {
        return uniqueHash;
    }

    @Override
    public String displaySnapshot() {
        return uniqueCode;
    }

    @Override
    public String getDescription() {
        return uniqueCode;
    }

    @Override
    public String getLog() {
        return uniqueCode;
    }
}
