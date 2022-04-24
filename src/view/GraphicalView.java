package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Objects;

import javax.swing.*;

import album.ISnapShot;
import album.Snapshot;

/**
 * The type Graphical view.
 */
public class GraphicalView extends JFrame {
  private final int maxWidth;
  private final int maxHeight;
  private final LinkedHashMap<LocalDateTime, Snapshot> snapshots;
  private final ArrayList<String> snapID = new ArrayList<>();
  private final List<ISnapShot> snaps = new ArrayList<>();
  private DrawPanel drawPanel;
  private final JFrame frame;
  private int counter = 0;
  private final Object[] options;
  private JLabel id;
  private JLabel description;
  private JPanel introPanel;

  //iterate through list based on IDs and find the snapshot that matches the ID
  //Use 2 hashmaps, 1 matches ID to number and 1 matches ID to snapshot

  /**
   * Instantiates a new Graphical view.
   *
   * @param snapshots the snapshots
   * @param maxWidth  the max width
   * @param maxHeight the max height
   */
  public GraphicalView(LinkedHashMap<LocalDateTime, Snapshot> snapshots, int maxWidth, int maxHeight) {
    super();
    frame = this;
    this.snapshots = snapshots;
    this.maxWidth = maxWidth;
    this.maxHeight = maxHeight;
    this.setSize(maxWidth, maxHeight);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("CS5004 Shapes Photo Album");
    //snapshots.values().toArray();

    for (ISnapShot id : this.snapshots.values()) {
      snapID.add(id.getID().toString());
      snaps.add(id);
    }
    options = snapID.toArray(new String[0]);
    JPanel btnPanel = new JPanel();
    introPanel = new JPanel();
    drawPanel = new DrawPanel(snaps.get(0));
    drawPanel.setPreferredSize(new Dimension(maxWidth, maxHeight));
    id = new JLabel(snaps.get(0).getID().toString());
    description = new JLabel(snaps.get(0).getDescription());

    JButton next = new JButton("Next");
    next.addActionListener(new NextListener());
    JButton select = new JButton("Select");
    select.addActionListener(new SelectionListener());
    JButton previous = new JButton("Previous");
    previous.addActionListener(new PreviousListener());
    JButton quit = new JButton("Quit");
    MyCloseListener listener = new MyCloseListener(); // listen for events
    quit.addActionListener(listener);

    btnPanel.add(previous);
    btnPanel.add(select);
    btnPanel.add(next);
    btnPanel.add(quit);
    btnPanel.setBackground(Color.ORANGE);
    introPanel.setBackground(Color.PINK);
    introPanel.add(id, BorderLayout.NORTH);
    introPanel.add(description, BorderLayout.SOUTH);
    add(introPanel, BorderLayout.PAGE_START);
    add(btnPanel, BorderLayout.PAGE_END);
    add(drawPanel, BorderLayout.CENTER);
    drawPanel.setVisible(true);
    setVisible(true);
  }

  private void updateLabel() {
    id.setText(snaps.get(counter).getID().toString());
    if (snaps.get(counter).getDescription() != null) {
      description.setText(snaps.get(counter).getDescription());
    }
    introPanel.add(id, BorderLayout.NORTH);
    introPanel.add(description, BorderLayout.SOUTH);
    setVisible(true);
  }

  private void displaySnapshots() {
    drawPanel = new DrawPanel(snaps.get(counter));
    add(drawPanel, BorderLayout.CENTER);
    drawPanel.setVisible(true);
    setVisible(true);
  }

  private class SelectionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String s = (String) JOptionPane.showInputDialog(frame,
              "Please selection a snapshot from the following snapshots",
              "Snapshots", JOptionPane.INFORMATION_MESSAGE,
              null, options, snapID.get(0));
      for (int i = 0; i < snaps.size(); i++) {
        if (Objects.equals(s, snapID.get(i))) {
          counter = i;
          updateLabel();
          displaySnapshots();
        }
      }
    }
  }
  private class NextListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      counter++;
      if (counter == snaps.size()) {
        JOptionPane.showMessageDialog(frame, "You have reached the end of the album",
                "End of the Album", JOptionPane.WARNING_MESSAGE);
        counter = snaps.size()-1;
        updateLabel();
        displaySnapshots();
      }
      updateLabel();
      displaySnapshots();
    }
  }
  private class PreviousListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      counter--;
      if (counter < 0) {
        JOptionPane.showMessageDialog(frame, "You have reached the first snapshot",
                "Beginning of the Album", JOptionPane.WARNING_MESSAGE);
        counter = 0;
        updateLabel();
        displaySnapshots();
      }
      updateLabel();
      displaySnapshots();
    }
  }
}


