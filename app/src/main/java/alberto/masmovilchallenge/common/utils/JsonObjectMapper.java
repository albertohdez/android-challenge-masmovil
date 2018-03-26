package alberto.masmovilchallenge.common.utils;


import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonObjectMapper {
    private static final String TAG = JsonObjectMapper.class.getCanonicalName();

    private static ObjectMapper mapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json != null) {
            try {
                return mapper.readValue(json, clazz);
            } catch (IOException e) {
                Log.e(TAG, "Cannot parse '" + json
                        + "' to class '" + clazz.getSimpleName() + "'", e);
            }
        }
        return null;
    }

    public static String toJson(Object val) {
        try {
            return mapper.writeValueAsString(val);
        } catch (JsonProcessingException e) {
            Log.e(TAG, "Cannot convert '" + val + "' into json", e);
        }
        return null;
    }
}