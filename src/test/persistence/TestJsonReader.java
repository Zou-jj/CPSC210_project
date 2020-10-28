package persistence;

import model.Troop;
import model.World;
import org.junit.jupiter.api.Test;
import ui.GamePanel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonReader {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            World world = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorld() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorld.json");
        try {
            World world = reader.read();
            assertEquals(0, world.getWorldSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorld.json");
        try {
            World world = reader.read();
            assertEquals("elf", world.getTroopByIndex(0).getRace());
            assertEquals("undead", world.getTroopByIndex(1).getRace());
            List<Troop> troops = new ArrayList<>();
            troops.add(world.getTroopByIndex(0));
            troops.add(world.getTroopByIndex(1));
            assertEquals(2, troops.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
