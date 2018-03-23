package alberto.masmovilchallenge.injection.module;

import alberto.masmovilchallenge.MMApplication;
import alberto.masmovilchallenge.data.service.ApiClient;
import alberto.masmovilchallenge.data.service.AppService;

import static org.mockito.Mockito.mock;


public class ApplicationTestModule extends ApplicationModule {

    public ApplicationTestModule(MMApplication application) {
        super(application);
    }

    @Override
    public AppService provideAppService(ApiClient apiClient) {
        return mock(AppService.class);
    }
}