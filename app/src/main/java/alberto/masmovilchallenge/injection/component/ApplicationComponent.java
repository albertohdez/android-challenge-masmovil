package alberto.masmovilchallenge.injection.component;

import javax.inject.Singleton;

import alberto.masmovilchallenge.MMApplication;
import alberto.masmovilchallenge.common.view.activity.BaseActivity;
import alberto.masmovilchallenge.data.prefs.DataStore;
import alberto.masmovilchallenge.data.service.AppService;
import alberto.masmovilchallenge.injection.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    void inject(MMApplication application);

    DataStore dataStore();

    AppService appService();
}
