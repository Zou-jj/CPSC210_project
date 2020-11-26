package persistence;

import model.World;
import org.junit.jupiter.api.Test;
import ui.WarSim;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestJsonWriter extends TestJson {

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
            world.addTroop(WarSim.initElf());
            world.addTroop(WarSim.initUndead());
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorld.json");
            writer.open();
            writer.write(world);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorld.json");
            world = reader.read();
            assertEquals("elf", world.getTroopByIndex(0).getRace());
            assertEquals("undead", world.getTroopByIndex(1).getRace());
            assertEquals(2, world.getTroopByIndex(0).getTroopSize());
            assertEquals(2, world.getTroopByIndex(1).getTroopSize());
            checkWarrior("elf archer", 100, 20,
                    world.getTroopByIndex(0).getWarriorByName("elf archer"));
            checkWarrior("elf ranger", 70, 50,
                    world.getTroopByIndex(0).getWarriorByName("elf ranger"));
            checkWarrior("undead rider", 50, 80,
                    world.getTroopByIndex(1).getWarriorByName("undead rider"));
            checkWarrior("undead bat", 100, 10,
                    world.getTroopByIndex(1).getWarriorByName("undead bat"));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

/*
 *Title:JsonSerializationDemo
 *Author:Paul Carter
 *Date:Oct 17, 2020
 *Availability:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */