package alberto.masmovilchallenge.di.module;

import android.content.Context;

import javax.inject.Singleton;

import alberto.masmovilchallenge.MMApplication;
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

}
