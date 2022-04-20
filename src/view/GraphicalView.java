package view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import java.awt.*;
import java.util.List;
import java.util.Map;


import javax.swing.*;

import album.IShape;
import album.Point;
import album.ShapeModel;
import album.Snapshot;

public class GraphicalView extends JFrame {
  public static final int WIDTH = 1000;
  public static final int HEIGHT = 1000;
  HashMap<LocalDateTime, Snapshot> snapshots = new HashMap<>();
  List<String> IdList = new ArrayList<>();

  //iterate through list based on IDs and find the snapshot that matches the ID
  //Use 2 hashmaps, 1 matches ID to number and 1 matches ID to snapshot

  public GraphicalView(HashMap<LocalDateTime, Snapshot> snapshots) {
    this.snapshots = snapshots;
    this.setSize(WIDTH, HEIGHT);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("CS5004 Shapes Photo Album View");
    DrawPanel drawPanel = new DrawPanel();
    this.add(drawPanel);
    JPanel btnPanel = new JPanel();
    JButton next = new JButton("Next");
    JButton select = new JButton("Select");
    //make a list of the ID keys for the list of screenshots?
/*    for (Map.Entry<LocalDateTime, Snapshot> snapshot : snapshots.entrySet()) {
      StringBuilder IdString = new StringBuilder();
      IdString.append(snapshot.getKey().toString());
      IdList.add(IdString.toString());
    }
    JComboBox Ids = new JComboBox((ComboBoxModel) IdList);
    Ids.setSelectedItem(IdList.size());
    Ids.addActionListener((ActionListener) this);
    btnPanel.add(Ids);*/

    JButton previous = new JButton("Previous");
    JButton quit = new JButton("Quit");
    MyCloseListener listener = new MyCloseListener(); // listen for events
    quit.addActionListener(listener);
    btnPanel.add(next);
    btnPanel.add(select);
    btnPanel.add(previous);
    btnPanel.add(quit);
    add(btnPanel, BorderLayout.PAGE_END);
  }

  public static void main(String[] args) {
    //cant get anything with a name to work
    ShapeModel model = new ShapeModel();
    model.createShape("myrect", "rectangle", new Point(200, 200),
            255, 0, 0, 50, 100);
    model.createShape("myoval", "oval", new Point(500, 100),
            0, 255, 1, 60, 30);
    GraphicalView window = new GraphicalView(model.getSnapshots());
    model.snapShot("first");
    //model.move("myrect", new Point(50, 50));
    window.setVisible(true);
  }

  class DrawPanel extends JPanel {
    //right now no way to just do 1 screen shot at a time, not sure how to do this with hashmaps
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      for (Map.Entry<LocalDateTime, Snapshot> snapshot : snapshots.entrySet()) {
        JLabel label1 = new JLabel();
        label1.setText(snapshot.getValue().getID().toString());
        label1.setBounds(0, 0, 200, 20);
        add(label1);
        JLabel label2 = new JLabel();
        label2.setText(snapshot.getValue().getDescription());
        label2.setBounds(0, 20, 200, 20);
        add(label2);
        for (IShape shape : snapshot.getValue().getIShape()) {
          if (shape.getType().equalsIgnoreCase("rectangle")) {
            g.drawRect(shape.getPoint().getX(), shape.getPoint().getY(),
                    shape.getHorizontal(), shape.getVertical());
            g.setColor(Color.getColor(shape.getColor().toString()));
            g.fillRect(shape.getPoint().getX(), shape.getPoint().getY(),
                    shape.getHorizontal(), shape.getVertical());

          } else {
            g.drawOval(shape.getPoint().getX(), shape.getPoint().getY(),
                    shape.getHorizontal(), shape.getVertical());
            g.setColor(Color.getColor(shape.getColor().toString()));
            g.fillOval(shape.getPoint().getX(), shape.getPoint().getY(),
                    shape.getHorizontal(), shape.getVertical());
          }
        }
      }
    }
  }
}
