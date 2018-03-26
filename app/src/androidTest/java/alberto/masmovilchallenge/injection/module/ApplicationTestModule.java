package alberto.masmovilchallenge.injection.module;

import android.content.Context;

import javax.inject.Singleton;

import alberto.masmovilchallenge.MMApplication;
import alberto.masmovilchallenge.data.prefs.DataStore;
import alberto.masmovilchallenge.data.service.ApiClient;
import alberto.masmovilchallenge.data.service.AppService;
import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class ApplicationTestModule {

    protected final MMApplication app;

    public ApplicationTestModule(MMApplication app) {
        this.app = app;
    }

    @Provides
    MMApplication provideApplication() {
        return app;
    }

    @Provides
    Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    DataStore provideDataStore(Context context) {
        return mock(DataStore.class);
    }

    @Provides
    @Singleton
    ApiClient provideApiClient(DataStore dataStore) {
        return new ApiClient(dataStore);
    }

    @Provides
    @Singleton
    AppService provideAppService(ApiClient apiClient) {
        return mock(AppService.class);
    }
}