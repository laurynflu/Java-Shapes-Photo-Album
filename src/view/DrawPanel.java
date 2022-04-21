package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import java.awt.Color;

import album.IShape;
import album.Snapshot;

public class DrawPanel extends JPanel {
  private Snapshot snapshot;
  private List<IShape> shapes;

  public DrawPanel(Snapshot snapshot) {
    super(true);
    System.out.println("hello");
    this.snapshot = snapshot;
    this.setBackground(Color.CYAN);

  }

  //right now no way to just do 1 screen shot at a time, not sure how to do this with hashmaps
  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g = (Graphics2D) graphics;
    for (IShape shape : snapshot.getIShape()) {
      System.out.println(shape.getName());
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
