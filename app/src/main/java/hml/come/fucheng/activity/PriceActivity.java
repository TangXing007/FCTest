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

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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
 * Created by TX on 2017/7/21.
 */

public class PriceActivity extends BaseActivity implements View.OnClickListener{
    private ListView price_listView;
    private LinearLayout back_button;
    private TextView title_text, no_found_text;
    private List<PriceData.Data> list = new ArrayList<>();
    private PriceData priceData;
    private PriceAdater adater;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        immersion();
        price_listView = (ListView)findViewById(R.id.price_listView);
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        title_text = (TextView)findViewById(R.id.title_text);
        no_found_text = (TextView)findViewById(R.id.no_found_text);
        onHttp();
        price_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent listIntent = new Intent(PriceActivity.this, LIstShowActivity.class);
                listIntent.putExtra("aid", list.get(position).aid + "");
                startActivity(listIntent);
            }
        });
    }

    private void onHttp(){
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        title_text.setText(title);
        String spriceup = intent.getStringExtra("priceup");
        String spricetop = intent.getStringExtra("pricetop");
        int priceup = Integer.parseInt(spriceup);
        int pricetop = Integer.parseInt(spricetop);
        RequestParams params = new RequestParams();
        params.put("min", priceup);
        params.put("max", pricetop);
        params.put("type", 1);
        HttpClient.get_istance().post(NetUrl.PRICE, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200){
                    Gson gson = new Gson();
                    priceData = gson.fromJson(response.toString(), PriceData.class);
                }
                list = priceData.data;
                adater = new PriceAdater(PriceActivity.this, list, 0);
                price_listView.setAdapter(adater);
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println(errorResponse);
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
