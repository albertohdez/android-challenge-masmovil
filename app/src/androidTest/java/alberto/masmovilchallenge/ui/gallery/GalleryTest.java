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
import alberto.masmovilchallenge.common.model.response.BasicResponse;
import alberto.masmovilchallenge.data.prefs.DataStore;
import alberto.masmovilchallenge.data.service.AppService;
import alberto.masmovilchallenge.injection.component.ApplicationTestComponent;
import alberto.masmovilchallenge.utils.RecyclerViewItemCountAssertion;
import alberto.masmovilchallenge.utils.RecyclerViewMatcher;
import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class GalleryTest {
    private static final String ID1 = "FSDF8AS8G";
    private static final String ID2 = "SADGS55YW";
    @Inject
    AppService service;

    @Inject
    DataStore dataStore;

    @Rule
    public ActivityTestRule<GalleryActivity> galleryActivityActivityTestRule =
            new ActivityTestRule<>(GalleryActivity.class, true, false);
    private AlbumResponse albumResponse;

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
        albumResponse = createAlbumResponse();
        when(service.getAllImages(any(String.class))).thenReturn(Observable.just(albumResponse));
    }

    @Test
    public void testGallery() {
        galleryActivityActivityTestRule.launchActivity(null);
        onView(withId(R.id.rvImages)).check(new RecyclerViewItemCountAssertion(2));
    }

    @Test
    public void testDeleteImage() throws InterruptedException {
        galleryActivityActivityTestRule.launchActivity(null);

        when(service.deleteImage(any(String.class))).thenReturn(Observable.just(new BasicResponse()));

        onView(withId(R.id.action_delete)).perform(click());
        onView(withRecyclerView(R.id.rvImages).atPositionOnView(0, R.id.ivRemoveImage)).perform(click());
        onView(withText(R.string.ok)).perform(click());
        //TODO Improve this tricky logic
        albumResponse.getData().remove(0);

        onView(withId(R.id.rvImages)).check(new RecyclerViewItemCountAssertion(1));
    }

    private AlbumResponse createAlbumResponse() {
        List<ImgurPhotoDto> data = new ArrayList<>();
        ImgurPhotoDto photo1 = new ImgurPhotoDto();
        photo1.setId(ID1);
        data.add(photo1);
        ImgurPhotoDto photo2 = new ImgurPhotoDto();
        photo2.setId(ID2);
        data.add(photo2);
        AlbumResponse albumResponse = new AlbumResponse();
        albumResponse.setData(data);
        return albumResponse;
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {

        return new RecyclerViewMatcher(recyclerViewId);
    }

}
