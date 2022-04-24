import java.io.File;

import album.IShapeModel;
import album.ShapeModel;
import controller.GraphiclController;
import controller.WebController;

/**
 * The type Photo album main.
 */
public class PhotoAlbumMain {
  /**
   * The entry point of application.
   *
   * @param args the input arguments
   * @throws Exception the exception
   */
  public static void main(String [] args) throws Exception {
    File inFile = null;
    String output = null;
    String type = null;
    int maxWidth = 1000;
    int maxHeight = 1000;
    IShapeModel model = new ShapeModel();

    for (int i = 0; i<args.length; i++) {
      if (args[i].equalsIgnoreCase("-in")) {
        inFile = new File(args[i + 1]);
      } else if (args[i].equalsIgnoreCase("-v")
              || args[i].equalsIgnoreCase("-view")) {
        type = args[i + 1];
        if (args.length>4 && type.equalsIgnoreCase("graphical")){
          maxWidth = Integer.parseInt(args[i + 2]);
          maxHeight = Integer.parseInt(args[i + 3]);
        } else if (args.length>6 && type.equalsIgnoreCase("web")) {
          maxWidth = Integer.parseInt(args[i + 2]);
          maxHeight = Integer.parseInt(args[i + 3]);
        }
      }
    }
    if (type.equalsIgnoreCase("web")) {
      for (int i = 0; i<args.length; i++) {
        if (args[i].equalsIgnoreCase("-out")) {
          output = args[i + 1];
        }
      }
      WebController web = new WebController(inFile, output, maxWidth, maxHeight);
      web.go(model);
    } else if (type.equalsIgnoreCase("graphical")) {
      GraphiclController graph = new GraphiclController(inFile, maxWidth, maxHeight);
      graph.go(model);
    }
  }
}


