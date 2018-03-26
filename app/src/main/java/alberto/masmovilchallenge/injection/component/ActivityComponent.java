package alberto.masmovilchallenge.injection.component;

import android.app.Activity;

import alberto.masmovilchallenge.injection.annotation.PerActivity;
import alberto.masmovilchallenge.injection.module.ActivityModule;
import alberto.masmovilchallenge.ui.camera.CameraActivity;
import alberto.masmovilchallenge.ui.gallery.GalleryActivity;
import alberto.masmovilchallenge.ui.login.LoginActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Activities
    Activity activity();

    void inject(LoginActivity homeActivity);

    void inject(GalleryActivity galleryActivity);

    void inject(CameraActivity cameraActivity);
}
