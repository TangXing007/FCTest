package hml.come.fucheng.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.nio.charset.Charset;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.adapter.ColorAdapter;
import hml.come.fucheng.moudle.CarWatchData;
import hml.come.fucheng.moudle.ColorData;
import hml.come.fucheng.moudle.LikeCarData;
import hml.come.fucheng.moudle.ZhuCeData;
import hml.come.fucheng.net_work.FuChenHttpHandler;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/7/21.
 */

public class LIstShowActivity extends BaseActivity implements View.OnClickListener{
    private ImageView car_show_image;
    private TextView car_name, car_name2, car_low_price, car_high_price, title_text, car_jibie,
    car_kucun, car_dress, car_peizhi, car_time, color_more, add_car;
    private CarWatchData watchData;
    private ColorData colorData;
    private ArrayList list = new ArrayList();
    private PopupWindow popupWindow;
    private ListView popup_listView;
    private LinearLayout commit_button, back_button;
    private ZhuCeData addCarData;
    private LikeCarData likeCarData;
    private String status;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_list_show);
        immersion();
        car_show_image = (ImageView)findViewById(R.id.car_show_image);
        car_show_image.setOnClickListener(this);
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        add_car = (TextView)findViewById(R.id.add_intention_car);
        title_text = (TextView)findViewById(R.id.title_text);
        car_name = (TextView)findViewById(R.id.car_show_name);
        car_name2 = (TextView)findViewById(R.id.car_show_name2);
        car_low_price = (TextView)findViewById(R.id.car_low_price);
        car_high_price = (TextView)findViewById(R.id.car_high_price);
        car_jibie = (TextView)findViewById(R.id.car_jibie);
        car_kucun = (TextView)findViewById(R.id.car_kucun);
        car_dress = (TextView)findViewById(R.id.car_dress);
        car_peizhi = (TextView)findViewById(R.id.car_peizhi);
        car_time = (TextView)findViewById(R.id.car_time);
        color_more = (TextView)findViewById(R.id.color_more);
        color_more.setOnClickListener(this);
        commit_button = (LinearLayout)findViewById(R.id.commit_button);
        commit_button.setOnClickListener(this);
        onHttp();
        twoHttp();
        IntentionCarButton();
    }

    private void initPopup(){
        View popup = LayoutInflater.from(this).inflate(R.layout.color_popupwindow, null);
        popupWindow = new PopupWindow(popup, ViewGroup.LayoutParams.MATCH_PARENT, 800);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popup_listView = (ListView)popup.findViewById(R.id.color_list_view);
        popup_listView.setDivider(null);
        ColorAdapter adapter = new ColorAdapter(this, list);
        popup_listView.setAdapter(adapter);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    private void onHttp(){
        Intent intent = getIntent();
        String said = intent.getStringExtra("aid");
        int aid = Integer.parseInt(said);
        RequestParams params = new RequestParams();
        params.put("carId", aid);
        HttpClient.get_istance().post(NetUrl.CAR_WATCH, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200){
                    Gson gson = new Gson();
                    watchData = gson.fromJson(response.toString(), CarWatchData.class);
                }
                title_text.setText(watchData.data.car_name);
                car_name.setText(watchData.data.car_name);
                car_name2.setText(watchData.data.car_name);
                car_low_price.setText(watchData.data.dealer_pricing + "  " + "(元)");
                car_high_price.setText(watchData.data.manufacturers_price + ".00" + "  " + "(元)");
                car_jibie.setText(watchData.data.cars);
                car_kucun.setText(watchData.data.car_number);
                car_dress.setText(watchData.data.address);
                String a = NetUrl.TEST_LF_HEAD + watchData.data.thumbnail;
                Picasso.with(getApplicationContext()).load(NetUrl.TEST_LF_HEAD + watchData.data.thumbnail)
                        .placeholder(R.mipmap.wrong_h)
                        .error(R.mipmap.wrong_h)
                        .into(car_show_image);
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {

            }
        });
    }

    private void twoHttp(){
        Intent intent = getIntent();
        String said = intent.getStringExtra("aid");
        int aid = Integer.parseInt(said);
        RequestParams params = new RequestParams();
        params.put("car_id", aid);
        String a = NetUrl.COLOR;
        HttpClient.get_istance().post(NetUrl.COLOR, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Gson gson = new Gson();
                    colorData = gson.fromJson(response.toString(), ColorData.class);
                    list = colorData.data;
                    initPopup();
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });
    }
    public void addCar(){
        final Intent intent = getIntent();
        String said = intent.getStringExtra("aid");
        int aid = Integer.parseInt(said);
        RequestParams params = new RequestParams();
        params.put("uid", CustomInfo.getInfo().getUser_id());
        params.put("carId", aid);
        HttpClient.get_istance().post(NetUrl.ADD_CAR, params, new JsonHttpResponseHandler(){
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new Gson();
                addCarData = gson.fromJson(response.toString(), ZhuCeData.class);
                String msg = addCarData.msg;
                Toast.makeText(LIstShowActivity.this, msg, Toast.LENGTH_SHORT).show();
                status = addCarData.status;
                if (status.equals("1")){
                    add_car.setText("删除意向车型");
                    commit_button.setBackground(getResources().getDrawable(R.drawable.cancel_button_border));
                }else if (status.equals("3")){
                    add_car.setText("保存意向车型");

                commit_button.setBackground(getResources().getDrawable(R.drawable.commit_button_border));
            }}
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                    String a = "ssss";
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void IntentionCarButton(){
        final Intent intent = getIntent();
        String said = intent.getStringExtra("aid");
        int aid = Integer.parseInt(said);
        RequestParams params = new RequestParams();
        params.put("car_id", aid);
        params.put("uid", CustomInfo.getInfo().getUser_id());
        HttpClient.get_istance().post(NetUrl.PERSON_LIKE_CAR, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new Gson();
                likeCarData = gson.fromJson(response.toString(), LikeCarData.class);
                String stats = likeCarData.status;
                if (stats.equals("1")){
                    add_car.setText("删除意向车型");
                    commit_button.setBackground(getResources().getDrawable(R.drawable.cancel_button_border));
                }else {
                    add_car.setText("保存意向车型");
                    commit_button.setBackground(getResources().getDrawable(R.drawable.commit_button_border));
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
            case R.id.color_more:
                if (popupWindow.isShowing()){
                    popupWindow.dismiss();
                }else {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 0.7f;
                    getWindow().setAttributes(lp);
                    popupWindow.showAsDropDown(color_more);
                }
                break;
            case R.id.commit_button:
                addCar();
                break;
            case R.id.car_show_image:
                final Intent intent = getIntent();
                String said = intent.getStringExtra("aid");
                Intent galleryIntent = new Intent(LIstShowActivity.this, GalleryActivity.class);
                galleryIntent.putExtra("car_id", said);
                startActivity(galleryIntent);
                break;
        }
    }
}
