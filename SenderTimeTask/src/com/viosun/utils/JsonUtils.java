package com.viosun.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {


    private static ObjectMapper mapper = new ObjectMapper();


    /**
     * Convert object to json
     *
     * @return json string
     */
    public static final String toJson(final Object object){

        try {
            return mapper.writeValueAsString(object);
        }catch (Exception err){
            return "";
        }
    }


    /**
     * Convert string to given type
     *
     * @return instance of type
     */
    public static final <V> V fromJson(String json, Class<V> type) {

        try {
            return mapper.readValue(json, type);
        } catch (Exception err) {
//            Log.i("mapper.readValue", err.getMessage());
            return null;
        }
    }

}

