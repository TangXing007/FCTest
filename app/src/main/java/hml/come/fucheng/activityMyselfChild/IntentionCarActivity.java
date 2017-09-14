package hml.come.fucheng.activityMyselfChild;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.LinkedList;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.adapter.IntentionCarAdapter;
import hml.come.fucheng.moudle.IntentionCarData;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/7/27.
 */

public class IntentionCarActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView listView;
    private IntentionCarAdapter adapter;
    private TextView  title_text;
    private LinearLayout back_button;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intention_car);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.bj));
        }
        listView = (ListView)findViewById(R.id.intention_car_listView);
        IntentionHttp();
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("我的意向车型");
    }

    private void IntentionHttp(){
        final RequestParams params = new RequestParams();
        params.put("id", CustomInfo.getInfo().getUser_id());
        HttpClient.get_istance().post(NetUrl.INTENTION_CAR, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new Gson();
                IntentionCarData data = gson.fromJson(response.toString(), IntentionCarData.class);
                if (data != null && data.data != null && data.data.size() != 0){
                    adapter = new IntentionCarAdapter(getApplicationContext(), data.data);
                    listView.setAdapter(adapter);
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {

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
