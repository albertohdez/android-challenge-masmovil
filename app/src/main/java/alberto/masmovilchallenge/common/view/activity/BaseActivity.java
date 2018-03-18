package alberto.masmovilchallenge.common.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import alberto.masmovilchallenge.MMApplication;
import alberto.masmovilchallenge.injection.component.ActivityComponent;
import alberto.masmovilchallenge.injection.component.ApplicationComponent;
import alberto.masmovilchallenge.injection.component.DaggerActivityComponent;
import alberto.masmovilchallenge.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {
    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
        initializeInjector();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MMApplication) getApplication()).getApplicationComponent();
    }

    private void initializeInjector() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
}
