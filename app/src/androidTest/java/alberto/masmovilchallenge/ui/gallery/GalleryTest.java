package alberto.masmovilchallenge.ui.gallery;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.inject.Inject;

import alberto.masmovilchallenge.MMTestApplication;
import alberto.masmovilchallenge.data.prefs.DataStore;
import alberto.masmovilchallenge.data.service.AppService;
import alberto.masmovilchallenge.injection.component.ApplicationTestComponent;

import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
public class GalleryTest {

    @Inject
    AppService service;

    @Inject
    DataStore dataStore;

    @Mock
    GalleryPresenter galleryPresenter;

    @Rule
    public ActivityTestRule<GalleryActivity> galleryActivityActivityTestRule =
            new ActivityTestRule<>(GalleryActivity.class, true, false);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MMTestApplication app = (MMTestApplication) instrumentation.getTargetContext().getApplicationContext();
        ApplicationTestComponent component = (ApplicationTestComponent) app.getApplicationComponent();
        component.inject(this);
        galleryActivityActivityTestRule.launchActivity(null);
//        galleryPresenter.attachView(galleryActivityActivityTestRule.getActivity());

    }

    @Test
    public void testShowActivatedWebViewDisabled() {
        when(dataStore.loadImgurUser().getUsername()).thenReturn("me");
        galleryPresenter.getGallery();
//        when(service.getAllImages("me")).thenReturn(Observable.just(new AlbumResponse()));
    }
}
