package alberto.masmovilchallenge.data.prefs.wrapper;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import alberto.masmovilchallenge.common.utils.JsonObjectMapper;

public class SharedPrefsImpl implements SharedPrefs {
    private static final String TAG = SharedPrefsImpl.class.getCanonicalName();
    private static final String FILE_NAME = "com.alberto.prefs";

    private final SharedPreferences prefs;

    public SharedPrefsImpl(Context context) {
        this.prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Saves a string to the preferences.
     * <p>
     * Supplying {@code null} as the value is equivalent to calling {@link #remove(String)}
     * with this key.
     *
     * @param key
     * @param value
     */
    @Override
    public void putString(String key, String value) {
        prefs.edit().putString(key, value).apply();
    }

    /**
     * Retrieves a String from the preferences.
     *
     * @param key
     * @param defaultValue Default value to return if this preference does not exist
     * @return Returns the preference value if it exists, or defaultValue.
     */
    @Override
    public String getString(String key, String defaultValue) {
        return prefs.getString(key, defaultValue);
    }

    /**
     * Saves an object to the preferences.
     * <p>
     * Value will be converted into a json string.
     * <p>
     * Supplying {@code null} as the value is equivalent to calling {@link #remove(String)}
     * with this key.
     *
     * @param key   Plaintext key
     * @param value Value object to be parsed into a json string
     */
    @Override
    public <T> void putObject(String key, T value) {
        if (value != null) {
            String json = JsonObjectMapper.toJson(value);
            if (json != null) {
                putString(key, json);
            } else {
                Log.e(TAG, "Cannot put (" + key + ", " + value + ") pair, " +
                        "since json is null");
            }
        } else {
            remove(key);
        }
    }

    /**
     * Retrieves an object that is serialized in json from the preferences.
     *
     * @param key
     * @param defaultValue Default value to return if this preference does not exist
     * @param valueClass   The class name for the json string to be de-serialized into.
     * @return Returns the preference value if it exists, or defaultValue.
     */
    @Override
    public <T> T getObject(String key, T defaultValue, Class<T> valueClass) {
        String json = prefs.getString(key, null);
        if (json == null) {
            return defaultValue;
        } else {
            T obj = JsonObjectMapper.fromJson(json, valueClass);
            if (obj == null) {
                Log.e(TAG, "Cannot deserialize " + json + " for key ");
                return defaultValue;
            } else {
                return obj;
            }
        }
    }

    /**
     * Removes a preference value.
     *
     * @param key
     */
    @Override
    public void remove(String key) {
        prefs.edit().remove(key).apply();
    }
}
