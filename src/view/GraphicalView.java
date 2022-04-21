package view;

import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import album.IShape;
import album.Point;
import album.ShapeModel;
import album.Snapshot;

public class GraphicalView extends JFrame {
  private int maxWidth;
  private int maxHeight;
  private DrawPanel drawPanel;
  private LinkedHashMap<LocalDateTime, Snapshot> snapshots;

  //iterate through list based on IDs and find the snapshot that matches the ID
  //Use 2 hashmaps, 1 matches ID to number and 1 matches ID to snapshot

  public GraphicalView(LinkedHashMap<LocalDateTime, Snapshot> snapshots, int maxWidth, int maxHeight) {
    this.snapshots = snapshots;
    this.maxWidth = maxWidth;
    this.maxHeight = maxHeight;
    this.setSize(maxWidth, maxHeight);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("CS5004 Shapes Photo Album View");
    //cycle through values and place in drawpanel
    drawPanel = new DrawPanel(snapshots.entrySet().stream().findFirst().get().getValue());
    drawPanel.setPreferredSize(new Dimension(maxWidth, maxHeight));
    JPanel btnPanel = new JPanel();
    JPanel introPanel = new JPanel();
    JLabel id = new JLabel(snapshots.entrySet().stream().findFirst().get().getKey().toString());
    JLabel description = new JLabel(snapshots.entrySet().stream().findFirst().get().getValue().getDescription());
    JButton next = new JButton("Next");
    JButton select = new JButton("Select");
    JButton previous = new JButton("Previous");
    JButton quit = new JButton("Quit");
    MyCloseListener listener = new MyCloseListener(); // listen for events
    quit.addActionListener(listener);
    btnPanel.add(next);
    btnPanel.add(select);
    btnPanel.add(previous);
    btnPanel.add(quit);
    introPanel.add(id, BorderLayout.NORTH);
    introPanel.add(description, BorderLayout.SOUTH);
    add(introPanel, BorderLayout.PAGE_START);
    add(btnPanel, BorderLayout.PAGE_END);
    add(drawPanel, BorderLayout.CENTER);
    drawPanel.setVisible(true);
    setVisible(true);
  }
}

