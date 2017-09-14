package hml.come.fucheng.activityResourceChild;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.nio.charset.Charset;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.activity.ApplyForJoin;
import hml.come.fucheng.activity.BaseActivity;
import hml.come.fucheng.activityDealerChild.DealerActivity;
import hml.come.fucheng.activityDealerChild.DealerLandingActivity;
import hml.come.fucheng.moudle.DealerLandingData;
import hml.come.fucheng.net_work.FuChenHttpHandler;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/8/7.
 */

public class ResourceLandingActivity extends BaseActivity implements View.OnClickListener{
    private DealerLandingData landingData;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private TextView title_text, apply_for, landing_button;
    private EditText resource_phone, resource_password;
    private PopupWindow popupWindow;
    @Override
    public void onCreate(Bundle savedInstancaState){
        super.onCreate(savedInstancaState);
        setContentView(R.layout.activity_resource_landing);
        back();
        immersion();
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("资源方登陆");
        apply_for = (TextView)findViewById(R.id.apply_for_resource);
        apply_for.setOnClickListener(this);
        landing_button = (TextView)findViewById(R.id.resource_landing_button);
        landing_button.setOnClickListener(this);
        resource_phone = (EditText)findViewById(R.id.resource_landing_phone);
        resource_password = (EditText)findViewById(R.id.resource_landing_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.resource_landing_button:
                View pop = LayoutInflater.from(this).inflate(R.layout.landing_popupwindow, null);
                popupWindow = new PopupWindow(pop, ViewGroup.LayoutParams.MATCH_PARENT,
                        800);
                popupWindow.showAsDropDown(title_text);
                resource_landing();
                break;
            case R.id.apply_for_resource:
                Intent apply_resourceIntent = new Intent(ResourceLandingActivity.this, ApplyForJoin.class);
                apply_resourceIntent.putExtra("mark", "9");
                startActivity(apply_resourceIntent);
                break;
        }
    }

    private void resource_landing(){
        String phone = resource_phone.getText().toString();
        String password = resource_password.getText().toString();
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("password", password);
        params.put("type", 2);
        if (phone != null || !phone.equals("") || password != null || !password.equals("")){
            HttpClient.get_istance().post(NetUrl.DEALER_LANDING, params,
                    new FuChenHttpHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            super.onSuccess(statusCode, headers, responseBody);
                            Gson gson = new Gson();
                            landingData = gson.fromJson(responseStr, DealerLandingData.class);
                            int status = landingData.status;
                            popupWindow.dismiss();
                            if (status == 1){
                                int id = landingData.uid;
                                String Sid = id + "";
                                preferences = getSharedPreferences("data", MODE_PRIVATE);
                                editor = preferences.edit();
                                editor.putString("resource_id", Sid);
                                editor.commit();
                                CustomInfo.getInfo().setResource_id(Sid);
                                Toast.makeText(ResourceLandingActivity.this,
                                        "登陆成功", Toast.LENGTH_SHORT).show();
                                Intent resourceIntent = new Intent(ResourceLandingActivity.this,
                                        ResourceActivity.class);
                                startActivity(resourceIntent);
                                finish();
                            }else{
                                Toast.makeText(ResourceLandingActivity.this,
                                        "账户密码错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            Toast.makeText(ResourceLandingActivity.this, "账户密码不能空", Toast.LENGTH_SHORT).show();
        }
    }
}
