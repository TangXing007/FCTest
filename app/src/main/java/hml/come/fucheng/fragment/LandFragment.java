package hml.come.fucheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import hml.come.fucheng.R;
import hml.come.fucheng.activityDealerChild.DealerActivity;
import hml.come.fucheng.activityDealerChild.DealerLandingActivity;
import hml.come.fucheng.activityResourceChild.ResourceActivity;
import hml.come.fucheng.activityResourceChild.ResourceLandingActivity;
import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/9/1.
 */

public class LandFragment extends Fragment implements View.OnClickListener{
    private LinearLayout dealerLand;
    private LinearLayout resourceLand;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_land, viewGroup, false);
        dealerLand = (LinearLayout)view.findViewById(R.id.dealer_land);
        dealerLand.setOnClickListener(this);
        resourceLand = (LinearLayout)view.findViewById(R.id.resource_land);
        resourceLand.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dealer_land:
                //if (CustomInfo.getInfo().getType().equals("1")){
                    Intent dealerIntent = new Intent(getActivity(), DealerActivity.class);
                    startActivity(dealerIntent);
               /* }else {
                    Intent dealerIntent  = new Intent(getActivity(), DealerLandingActivity.class);
                    startActivity(dealerIntent);
                }*/
                break;
            case R.id.resource_land:
                //if (CustomInfo.getInfo().getType().equals("2")){
                    Intent resourceIntent = new Intent(getActivity(), ResourceActivity.class);
                    startActivity(resourceIntent);
               /*}else {
                    Intent resourceIntent  = new Intent(getActivity(), ResourceLandingActivity.class);
                    startActivity(resourceIntent);
                }*/
                break;
        }
    }
}
