package persistence;

import org.json.JSONObject;

// Represent a json writable object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

/*
 *Title:JsonSerializationDemo
 *Author:Paul Carter
 *Date:Oct 17, 2020
 *Availability:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */