package alberto.masmovilchallenge.ui.gallery;

import alberto.masmovilchallenge.common.model.response.GalleryResponse;
import alberto.masmovilchallenge.common.view.presenter.BasePresenter;
import alberto.masmovilchallenge.data.prefs.DataStore;
import alberto.masmovilchallenge.data.service.AppService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GalleryPresenter extends BasePresenter<GalleryMvpView> {

    private final AppService appService;
    private final DataStore dataStore;

    public GalleryPresenter(AppService appService, DataStore dataStore) {
        this.appService = appService;
        this.dataStore = dataStore;
    }

    public void getGallery() {

        addRxSubscription(appService.getProfileAlbums(dataStore.loadImgurUser().getUsername(), 0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GalleryResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().getGalleryError();
                    }

                    @Override
                    public void onNext(GalleryResponse galleryResponse) {
                        getMvpView().getGallerySuccess(galleryResponse);
                    }
                })
        );
    }
}
