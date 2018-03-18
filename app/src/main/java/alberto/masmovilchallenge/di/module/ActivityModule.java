package alberto.masmovilchallenge.di.module;

import android.app.Activity;

import alberto.masmovilchallenge.di.annotation.PerActivity;
import alberto.masmovilchallenge.login.LoginPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter();
    }
}
