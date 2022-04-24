import album.*;
import controller.WebControllerMock;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

/**
 * The type Mock model web test.
 */
public class MockModelWebTest {
    /**
     * Go.
     *
     * @throws IOException the io exception
     */
    @Test
    public void go() throws IOException {
        File input = new File("./resource/buildings.txt");
        String output = "testbuilting.html";
        int maxWidth = 800;
        int maxHeight = 800;
        StringBuilder log = new StringBuilder();
        LinkedHashMap<String, IShape> snapshot = new LinkedHashMap<>();
        LinkedHashMap<LocalDateTime, Snapshot> snap = new LinkedHashMap<>();
        WebControllerMock controller = new WebControllerMock(input, output, maxWidth, maxHeight);
        IShape oval = new Oval("oval", "oval",
                new Point(4, 5), new Color(4, 5, 6), 10, 2);
        snapshot.put("Oval", oval);
        LocalDateTime now = LocalDateTime.of(2022, 4, 10, 11, 8,
                30);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        Snapshot selfie = new Snapshot(now, format.format(now), "first selfie", snapshot);
        snap.put(now, selfie);
        controller.go(new MockModel(log, "unique", oval, snap, selfie));
        assertEquals("Input: background rectangle (0,0) 33 94 248 800 800\n" +
                "Input: B0 rectangle (80,424) 0 0 0 100 326\n" +
                "Input: B1 rectangle (260,365) 0 0 0 100 385\n" +
                "Input: B2 rectangle (440,375) 0 0 0 100 375\n" +
                "Input: B3 rectangle (620,445) 0 0 0 100 305\n" +
                "Input: window000 rectangle (100,500) 255 255 255 20 20\n" +
                "Input: window001 rectangle (140,500) 255 255 255 20 20\n" +
                "Input: window010 rectangle (100,600) 255 255 255 20 20\n" +
                "Input: window011 rectangle (140,600) 255 255 255 20 20\n" +
                "Input: window002 rectangle (280,500) 255 255 255 20 20\n" +
                "Input: window021 rectangle (320,500) 255 255 255 20 20\n" +
                "Input: window022 rectangle (280,600) 255 255 255 20 20\n" +
                "Input: window200 rectangle (320,600) 255 255 255 20 20\n" +
                "Input: window003 rectangle (460,500) 255 255 255 20 20\n" +
                "Input: window033 rectangle (500,500) 255 255 255 20 20\n" +
                "Input: window333 rectangle (460,600) 255 255 255 20 20\n" +
                "Input: window313 rectangle (500,600) 255 255 255 20 20\n" +
                "Input: window004 rectangle (640,500) 255 255 255 20 20\n" +
                "Input: window044 rectangle (680,500) 255 255 255 20 20\n" +
                "Input: window444 rectangle (640,600) 255 255 255 20 20\n" +
                "Input: window414 rectangle (680,600) 255 255 255 20 20\n" +
                "Input: moon oval (200,200) 229 229 255 100 100\n" +
                "Input: Turn on the Lights!\n", log.toString());
    }
}