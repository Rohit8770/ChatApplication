package com.example.chatapplication.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.chatapplication.Adapter.ImageSliderAdapter;
import com.example.chatapplication.R;
import java.util.ArrayList;

public class InviteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_invite, container, false);

        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel( "https://i.postimg.cc/Y9Bq0cfb/landing-page-template-world-book-day-celebration-23-2150184554.jpg",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel( "https://i.postimg.cc/2Shdfsdk/My.jpg",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://i.postimg.cc/YCq4Bmt8/1000-F-305868293-Yprj4a-HIGhlxkaw-Hovb-UE7y-K4-My-J1-LXP.jpg",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel( "https://i.postimg.cc/05nJQqcV/Connexia-firma-world-blood-donor-day-1170-jpg.webp",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://i.postimg.cc/KjPFsFjC/grocery-shopping-1.webp",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel("https://i.postimg.cc/Bnj9HhmD/Untitled-design-1-2-1.png",ScaleTypes.CENTER_CROP));

        ImageSlider imageSlider = v.findViewById(R.id.image_slider);
        imageSlider.setImageList(imageList);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerview);
        ImageSliderAdapter adapter = new ImageSliderAdapter(getContext(), imageList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return v;
    }
}
