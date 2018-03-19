package alberto.masmovilchallenge.data.prefs.wrapper;

public interface SharedPrefs {

    void putString(String key, String value);

    String getString(String key, String defaultValue);

    <T> void putObject(String key, T value);

    <T> T getObject(String key, T defaultValue, Class<T> valueClass);

    void remove(String key);
}
