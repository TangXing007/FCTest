package hml.come.fucheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.activity.LIstShowActivity;
import hml.come.fucheng.adapter.PriceAdater;
import hml.come.fucheng.moudle.PriceData;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/7/18.
 */

public class OrderCarFragment extends Fragment {
    private ListView listView;
    private PriceData data;
    private PriceAdater adater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInsatanceState){
        View view = inflater.inflate(R.layout.fragment_order_car, viewGroup, false);
        listView = (ListView)view.findViewById(R.id.order_car_listView);
        Http();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent listIntent = new Intent(getActivity(), LIstShowActivity.class);
                listIntent.putExtra("aid", data.data.get(position).aid + "");
                startActivity(listIntent);
            }
        });
        return view;
    }

    public void Http(){
        RequestParams params = new RequestParams();
        params.put("car_type", 3);
        HttpClient.get_istance().post(NetUrl.ORDER_CAR, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new Gson();
                data = gson.fromJson(response.toString(), PriceData.class);
                if (data != null && data.data != null && data.data.size() != 0){
                    adater = new PriceAdater(getActivity(), data.data, 0);
                    listView.setAdapter(adater);
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {

            }
        });
    }
}
