package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyOptionListener implements ActionListener {

  public void actionPerformed(ActionEvent e) {
    JComboBox cb = (JComboBox)e.getSource();
    String petName = (String)cb.getSelectedItem();
  }
}
