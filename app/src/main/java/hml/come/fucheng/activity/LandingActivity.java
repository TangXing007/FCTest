package hml.come.fucheng.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.nio.charset.Charset;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.net_work.FuChenHttpHandler;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.moudle.LandingData;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by TX on 2017/7/24.
 */

public class LandingActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener{
    public SharedPreferences preferences;
    public SharedPreferences.Editor editor;
    private EditText landing_phone, landing_password;
    private TextView landing_button, new_zhuce, remeber_password, title;
    private LandingData landingData;
    private String phone, password;
    private ImageView eye;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        immersion();
        landing_phone = (EditText)findViewById(R.id.langding_phone);
        landing_password = (EditText)findViewById(R.id.landing_password);
        landing_button = (TextView)findViewById(R.id.landing_button);
        title = (TextView)findViewById(R.id.title_text);
        title.setText("登陆");
        preferences = getSharedPreferences("data", MODE_PRIVATE);
        editor = preferences.edit();
        new_zhuce = (TextView)findViewById(R.id.new_zhuce);
        remeber_password = (TextView)findViewById(R.id.remeber_password);
        landing_button.setOnClickListener(this);
        new_zhuce.setOnClickListener(this);
        remeber_password.setOnClickListener(this);
        eye = (ImageView)findViewById(R.id.land_eye);
        eye.setOnTouchListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if (requestCode != 0 && data != null){
                    phone = data.getStringExtra("phone");
                    password = data.getStringExtra("password");
                    landing_phone.setText(phone);
                    landing_password.setText(password);
                    break;
                }
            case 2:
                if (requestCode != 0 && data != null) {
                    phone = data.getStringExtra("phone");
                    password = data.getStringExtra("password");
                    landing_phone.setText(phone);
                    landing_password.setText(password);
                    break;
                }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.landing_button:
                final String number = landing_phone.getText().toString();
                final String password = landing_password.getText().toString();
                if (number == null || number.equals("") || password == null || password.equals("")){
                    Toast.makeText(LandingActivity.this,"账户和密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    RequestParams params = new RequestParams();
                    params.put("phone", number);
                    params.put("password", password);
                    HttpClient.get_istance().post(NetUrl.USER_LANDING, params, new FuChenHttpHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            super.onSuccess(statusCode, headers, responseBody);
                            Gson gson = new Gson();
                            landingData = gson.fromJson(responseStr, LandingData.class);
                            String status = landingData.status;
                            if (status.equals("1")){
                                editor.putString("type", landingData.type);
                                editor.putString("number", number);
                                editor.putString("password", password);
                                editor.putString("user_id" , landingData.uid);
                                editor.commit();
                                CustomInfo.getInfo().setUser_id(landingData.uid);
                                CustomInfo.getInfo().setType(landingData.type);
                                Toast.makeText(LandingActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LandingActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else if (status.equals("2")){
                                Toast.makeText(LandingActivity.this, landingData.msg, Toast.LENGTH_SHORT).show();
                            }else if (status.equals("3")){
                                Toast.makeText(LandingActivity.this, landingData.msg, Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LandingActivity.this, "登陆失败请检查账户密码", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
            case R.id.new_zhuce:
                Intent zhuceIntent = new Intent(LandingActivity.this, ZhuCeActivity.class);
                startActivityForResult(zhuceIntent, 1);
                break;
            case R.id.remeber_password:
                Intent forgetPasswordIntent = new Intent(LandingActivity.this, ForgetPasswordActivity.class);
                startActivityForResult(forgetPasswordIntent, 2);
                break;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.land_eye:
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        landing_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        landing_password.setSelection(landing_password.getText().length());
                        break;
                    case MotionEvent.ACTION_UP:
                        landing_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        landing_password.setSelection(landing_password.getText().length());
                        break;
                }
                break;
        }
        return true;
    }
}
