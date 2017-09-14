package hml.come.fucheng.activityFinancialChild;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
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
import hml.come.fucheng.adapter.JXSAdapter;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.moudle.JXSJinRongData;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/7/19.
 */

public class JInXiaoShangJinRong extends AppCompatActivity {
    private LinearLayout back_button;
    private TextView textView;
    private JXSJinRongData jinRongData;
    private JXSJinRongData.Data data;
    private List<JXSJinRongData.Data> list = new ArrayList<>();
    private ListView listView;
    private View headView;
    @Override
    public void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_jin_xiao_shang);
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
        textView.setText("经销商金融");
        listView = (ListView)findViewById(R.id.jxs_listView);
        headView = LayoutInflater.from(this).inflate(R.layout.jxs_head_view,null);
        doHttp();
    }

    private void doHttp(){
        jinRongData = new JXSJinRongData();
        HttpClient.get_istance().post(NetUrl.JXS_JIN_RONG, null, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200){
                    try {
                        jinRongData.code = response.getInt("code");
                        jinRongData.msg = response.getString("msg");
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            data = jinRongData.getData();
                            data.id = jsonObject.getInt("id");
                            data.name = jsonObject.getString("name");
                            data.yaoqiu = jsonObject.getString("yaoqiu");
                            data.edu = jsonObject.getString("edu");
                            data.qixian = jsonObject.getString("qixian");
                            data.content = jsonObject.getString("content");
                            list.add(data);
                        }
                        JXSAdapter adapter = new JXSAdapter(JInXiaoShangJinRong.this, list);
                        listView.addHeaderView(headView);
                        listView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("aaa");
            }
        });
    }
}
