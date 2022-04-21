package view;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import album.IShape;
import album.Snapshot;

public class WebView {
  private int maxWidth;
  private int maxHeight;
  private LinkedHashMap<LocalDateTime, Snapshot> snap;
  private String output;

  public WebView(LinkedHashMap<LocalDateTime, Snapshot> snap, String output, int maxWidth, int maxHeight) {
    this.snap = snap;
    this.output = output;
    this.maxWidth = maxWidth;
    this.maxHeight = maxHeight;
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
      String description = snapshot.getValue().getDescription();
      if (description == null) {
        description = " ";
      }
      shapeInfo.append("\n<div>\n" +
              "    <h2>" + snapshot.getValue().getID() + "</h2>\n" + "    <h2>"
              + description + "</h2>\n" + "    <svg width=\"" + maxWidth
              + "\" height=\"" + maxHeight + "\">");
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
    try {
      FileWriter fileWriter = new FileWriter(output);
      WebView view = new WebView(snap,output, maxWidth, maxHeight);
      fileWriter.write(view.buildHeader());
      fileWriter.write(view.getSnapshotInfo());
      fileWriter.write(view.buildFooter());
      fileWriter.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
