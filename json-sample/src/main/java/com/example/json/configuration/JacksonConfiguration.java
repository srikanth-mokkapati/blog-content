package com.example.json.configuration;

import com.example.json.parsing.CustomJsonArrayDeserializer;
import com.example.json.parsing.CustomJsonObjectDeserializer;
import com.example.json.parsing.CustomGsonObjectSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {


    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(JsonElement.class, new CustomGsonObjectSerializer());
        simpleModule.addDeserializer(JsonObject.class, new CustomJsonObjectDeserializer());
        simpleModule.addDeserializer(JsonArray.class, new CustomJsonArrayDeserializer());
        mapper.registerModule(simpleModule);
        return mapper;
    }

}
