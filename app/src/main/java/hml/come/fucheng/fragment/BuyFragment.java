package hml.come.fucheng.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.adapter.BuyViewPageAdapter;
import hml.come.fucheng.net_work.HttpClient;

/**
 * Created by TX on 2017/7/18.
 */

public class BuyFragment extends Fragment implements View.OnClickListener{
    private Fragment[] fragments = new Fragment[]{new NewCarFragment(), new StockCarFragment(),
    new OrderCarFragment(), new NewEnergyFragment()};
    private ViewPager viewPager;
    private BuyViewPageAdapter adapter;
    private TextView new_car, stock_car, order_car, new_energy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_buy, viewGroup, false);
        viewPager = (ViewPager)view.findViewById(R.id.buy_viewPage);
        adapter = new BuyViewPageAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        new_car = (TextView)view.findViewById(R.id.new_car);
        new_car.setTextColor(getResources().getColor(R.color.blue_h));
        new_car.setOnClickListener(this);
        stock_car = (TextView)view.findViewById(R.id.stock_car);
        stock_car.setOnClickListener(this);
        order_car = (TextView)view.findViewById(R.id.order_car);
        order_car.setOnClickListener(this);
        new_energy = (TextView)view.findViewById(R.id.new_energy);
        new_energy.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.new_car:
                new_car.setTextColor(getResources().getColor(R.color.blue_h));
                stock_car.setTextColor(getResources().getColor(R.color.text));
                order_car.setTextColor(getResources().getColor(R.color.text));
                new_energy.setTextColor(getResources().getColor(R.color.text));
                viewPager.setCurrentItem(0);
                break;
            case R.id.stock_car:
                new_car.setTextColor(getResources().getColor(R.color.text));
                stock_car.setTextColor(getResources().getColor(R.color.blue_h));
                order_car.setTextColor(getResources().getColor(R.color.text));
                new_energy.setTextColor(getResources().getColor(R.color.text));
                viewPager.setCurrentItem(1);
                break;
            case R.id.order_car:
                new_car.setTextColor(getResources().getColor(R.color.text));
                stock_car.setTextColor(getResources().getColor(R.color.text));
                order_car.setTextColor(getResources().getColor(R.color.blue_h));
                new_energy.setTextColor(getResources().getColor(R.color.text));
                viewPager.setCurrentItem(2);
                break;
            case R.id.new_energy:
                new_car.setTextColor(getResources().getColor(R.color.text));
                stock_car.setTextColor(getResources().getColor(R.color.text));
                order_car.setTextColor(getResources().getColor(R.color.text));
                new_energy.setTextColor(getResources().getColor(R.color.blue_h));
                viewPager.setCurrentItem(3);
                break;
        }
    }
}
