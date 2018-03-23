package alberto.masmovilchallenge.injection.component;


import javax.inject.Singleton;

import alberto.masmovilchallenge.injection.module.ApplicationTestModule;
import alberto.masmovilchallenge.ui.gallery.GalleryTest;
import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface ApplicationTestComponent extends ApplicationComponent {

    void inject(GalleryTest galleryTest);
}
