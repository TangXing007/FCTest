package hml.come.fucheng.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.adapter.PriceAdater;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.moudle.PriceData;

/**
 * Created by TX on 2017/7/22.
 */

public class BiaoZhiActivity extends BaseActivity implements View.OnClickListener{
    private ListView price_listView;
    private LinearLayout back_button;
    private TextView title_text, no_found_text;
    private List<PriceData.Data> list = new ArrayList<>();
    private PriceData priceData;
    private PriceData.Data data;
    private PriceAdater adater;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        immersion();
        price_listView = (ListView)findViewById(R.id.price_listView);
        back_button = (LinearLayout) findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        title_text = (TextView)findViewById(R.id.title_text);
        onHttp();
        price_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent listIntent = new Intent(BiaoZhiActivity.this, LIstShowActivity.class);
                listIntent.putExtra("aid", list.get(position).aid + "");
                startActivity(listIntent);
            }
        });
    }

    private void onHttp(){
        final Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String type = intent.getStringExtra("type");
        int types = Integer.parseInt(type);
        title_text.setText(title);
        String said = intent.getStringExtra("aid");
        int aid = Integer.parseInt(said);
        RequestParams params = new RequestParams();
        params.put("brandId", aid);
        params.put("type", types);
        HttpClient.get_istance().post(NetUrl.PINPAI_CHOSE, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                priceData = new PriceData();
                try {
                    priceData.code = response.getInt("code");
                    priceData.msg = response.getString("msg");
                    if (priceData.code == 1){
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0 ; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            data = priceData.getdate();
                            data.thumbnail = jsonObject.getString("thumbnail");
                            data.manufacturers_price = jsonObject.getString("manufacturers_price");
                            data.aid = jsonObject.getString("aid");
                            //data.brand = jsonObject.getString("brand");
                            data.car_name = jsonObject.getString("car_name");
                            data.dealer_pricing = jsonObject.getString("dealer_pricing");
                            list.add(data);
                            adater = new PriceAdater(BiaoZhiActivity.this, list, 0);
                            price_listView.setAdapter(adater);
                    }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                finish();
                break;
        }
    }
}
