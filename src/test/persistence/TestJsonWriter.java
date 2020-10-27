package persistence;

import model.InitWorld;
import model.World;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends TestJson {

    @Test
    void testWriterInvalidFile() {
        try {
            World world = new World();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            World world = new World();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorld.json");
            writer.open();
            writer.write(world);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorld.json");
            world = reader.read();
            assertEquals(0, world.getWorldSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            InitWorld world = new InitWorld();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorld.json");
            writer.open();
            writer.write(world);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorld.json");
            world = reader.read();
            assertEquals("My work room", wr.getName());
            List<Thingy> thingies = wr.getThingies();
            assertEquals(2, thingies.size());
            checkThingy("saw", Category.METALWORK, thingies.get(0));
            checkThingy("needle", Category.STITCHING, thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}