package hml.come.fucheng.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import hml.come.fucheng.fragment.GalleryFragment;
import hml.come.fucheng.moudle.LocalGalleryData;

/**
 * Created by TX on 2017/8/3.
 */

public class GalleryAdapter extends FragmentStatePagerAdapter {
    private ArrayList<LocalGalleryData> localGalleryDatas;
    public GalleryAdapter(FragmentManager fm, ArrayList<LocalGalleryData> datas) {
        super(fm);
        localGalleryDatas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return GalleryFragment.getGalleryFragment(localGalleryDatas.get(position).arrayList);
    }

    @Override
    public int getCount() {
        return localGalleryDatas.size();
    }
}
