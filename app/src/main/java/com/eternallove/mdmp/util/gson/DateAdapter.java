package com.eternallove.mdmp.util.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * @description:
 * @author: eternallove
 * @date: 2018/4/30 22:38
 */
public class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement json, Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException {
        if (json == null) {
            return null;
        } else {
            try {
                if(json instanceof JsonObject){
                    JsonObject mJson = (JsonObject) json;
                    if(mJson.has("time")){
                        return new Date(mJson.get("time").getAsLong());
                    }
                }
                return new Date(json.getAsJsonPrimitive().getAsLong());
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        String value = "";
        if (src != null) {
            value = String.valueOf(src.getTime());
        }
        return new JsonPrimitive(value);
    }
}
