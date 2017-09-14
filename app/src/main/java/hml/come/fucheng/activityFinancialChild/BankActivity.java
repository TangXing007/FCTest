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
import hml.come.fucheng.adapter.BankAdapter;
import hml.come.fucheng.moudle.BankData;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/7/19.
 */

public class BankActivity extends AppCompatActivity {
    private LinearLayout back_button;
    private TextView textView;
    private ListView listView;
    private View headView;
    private List<BankData.Data> list = new ArrayList<>();
    private BankData bankData;
    @Override
    public void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_banck);
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
        textView.setText("银行按揭");
        headView = LayoutInflater.from(this).inflate(R.layout.bank_head_view, null);
        onHttp();
    }

    public void onHttp(){
        bankData = new BankData();
        HttpClient.get_istance().post(NetUrl.BANK, null, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200){
                    try {
                        bankData.code = response.getString("code");
                        bankData.msg = response.getString("msg");
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            BankData.Data data = bankData.getData();
                            data.bank_category = jsonObject.getString("bank_category");
                            data.id = jsonObject.getInt("id");
                            data.bank_name = jsonObject.getString("bank_name");
                            data.content = jsonObject.getString("content");
                            list.add(data);
                        }
                        BankAdapter adapter = new BankAdapter(BankActivity.this, list);
                        listView = (ListView)findViewById(R.id.bank_listView);
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
