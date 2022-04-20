package controller;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

import album.Color;
import album.Point;
import album.ShapeModel;
import album.Snapshot;
import view.WebView;

public class WebController {

  private double maxWidth;
  private double maxHeight;
  private String output;
  private File inputFile;
  private LinkedHashMap<String, Snapshot> snapshot;
  ShapeModel model = new ShapeModel();

  public WebController(File inputFile, String output,
                       int maxHeight, int maxWidth) {
    this.inputFile = inputFile;
    this.output = output;
    this.maxHeight = maxHeight;
    this.maxWidth = maxWidth;
  }

  public void go(ShapeModel model) throws IOException {
    this.model = model;
    // pass the path to the file as a parameter
    Scanner sc = new Scanner(this.inputFile);

    while (sc.hasNextLine()) {
      String lineOfText = sc.nextLine().trim().replaceAll(" +", " ");
      if (lineOfText.startsWith("#")) {
        continue; //Exit this iteration if line starts with space or /
      } else if (lineOfText.trim().startsWith("shape")) {
        String[] array = lineOfText.split("\\s+");
        String name = array[1];
        String type = array[2];
        int pointX = Integer.parseInt(array[3]);
        int pointY= Integer.parseInt(array[4]);
        int horizontal = Integer.parseInt(array[5]);
        int vertical = Integer.parseInt(array[6]);
        int r = Integer.parseInt(array[7]);
        int g = Integer.parseInt(array[8]);
        int b = Integer.parseInt(array[9]);
        model.createShape(name, type, new Point(pointX, pointY), r, g, b, horizontal, vertical);

      } else if (lineOfText.trim().startsWith("snapshot")) {
        String[] array = lineOfText.split("\\s+");
        //combine the second part so description is all in one
        if (array.length > 1) {
          String[] array2 = Arrays.copyOfRange(array, 1, array.length);
          String snapDescription = String.join(" ", array2);
          model.snapShot(snapDescription);
        } else {
          model.snapShot();
        }

      }  else if (lineOfText.trim().startsWith("move")) {
        String[] array = lineOfText.split("\\s+");
        model.move(array[1], new Point(Integer.parseInt(array[2]), Integer.parseInt(array[3])));

      } else if (lineOfText.trim().startsWith("resize")) {
        String[] array = lineOfText.split("\\s+");
        model.resize(array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]));

      } else if (lineOfText.trim().startsWith("color")) {
        String[] array = lineOfText.split("\\s+");
        model.changeColor(array[1], new Color(Integer.parseInt(array[2]),
                Integer.parseInt(array[3]), Integer.parseInt(array[4])));

      } else if (lineOfText.trim().startsWith("remove")) {
        String[] array = lineOfText.split("\\s+");
        model.remove(array[1]);
      }
    }
    WebView webView = new WebView(model.getSnapshots(), output);
    webView.runIt();
  }
}
