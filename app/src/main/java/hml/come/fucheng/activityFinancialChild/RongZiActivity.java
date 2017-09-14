package hml.come.fucheng.activityFinancialChild;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.adapter.RongZiAdapter;
import hml.come.fucheng.moudle.GsonRongziData;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.moudle.RongZiData;

/**
 * Created by TX on 2017/7/19.
 */

public class RongZiActivity extends AppCompatActivity {
    private LinearLayout back_button;
    private TextView textView;
    private List<RongZiData.Data> list = new ArrayList<>();
    private ListView listView;
    private View headView;
    private RongZiData rongZiData;
    @Override
    public void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_rongzi);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.bj));
        }
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView = (TextView)findViewById(R.id.title_text);
        textView.setText("融资租赁");
        headView = LayoutInflater.from(this).inflate(R.layout.rongzi_head_view,null);
        onHttp();
    }

    public void onHttp(){
        rongZiData = new RongZiData();
        HttpClient.get_istance().post(NetUrl.RONG_ZI_ZU_LIN, null, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200){
                    Gson gson = new Gson();
                    GsonRongziData gsonRongziData = gson.fromJson(response.toString(), GsonRongziData.class);
                    try {
                        rongZiData.code = response.getString("code");
                        rongZiData.msg = response.getString("msg");
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            RongZiData.Data data = rongZiData.getData();
                            data.id = jsonObject.getString("id");
                            data.name = jsonObject.getString("name");
                            data.description = jsonObject.getString("description");
                            data.content = jsonObject.getString("content");
                            list.add(data);
                        }
                        RongZiAdapter adapter = new RongZiAdapter(RongZiActivity.this, list);
                        listView = (ListView)findViewById(R.id.rongzi_list);
                        listView.addHeaderView(headView);
                        listView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });
    }
}
