package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import album.Color;
import album.IShapeModel;
import album.Point;
import album.ShapeModel;
import view.GraphicalView;

/**
 * The type Graphic i controller.
 */
public class GraphiclController implements IController {
  private int maxWidth;
  private int maxHeight;
  private File inputFile;
  /**
   * The Model.
   */
  IShapeModel model = new ShapeModel();

  /**
   * Instantiates a new Graphic i controller.
   *
   * @param inputFile the input file
   * @param maxHeight the max height
   * @param maxWidth  the max width
   */
  public GraphiclController(File inputFile, int maxHeight, int maxWidth) {
    this.inputFile = inputFile;
    this.maxHeight = maxHeight;
    this.maxWidth = maxWidth;
  }

  //File not found exception to cover case where there is no file to place in my scanner
  public void go(IShapeModel model) throws FileNotFoundException {
    this.model = model;
    // pass the path to the file as a parameter
    Scanner sc = new Scanner(this.inputFile);

    //Check to make sure there are lines in the file
    while (sc.hasNextLine()) {
      String lineOfText = sc.nextLine().trim().replaceAll(" +", " ");
      if (lineOfText.startsWith("#")) {
        continue; //Exit this iteration if line starts with #
      } else if (lineOfText.trim().startsWith("shape")) {
        //take line and put in an array without any leading or trailing space
        String[] array = lineOfText.split("\\s+");
        //go through the line and find the attributes we need to create the shape
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
        //take line and put in an array without any leading or trailing space
        String[] array = lineOfText.split("\\s+");
        //Because description is optional just checks to see if there is an expected description
        if (array.length > 1) {
          String[] array2 = Arrays.copyOfRange(array, 1, array.length);
          //join the lines if there are more than one that relates to the description
          String snapDescription = String.join(" ", array2);
          //pass in the optional description
          model.snapShot(snapDescription);
        } else {
          //Case when there is no description
          model.snapShot();
        }

      } else if (lineOfText.trim().startsWith("move")) {
        //take line and put in an array without any leading or trailing space
        String[] array = lineOfText.split("\\s+");
        model.move(array[1], new Point(Integer.parseInt(array[2]), Integer.parseInt(array[3])));

      } else if (lineOfText.trim().startsWith("resize")) {
        //take line and put in an array without any leading or trailing space
        String[] array = lineOfText.split("\\s+");
        model.resize(array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]));

      } else if (lineOfText.trim().startsWith("color")) {
        //take line and put in an array without any leading or trailing space
        String[] array = lineOfText.split("\\s+");
        model.changeColor(array[1], new Color(Integer.parseInt(array[2]),
                Integer.parseInt(array[3]), Integer.parseInt(array[4])));

      } else if (lineOfText.trim().startsWith("remove")) {
        //take line and put in an array without any leading or trailing space
        String[] array = lineOfText.split("\\s+");
        model.remove(array[1]);
      }
    }
    new GraphicalView(model.getSnapshots(), maxWidth, maxHeight);
  }
}
