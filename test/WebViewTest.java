import album.*;
import controller.WebControllerMock;
import org.junit.Before;
import org.junit.Test;
import view.WebView;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * The type Web view test.
 */
public class WebViewTest {
    /**
     * The Input.
     */
    File input;
    /**
     * The Output.
     */
    String output;
    /**
     * The Max width.
     */
    int maxWidth;
    /**
     * The Max height.
     */
    int maxHeight;
    /**
     * The Snapshot.
     */
    LinkedHashMap<String, IShape> snapshot;
    /**
     * The Snap.
     */
    LinkedHashMap<LocalDateTime, Snapshot> snap;
    /**
     * The Oval.
     */
    IShape oval;
    /**
     * The Selfie.
     */
    Snapshot selfie;
    /**
     * The View.
     */
    WebView view;

    /**
     * Test set up.
     */
    @Before
    public void testSetUp() {
        input = new File("./resource/buildings.txt");
        output = "testbuilting.html";
        maxWidth = 800;
        maxHeight = 800;
        snapshot = new LinkedHashMap<>();
        snap = new LinkedHashMap<>();
        oval = new Oval("oval", "oval",
                new Point(4, 5), new Color(4, 5, 6), 10, 2);
        snapshot.put("Oval", oval);
        LocalDateTime now = LocalDateTime.of(2022, 4, 10, 11, 8,
                30);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        selfie = new Snapshot(now, format.format(now), "first selfie", snapshot);
        snap.put(now, selfie);
        view = new WebView(snap,output, maxWidth, maxHeight);
    }

    /**
     * Test build header.
     */
    @Test
    public void testBuildHeader() {
        assertEquals("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "<h1>Your photo album!!</h1>", view.buildHeader());

    }

    /**
     * Test get snapshot info.
     */
    @Test
    public void testGetSnapshotInfo() {
        assertEquals("\n<div>\n" +
                "    <h2>2022-04-10T11:08:30</h2>\n" +
                "    <h2>first selfie</h2>\n" +
                "    <svg width=\"800\" height=\"800\">\n" +
                "        <ellipse id=\"oval\" cx=\"4\" cy=\"5\" rx=\"10\" ry=\"2\" fill=\"rgb(4,5,6)\">\n" +
                "        </ellipse>\n" +
                "    </svg>\n" +
                "</div>\n", view.getSnapshotInfo());
    }

    /**
     * Test build footer.
     */
    @Test
    public void testBuildFooter() {
        assertEquals("</body>\n" +
                "</html>", view.buildFooter());
    }
}