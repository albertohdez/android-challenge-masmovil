package alberto.masmovilchallenge.common.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import alberto.masmovilchallenge.MMApplication;
import alberto.masmovilchallenge.di.component.ActivityComponent;
import alberto.masmovilchallenge.di.component.ApplicationComponent;
import alberto.masmovilchallenge.di.component.DaggerActivityComponent;
import alberto.masmovilchallenge.di.module.ActivityModule;

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
