package com.example.json.parsing;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.util.Map;

public class CustomGsonObjectSerializer extends JsonSerializer<JsonElement> {
    @Override
    public void serialize(JsonElement value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(value.isJsonObject()) {
            writeJsonObject(value.getAsJsonObject(), gen, serializers);
        } else if (value.isJsonArray()) {
            writeJsonArray(value.getAsJsonArray(), gen, serializers);
        } else if (value.isJsonPrimitive()) {
            writeJsonPrimitive(value.getAsJsonPrimitive(), gen, serializers);
        } else if (value.isJsonNull()) {
            gen.writeNull();
        } else {
            throw new UnsupportedOperationException("Unsupported Gson - JsonElement Type.");
        }
    }

    private void writeJsonPrimitive(JsonPrimitive value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(processJsonPrimitive(value));
    }

    private void writeJsonArray(JsonArray value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (JsonElement element : value) {
            gen.writeObject(element);
        }
        gen.writeEndArray();
    }

    private void writeJsonObject(JsonObject value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        for(Map.Entry<String,JsonElement> entry : value.entrySet()) {
            gen.writeFieldName(entry.getKey());
            gen.writeObject(entry.getValue());
        }
        gen.writeEndObject();
    }

    private Object processJsonPrimitive(JsonPrimitive value) {
        if(value.isBoolean()) {
            return value.getAsBoolean();
        } else if (value.isString()) {
            return value.getAsString(); //toString method will add extra quotes
        } else if (value.isNumber()) {
            return NumberUtils.createNumber(value.getAsString()); //apache lang3 library
        } else {
            throw new UnsupportedOperationException("UnSupported Gson - JsonPrimitive Type.");
        }
    }
}
