package com.example.chatapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.chatapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder> {

    private Context context;
    private List<SlideModel> slideList;

    public ImageSliderAdapter(Context context, List<SlideModel> slideList) {
        this.context = context;
        this.slideList = slideList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_slide_item_file, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        SlideModel slideModel = slideList.get(position);
        holder.itemTitle.setText(slideModel.getTitle());

        ImageSlider itemImageSlider = holder.itemView.findViewById(R.id.itemImageSlider);
        itemImageSlider.setImageList(Collections.singletonList(slideModel));

    }

    @Override
    public int getItemCount() {
        return slideList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        ImageSlider itemImageSlider;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemImageSlider = itemView.findViewById(R.id.itemImageSlider);
        }
    }
}
