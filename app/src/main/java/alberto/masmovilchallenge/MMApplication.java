package alberto.masmovilchallenge;

import android.app.Application;

import alberto.masmovilchallenge.injection.component.ApplicationComponent;
import alberto.masmovilchallenge.injection.component.DaggerApplicationComponent;
import alberto.masmovilchallenge.injection.module.ApplicationModule;

public class MMApplication extends Application {
    private ApplicationComponent mApplicationComponent;
    private static MMApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        setInstance(this);
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public static synchronized MMApplication getInstance() {
        return mInstance;
    }

    public static void setInstance(MMApplication mInstance) {
        MMApplication.mInstance = mInstance;
    }
}
