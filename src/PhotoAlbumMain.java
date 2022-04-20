import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import album.ShapeModel;
import controller.WebController;

public class PhotoAlbumMain {
  public static void main(String [] args) throws IOException {
    File inFile = null;
    String output = null;
    String type = null;
    int maxWidth = 1000;
    int maxHeight = 1000;
    //take in command line input

    for (int i = 0; i<args.length; i++) {
      if (args[i].equalsIgnoreCase("-in")) {
        inFile = new File(args[i + 1]);
      } else if (args[i].equalsIgnoreCase("-v")
              || args[i].equalsIgnoreCase("-view")) {
        type = args[i + 1];
        Scanner scanner = new Scanner(type);
        if (scanner.hasNextInt()) {
          maxWidth = scanner.nextInt();
          Scanner scan2 = new Scanner(String.valueOf(maxWidth));
          if (scan2.hasNextInt()) {
            maxHeight = scan2.nextInt();
          }
        }
      }
    }

    if (type.equalsIgnoreCase("web")) {
      for (int i = 0; i<args.length; i++) {
        if (args[i].equalsIgnoreCase("-out")) {
          output = args[i+1];
        }
      }
    }
    System.out.println(inFile + output + maxWidth + maxHeight + type);
    WebController web = new WebController(inFile, output, maxWidth, maxHeight);
    ShapeModel model = new ShapeModel();
    web.go(model);
  }
}


