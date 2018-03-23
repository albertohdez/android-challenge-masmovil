package alberto.masmovilchallenge;


import alberto.masmovilchallenge.injection.component.ApplicationComponent;
import alberto.masmovilchallenge.injection.component.DaggerApplicationTestComponent;
import alberto.masmovilchallenge.injection.module.ApplicationTestModule;

public class MMTestApplication extends MMApplication {
    @Override
    public void createAppComponent() {
        applicationComponent = DaggerApplicationTestComponent.builder()
                .applicationModule(new ApplicationTestModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}

