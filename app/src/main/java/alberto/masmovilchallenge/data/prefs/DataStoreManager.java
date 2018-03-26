package alberto.masmovilchallenge.data.prefs;


import android.content.Context;

import alberto.masmovilchallenge.data.prefs.wrapper.SharedPrefsImpl;

public class DataStoreManager {
    private static DataStore dataStore;

    public static synchronized DataStore getAppDataStore(Context context) {
        if (dataStore == null) {
            dataStore = new DataStoreImpl(new SharedPrefsImpl(context));
        }

        return dataStore;
    }
}
