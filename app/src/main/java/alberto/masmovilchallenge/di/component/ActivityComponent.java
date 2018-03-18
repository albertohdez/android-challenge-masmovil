package alberto.masmovilchallenge.di.component;

import android.app.Activity;

import alberto.masmovilchallenge.di.annotation.PerActivity;
import alberto.masmovilchallenge.di.module.ActivityModule;
import alberto.masmovilchallenge.login.LoginActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Activities
    Activity activity();

    void inject(LoginActivity homeActivity);
}
