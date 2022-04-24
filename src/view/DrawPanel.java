package view;

import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.time.chrono.IsoChronology;

import album.IShape;
import album.ISnapShot;

/**
 * The type Draw panel.
 */
public class DrawPanel extends JPanel {
  private ISnapShot snapshot;

  /**
   * Instantiates a new Draw panel.
   *
   * @param snapshot the snapshot
   */
  public DrawPanel(ISnapShot snapshot) {
    super(true);
    this.snapshot = snapshot;

  }

  //right now no way to just do 1 screen shot at a time, not sure how to do this with hashmaps
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g = (Graphics2D) graphics;
    for (IShape shape : snapshot.getIShape()) {
      if (shape.getType().equalsIgnoreCase("rectangle")) {
        g.drawRect(shape.getPoint().getX(), shape.getPoint().getY(),
                shape.getHorizontal(), shape.getVertical());
        g.setColor(new Color(shape.getColor().getR(), shape.getColor().getG(), shape.getColor().getB()));
        g.fillRect(shape.getPoint().getX(), shape.getPoint().getY(),
                shape.getHorizontal(), shape.getVertical());

      } else {
        g.drawOval(shape.getPoint().getX(), shape.getPoint().getY(),
                shape.getHorizontal(), shape.getVertical());
        g.setColor(new Color(shape.getColor().getR(), shape.getColor().getG(), shape.getColor().getB()));
        g.fillOval(shape.getPoint().getX(), shape.getPoint().getY(),
                shape.getHorizontal(), shape.getVertical());
      }
    }
  }
}
