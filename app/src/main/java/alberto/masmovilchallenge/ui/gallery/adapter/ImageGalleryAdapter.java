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

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder> {

    private AlbumResponse galleryImages;
    private Context context;

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
    }

    @Override
    public int getItemCount() {
        return (galleryImages.getData().size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;

        public MyViewHolder(View itemView) {

            super(itemView);
            imageView = itemView.findViewById(R.id.ivImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                ImgurBaseDto image = galleryImages.getData().get(position);
                //TODO
            }
        }
    }
}

