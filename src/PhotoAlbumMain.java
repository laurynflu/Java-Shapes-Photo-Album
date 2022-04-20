import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import album.Color;
import album.Point;
import album.ShapeModel;
import view.WebView;

public class PhotoAlbumMain {

  public static void main(String[] args) throws Exception {
    ShapeModel model = new ShapeModel();
    // pass the path to the file as a parameter
    String files = "./buildings.txt";
    File file = new File(files);
    Scanner sc = new Scanner(file);

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
    System.out.println(model.getLog());
    WebView webView = new WebView(model.getSnapshots());
    webView.runIt();
  }
}
