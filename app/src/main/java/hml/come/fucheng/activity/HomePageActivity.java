package hml.come.fucheng.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/7/17.
 */

public class HomePageActivity extends BaseActivity {
    private SharedPreferences preferences;
    private Boolean islanding;
    Handler handler = new Handler();
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        preferences = getSharedPreferences("data", MODE_PRIVATE);
        handler.postDelayed(runnable, 1000);
        immersion();
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (CustomInfo.getInfo().islanding()){
                Intent i = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }else {
                Intent intent = new Intent(HomePageActivity.this, LandingActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
