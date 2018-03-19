package alberto.masmovilchallenge.injection.module;

import android.content.Context;

import javax.inject.Singleton;

import alberto.masmovilchallenge.MMApplication;
import alberto.masmovilchallenge.data.prefs.DataStore;
import alberto.masmovilchallenge.data.prefs.DataStoreManager;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final MMApplication mApplication;

    public ApplicationModule(MMApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mApplication;
    }


    @Provides
    @Singleton
    DataStore provideDataStore(Context context) {
        return DataStoreManager.getAppDataStore(context);
    }
}
