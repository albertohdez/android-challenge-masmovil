package alberto.masmovilchallenge.injection.module;

import android.app.Activity;

import alberto.masmovilchallenge.common.navigator.Navigator;
import alberto.masmovilchallenge.common.view.dialog.DialogManager;
import alberto.masmovilchallenge.data.prefs.DataStore;
import alberto.masmovilchallenge.data.service.AppService;
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
    DialogManager provideDialogManager() {
        return new DialogManager(mActivity);
    }

    @Provides
    @PerActivity
    LoginPresenter provideLoginPresenter(DataStore dataStore) {
        return new LoginPresenter(dataStore);
    }

    @Provides
    @PerActivity
    GalleryPresenter provideGalleryPresenter(AppService appService, DataStore dataStore) {
        return new GalleryPresenter(appService, dataStore);
    }

    @Provides
    @PerActivity
    CameraPresenter provideCameraPresenter(AppService appService) {
        return new CameraPresenter(appService);
    }
}
