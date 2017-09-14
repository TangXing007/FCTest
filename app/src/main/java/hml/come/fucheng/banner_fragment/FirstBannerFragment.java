package hml.come.fucheng.banner_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import hml.come.fucheng.R;

/**
 * Created by TX on 2017/7/18.
 */

public class FirstBannerFragment extends Fragment {
    private ImageView imageView;
    public static final String IMAGE_SRC = "image_src";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.banner_viewpage_fragment, viewGroup, false);
        Bundle bundle = getArguments();
        imageView = (ImageView)view.findViewById(R.id.banner1_image);
        imageView.setImageResource(bundle.getInt(IMAGE_SRC));
        return view;
    }

    public static Fragment getFragment(int imageSrc){
        Fragment fragment = new FirstBannerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IMAGE_SRC, imageSrc);
        fragment.setArguments(bundle);
        return fragment;
    }
}
