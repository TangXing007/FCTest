package hml.come.fucheng.activityMyselfChild;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.moudle.ZhuCeData;
import hml.come.fucheng.net_work.FuChenHttpHandler;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/7/27.
 */

public class AmendActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout back_button;
    private TextView title_text, amand_button;
    private EditText amand_text;
    private ZhuCeData data;
    @Override
    public void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_amand);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(getResources().getColor(R.color.bj));
        }
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("修改姓名");
        amand_button = (TextView)findViewById(R.id.amand_button);
        amand_button.setOnClickListener(this);
        amand_text = (EditText)findViewById(R.id.amand_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                finish();
                break;
            case R.id.amand_button:
                if (TextUtils.isEmpty(amand_text.getText())){
                    Toast.makeText(AmendActivity.this, "名字不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    RequestParams params = new RequestParams();
                    params.put("id", CustomInfo.getInfo().getUser_id());
                    params.put("name", amand_text.getText());
                    String a = NetUrl.AMEND_NAME;
                    HttpClient.get_istance().post(NetUrl.AMEND_NAME, params, new FuChenHttpHandler(){
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if (responseStr != null || !responseBody.equals("")){
                                Gson gson = new Gson();
                                data = gson.fromJson(responseStr, ZhuCeData.class);
                            }
                            Intent amandIntent = new Intent(AmendActivity.this, PersonDataActivity.class);
                            amandIntent.putExtra("pet_name", amand_text.getText().toString());
                            AmendActivity.this.setResult(1, amandIntent);
                            finish();
                        }
                    });
                }
                break;
        }
    }
}
