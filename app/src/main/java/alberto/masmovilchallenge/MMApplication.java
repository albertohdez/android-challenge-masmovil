package alberto.masmovilchallenge;

import android.app.Application;

import alberto.masmovilchallenge.injection.component.ApplicationComponent;
import alberto.masmovilchallenge.injection.component.DaggerApplicationComponent;
import alberto.masmovilchallenge.injection.module.ApplicationModule;

public class MMApplication extends Application {
    private ApplicationComponent applicationComponent = createAppComponent();

    @Override
    public void onCreate() {
        super.onCreate();
        getApplicationComponent().inject(this);
    }

    protected ApplicationComponent createAppComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
