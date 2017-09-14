package hml.come.fucheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hml.come.fucheng.R;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by Administrator on 2017/8/3.
 */

public class GalleryFragment extends Fragment {
    private static final String URL_IMAGE = "urls_image";

    private ArrayList<String> image_urls = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null){
            image_urls = bundle.getStringArrayList(URL_IMAGE);
        }
        View view = inflater.inflate(R.layout.gallery_fragment, container, false);
        Picasso.with(getContext())
                .load(NetUrl.HEAD + image_urls.get(0)).
                into(((ImageView)view.findViewById(R.id.image1)));
        Picasso.with(getContext())
                .load(NetUrl.HEAD + image_urls.get(1)).
                into(((ImageView)view.findViewById(R.id.image2)));
        Picasso.with(getContext())
                .load(NetUrl.HEAD + image_urls.get(2)).
                into(((ImageView)view.findViewById(R.id.image3)));
        Picasso.with(getContext())
                .load(NetUrl.HEAD + image_urls.get(3)).
                into(((ImageView)view.findViewById(R.id.image4)));
        return view;
    }

    public static Fragment getGalleryFragment(ArrayList<String> urls){
       Fragment fragment = new GalleryFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(URL_IMAGE, urls);
        fragment.setArguments(bundle);
        return fragment;
    }

}
