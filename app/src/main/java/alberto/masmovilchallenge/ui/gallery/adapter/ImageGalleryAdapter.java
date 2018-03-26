package alberto.masmovilchallenge.ui.gallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import alberto.masmovilchallenge.R;
import alberto.masmovilchallenge.common.model.ImgurBaseDto;
import alberto.masmovilchallenge.common.model.response.AlbumResponse;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder> {

    private AlbumResponse galleryImages;
    private Context context;
    private boolean deleteFlow = false;
    private OnRemoveClickListener onRemoveClickListener;

    public interface OnRemoveClickListener {
        void onRemoveItemClick(String deleteHash);
    }

    public ImageGalleryAdapter(AlbumResponse albumResponse) {
        this.galleryImages = albumResponse;
    }

    @Override
    public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.row_gallery, parent, false);
        return new MyViewHolder(photoView);
    }

    @Override
    public void onBindViewHolder(ImageGalleryAdapter.MyViewHolder holder, int position) {
        ImgurBaseDto image = galleryImages.getData().get(position);
        ImageView imageView = holder.imageView;

        Glide.with(context)
                .load(image.getLink())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView);


        ImageView removeImageView = holder.removeImageView;
        removeImageView.setVisibility(deleteFlow ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return (galleryImages.getData().size());
    }

    public void updateRemoveFlow() {
        deleteFlow = !deleteFlow;
    }

    public void setOnRemoveClickListener(OnRemoveClickListener onRemoveClickListener) {
        this.onRemoveClickListener = onRemoveClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivImage)
        ImageView imageView;

        @BindView(R.id.ivRemoveImage)
        ImageView removeImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.ivRemoveImage)
        public void onClick() {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                ImgurBaseDto image = galleryImages.getData().get(position);
                onRemoveClickListener.onRemoveItemClick(image.getDeleteHash());
            }
        }
    }
}

