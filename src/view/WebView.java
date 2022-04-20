package view;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import album.Color;
import album.IShape;
import album.Point;
import album.ShapeModel;
import album.Snapshot;

public class WebView {
  private final HashMap<LocalDateTime, Snapshot> snap;

  public WebView(HashMap<LocalDateTime, Snapshot> snap) {
    this.snap = snap;
  }

  //need to find a way to actually get the Ishape from the snapshots, right now its just the 2
  // string of those shapes

  public String buildHeader() {
    StringBuilder header = new StringBuilder();
    header.append("<!DOCTYPE html>\n" +
            "<html>\n" +
            "<body>\n" +
            "<h1>Your photo album!!</h1>");
    return header.toString();
  }

  //method that takes in the snapshot information
  //can i take in this information and append to the string builder all at once
  public String getSnapshotInfo() {
    StringBuilder shapeInfo = new StringBuilder();
    for (Map.Entry<LocalDateTime, Snapshot> snapshot : snap.entrySet()) {
      shapeInfo.append("\n<div>\n" +
              "    <h2>" + snapshot.getValue().getID() + "</h2>\n" + "    <h2>"
              + snapshot.getValue().getDescription() + "</h2>\n" + "    <svg width=\"" + 800
              + "\" height=\"" + 800 + "\">");
      for (IShape shape : snapshot.getValue().getIShape()) {
        if (shape.getType().equalsIgnoreCase("rectangle")) {
          shapeInfo.append("\n        <rect id=\""
                  + shape.getName() + "\" x=\"" + shape.getPoint().getX()
                  + "\" y=\"" + shape.getPoint().getY() + "\" width=\""
                  + shape.getHorizontal() + "\" height=\""
                  + shape.getVertical() + "\" fill=\"rgb" + shape.getColor()
                  + "\">\n        </rect>\n");
        } else {
          shapeInfo.append("\n        <ellipse id=\""
                  + shape.getName() + "\" cx=\"" + shape.getPoint().getX()
                  + "\" cy=\"" + shape.getPoint().getY() + "\" rx=\""
                  + shape.getHorizontal() + "\" ry=\"" + shape.getVertical()
                  + "\" fill=\"rgb" + shape.getColor() + "\">\n        </ellipse>\n");
        }
      }
      shapeInfo.append("    </svg>\n" + "</div>\n");
    }
    return shapeInfo.toString();
  }

  public String buildFooter() {
    StringBuilder footer = new StringBuilder();
    footer.append("</body>\n" + "</html>");
    return footer.toString();
  }

  public void runIt() {
    String filename = "./out.html";
    try {
      FileWriter fileWriter = new FileWriter(filename);
      WebView view = new WebView(snap);
      fileWriter.write(view.buildHeader());
      fileWriter.write(view.getSnapshotInfo());
      fileWriter.write(view.buildFooter());
      fileWriter.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
