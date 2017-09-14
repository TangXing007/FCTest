package hml.come.fucheng.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import hml.come.fucheng.R;
import hml.come.fucheng.activityFinancialChild.BankActivity;
import hml.come.fucheng.activityFinancialChild.JInXiaoShangJinRong;
import hml.come.fucheng.activityFinancialChild.RongZiActivity;

/**
 * Created by TX on 2017/7/19.
 */

public class JinRongActivity extends BaseActivity implements View.OnClickListener{
    private TextView title_text, financial_button1, financial_button2, financial_button3;
    private LinearLayout back_button;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jinrong);
        immersion();
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("金融");
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
        financial_button1 = (TextView)findViewById(R.id.financial_button1);
        financial_button1.setOnClickListener(this);
        financial_button2 = (TextView)findViewById(R.id.financial_button2);
        financial_button2.setOnClickListener(this);
        financial_button3 = (TextView)findViewById(R.id.financial_button3);
        financial_button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                finish();
                break;
            case R.id.financial_button1:
                Intent intent1 = new Intent(JinRongActivity.this, JInXiaoShangJinRong.class);
                startActivity(intent1);
                break;
            case R.id.financial_button2:
                Intent intent2 = new Intent(JinRongActivity.this, RongZiActivity.class);
                startActivity(intent2);
                break;
            case R.id.financial_button3:
                Intent intent3 = new Intent(JinRongActivity.this, BankActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
