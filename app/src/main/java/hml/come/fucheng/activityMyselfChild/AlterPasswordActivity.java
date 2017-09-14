package hml.come.fucheng.activityMyselfChild;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.moudle.ZhuCeData;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/7/27.
 */

public class AlterPasswordActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnTouchListener{
    private LinearLayout back_button;
    private EditText old_password, new_password, again_password;
    private ImageView eyes1, eyes2, eyes3;
    private TextView commit_button, cancel_button, title_text;
    private String Sold_password, Snew_password, Sagain_password;
    private ZhuCeData AlterData;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_password);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.bj));
        }
        old_password = (EditText)findViewById(R.id.old_password);
        new_password = (EditText)findViewById(R.id.new_password);
        again_password = (EditText)findViewById(R.id.again_password);
        eyes1 = (ImageView)findViewById(R.id.eyes1);
        eyes1.setOnTouchListener(this);
        eyes2 = (ImageView)findViewById(R.id.eyes2);
        eyes2.setOnTouchListener(this);
        eyes3 = (ImageView)findViewById(R.id.eyes3);
        eyes3.setOnTouchListener(this);
        commit_button = (TextView)findViewById(R.id.alter_password_commit);
        commit_button.setOnClickListener(this);
        cancel_button = (TextView)findViewById(R.id.alter_password_cancel);
        cancel_button.setOnClickListener(this);
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("修改密码");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                finish();
                break;
            case R.id.alter_password_commit:
                alterPasswordHttp();
                break;
            case R.id.alter_password_cancel:
                old_password.setText("");
                new_password.setText("");
                again_password.setText("");
                break;
        }
    }

    private void alterPasswordHttp(){
        Sold_password = old_password.getText().toString();
        Snew_password = new_password.getText().toString();
        Sagain_password = again_password.getText().toString();
        if (Sold_password == null || Sold_password.equals("") || Snew_password == null ||
                Snew_password.equals("") || Sagain_password == null || Sagain_password.equals("")){
            Toast.makeText(AlterPasswordActivity.this, "请填写完整信息", Toast.LENGTH_SHORT).show();
        }else if(Sold_password.equals(Snew_password)){
            Toast.makeText(AlterPasswordActivity.this, "新密码和老密码不能相同", Toast.LENGTH_SHORT)
                    .show();
        }else if (!Snew_password.equals(Sagain_password)){
            Toast.makeText(AlterPasswordActivity.this, "两次密码不相同", Toast.LENGTH_SHORT).show();
        }else {
            RequestParams params = new RequestParams();
            params.put("id", CustomInfo.getInfo().getUser_id());
            params.put("oldPassword", Sold_password);
            params.put("newPassword", Snew_password);
            HttpClient.get_istance().post(NetUrl.ALTER_PASSWORD, params, new JsonHttpResponseHandler(){
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Gson gson = new Gson();
                    AlterData = gson.fromJson(response.toString(), ZhuCeData.class);
                    String status = AlterData.status;
                    if (status.equals("1")){
                        Toast.makeText(AlterPasswordActivity.this, AlterData.msg.toString(), Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(AlterPasswordActivity.this, AlterData.msg.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

                }
            });
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.eyes1:
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        old_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        old_password.setSelection(old_password.getText().length());
                        break;
                    case MotionEvent.ACTION_UP:
                        old_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        old_password.setSelection(old_password.getText().length());
                        break;
                }
                break;
            case R.id.eyes2:
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        new_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        new_password.setSelection(new_password.getText().length());
                        break;
                    case MotionEvent.ACTION_UP:
                        new_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        new_password.setSelection(new_password.getText().length());
                        break;
                }
                break;
            case R.id.eyes3:
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        again_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        again_password.setSelection(again_password.getText().length());
                        break;
                    case MotionEvent.ACTION_UP:
                        again_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        again_password.setSelection(again_password.getText().length());
                        break;
                }
                break;
        }
        return true;
    }
}
