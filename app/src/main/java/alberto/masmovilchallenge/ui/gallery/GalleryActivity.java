package alberto.masmovilchallenge.ui.gallery;

import android.os.Bundle;

import javax.inject.Inject;

import alberto.masmovilchallenge.R;
import alberto.masmovilchallenge.common.view.activity.BaseActivity;
import butterknife.ButterKnife;

public class GalleryActivity extends BaseActivity implements GalleryMvpView {
    @Inject
    GalleryPresenter galleryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        galleryPresenter.attachView(this);
    }

}
