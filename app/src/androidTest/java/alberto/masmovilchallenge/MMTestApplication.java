package alberto.masmovilchallenge;


import alberto.masmovilchallenge.injection.component.ApplicationComponent;
import alberto.masmovilchallenge.injection.component.DaggerApplicationTestComponent;
import alberto.masmovilchallenge.injection.module.ApplicationTestModule;

public class MMTestApplication extends MMApplication {
    @Override
    public ApplicationComponent createAppComponent() {
        return DaggerApplicationTestComponent.builder()
                .applicationTestModule(new ApplicationTestModule(this))
                .build();
    }
}

