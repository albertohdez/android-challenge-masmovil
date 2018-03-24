package alberto.masmovilchallenge.ui.gallery;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import alberto.masmovilchallenge.MMTestApplication;
import alberto.masmovilchallenge.R;
import alberto.masmovilchallenge.common.model.ImgurPhotoDto;
import alberto.masmovilchallenge.common.model.ImgurUser;
import alberto.masmovilchallenge.common.model.response.AlbumResponse;
import alberto.masmovilchallenge.data.prefs.DataStore;
import alberto.masmovilchallenge.data.service.AppService;
import alberto.masmovilchallenge.injection.component.ApplicationTestComponent;
import alberto.masmovilchallenge.utils.RecyclerViewItemCountAssertion;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class GalleryTest {
    @Inject
    AppService service;

    @Inject
    DataStore dataStore;

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

        ImgurUser imgurUser = new ImgurUser("me", "0s9dfuy8auyf",
                "a0sbv0badsfgj", 342384723);
        when(dataStore.loadImgurUser()).thenReturn(imgurUser);
        AlbumResponse albumResponse = createAlbumResponse();
        when(service.getAllImages(any(String.class))).thenReturn(Observable.just(albumResponse));
    }

    @Test
    public void testGallery() {
        galleryActivityActivityTestRule.launchActivity(null);
        onView(withId(R.id.rvImages)).check(new RecyclerViewItemCountAssertion(2));
    }

    private AlbumResponse createAlbumResponse() {
        List<ImgurPhotoDto> data = new ArrayList<>();
        ImgurPhotoDto photo = new ImgurPhotoDto();
        data.add(photo);
        data.add(photo);
        AlbumResponse albumResponse = new AlbumResponse();
        albumResponse.setData(data);
        return albumResponse;
    }
}
