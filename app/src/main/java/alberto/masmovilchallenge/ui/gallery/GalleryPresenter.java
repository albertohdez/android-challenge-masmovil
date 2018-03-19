package alberto.masmovilchallenge.ui.gallery;

import alberto.masmovilchallenge.common.model.response.GalleryResponse;
import alberto.masmovilchallenge.common.view.presenter.BasePresenter;
import alberto.masmovilchallenge.data.service.AppService;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class GalleryPresenter extends BasePresenter<GalleryMvpView> {

    private final AppService appService;

    public GalleryPresenter(AppService appService) {
        this.appService = appService;
    }

    public void getGallery(String section, String sort, int page, boolean showViral) {
        addRxSubscription(appService.getGallery(section, sort, page, showViral)
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
