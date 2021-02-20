package com.example.json.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {

    @GetMapping("/get")
    public JsonObject status() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("String", "string");
        jsonObject.addProperty("number", 1);
        jsonObject.addProperty("boolean", "true");
        //any null property added will automatically becomes JsonNull
        jsonObject.add("null", JsonNull.INSTANCE);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add("string");
        jsonArray.add(1.1);
        jsonArray.add(false);
        jsonArray.add(JsonNull.INSTANCE);
        jsonObject.add("jsonArray", jsonArray);
        return jsonObject;
    }

    @PostMapping("/post")
    public JsonObject post(@RequestBody JsonObject jsonObject) {
        return jsonObject;
    }
}
