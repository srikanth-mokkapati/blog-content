package com.example.json.parsing;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

public class CustomJsonObjectDeserializer extends JsonDeserializer<JsonObject> {

    @Override
    public JsonObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonElement jsonElement =  com.google.gson.JsonParser.parseString(p.readValueAsTree().toString());
        if (jsonElement.isJsonNull()) {
            return null;
        } else if (jsonElement.isJsonObject()) {
            return jsonElement.getAsJsonObject();
        } else {
            throw new JsonParseException(p, "Input not a JsonObject");
        }
    }

}
