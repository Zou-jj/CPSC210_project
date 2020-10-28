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

public class TestJsonWriter {

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
    void testWriterGeneralWorld() {
        try {
            World world = new World();
            GamePanel.initElf(world);
            GamePanel.initUndead(world);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorld.json");
            writer.open();
            writer.write(world);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorld.json");
            world = reader.read();
            assertEquals("elf", world.getTroopByIndex(0).getRace());
            assertEquals("undead", world.getTroopByIndex(1).getRace());
            List<Troop> troops = new ArrayList<>();
            troops.add(world.getTroopByIndex(0));
            troops.add(world.getTroopByIndex(1));
            assertEquals(2, troops.size());

//            protected void checkTroop(String race,int size, Troop troop, List< Warrior > warriors) {
//                assertEquals(race, troop.getRace());
//                assertEquals(size, troop.getTroopSize());
//                for (Warrior warrior : warriors) {
//
//                }
//                assertEquals(attack, warrior.getAttack());
//                assertEquals(defense, warrior.getDefense());
//            }
//
//            checkThingy("saw", Category.METALWORK, thingies.get(0));
//            checkThingy("needle", Category.STITCHING, thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}