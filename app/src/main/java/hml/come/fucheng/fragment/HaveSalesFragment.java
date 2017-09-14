package hml.come.fucheng.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.adapter.OptionsAdapter;
import hml.come.fucheng.moudle.SalesManagementListData;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/8/11.
 */

public class HaveSalesFragment extends Fragment {
    private SalesManagementListData saleData;
    private ArrayList<SalesManagementListData.Data> list;
    private OptionsAdapter adapter;
    private ListView listView;
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        super.onCreateView(inflater, viewGroup, savedInstanceState);
        View view = inflater.inflate(R.layout.sales_fragment, viewGroup, false);
        listView = (ListView)view.findViewById(R.id.have_sales_list);
        textView = (TextView)view.findViewById(R.id.have_sales_text);
        saleHttp();
        return view;
    }

    private void saleHttp(){
        RequestParams params = new RequestParams();
        params.put("uid", CustomInfo.getInfo().getUser_id());
        params.put("type", 1);
        HttpClient.get_istance().post(NetUrl.SALES_MANAGEMENT, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new Gson();
                saleData = gson.fromJson(response.toString(), SalesManagementListData.class);
                if (saleData.data.key1 != null && saleData.data.key1.size() != 0){
                    list = saleData.data.key1;
                    adapter  = new OptionsAdapter(getContext(), list);
                    listView.setVisibility(View.VISIBLE);
                    listView.setAdapter(adapter);
                    textView.setVisibility(View.GONE);
                }else {
                    listView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });
    }
}
