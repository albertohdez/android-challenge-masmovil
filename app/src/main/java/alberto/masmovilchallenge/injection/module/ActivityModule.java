package alberto.masmovilchallenge.injection.module;

import android.app.Activity;

import alberto.masmovilchallenge.common.navigator.Navigator;
import alberto.masmovilchallenge.data.prefs.DataStore;
import alberto.masmovilchallenge.injection.annotation.PerActivity;
import alberto.masmovilchallenge.ui.camera.CameraPresenter;
import alberto.masmovilchallenge.ui.gallery.GalleryPresenter;
import alberto.masmovilchallenge.ui.login.LoginPresenter;
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
    Navigator provideNavigator() {
        return new Navigator(mActivity);
    }

    @Provides
    @PerActivity
    LoginPresenter provideLoginPresenter(DataStore dataStore) {
        return new LoginPresenter(dataStore);
    }

    @Provides
    @PerActivity
    GalleryPresenter provideGalleryPresenter() {
        return new GalleryPresenter();
    }

    @Provides
    @PerActivity
    CameraPresenter provideCameraPresenter() {
        return new CameraPresenter();
    }
}
