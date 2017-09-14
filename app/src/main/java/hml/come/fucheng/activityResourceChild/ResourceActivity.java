package hml.come.fucheng.activityResourceChild;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import hml.come.fucheng.R;
import hml.come.fucheng.activity.BaseActivity;
import hml.come.fucheng.activityDealerChild.BankCardActivity;
import hml.come.fucheng.activityDealerChild.ContractActivity;
import hml.come.fucheng.activityDealerChild.DealerActivity;
import hml.come.fucheng.activityDealerChild.DealerLandingActivity;
import hml.come.fucheng.activityDealerChild.MyMessageActivity;
import hml.come.fucheng.activityDealerChild.OnLineCarActivity;
import hml.come.fucheng.moudle.BankCardData;
import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/8/7.
 */

public class ResourceActivity extends BaseActivity implements View.OnClickListener{
    private TextView title_text;
    private LinearLayout my_options, on_line_car, my_contract, my_message, bank_card,
            out;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);
        back();
        immersion();
        my_options = (LinearLayout)findViewById(R.id.my_options);
        my_options.setOnClickListener(this);
        on_line_car = (LinearLayout)findViewById(R.id.resource_on_line_car);
        on_line_car.setOnClickListener(this);
        my_contract = (LinearLayout)findViewById(R.id.resource_my_contract);
        my_contract.setOnClickListener(this);
        my_message = (LinearLayout)findViewById(R.id.resource_my_message);
        my_message.setOnClickListener(this);
        bank_card = (LinearLayout)findViewById(R.id.resource_bank_card);
        bank_card.setOnClickListener(this);
        /*out = (LinearLayout)findViewById(R.id.resource_out);
        out.setOnClickListener(this);*/
        title_text =  (TextView)findViewById(R.id.title_text);
        title_text.setText("资源方");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_options:
                Intent optionIntent = new Intent(ResourceActivity.this, MyOptionsActivity.class);
                startActivity(optionIntent);
                break;
            case R.id.resource_on_line_car:
                Intent onLineIntent = new Intent(ResourceActivity.this, OnLineCarActivity.class);
                onLineIntent.putExtra("type", "2");
                startActivity(onLineIntent);
                break;
            case R.id.resource_my_contract:
                Intent contractIntent = new Intent(ResourceActivity.this, ContractActivity.class);
                int type = 2;
                contractIntent.putExtra("type", type + "");
                contractIntent.putExtra("id", CustomInfo.getInfo().getResource_id());
                startActivity(contractIntent);
                break;
            case R.id.resource_my_message:
                Intent msgIntent = new Intent(ResourceActivity.this, MyMessageActivity.class);
                startActivity(msgIntent);
                break;
            case R.id.resource_bank_card:
                Intent bankIntent = new Intent(ResourceActivity.this, BankCardActivity.class);
                bankIntent.putExtra("id", CustomInfo.getInfo().getResource_id());
                startActivity(bankIntent);
                break;
            /*case R.id.resource_out:
                preferences = getSharedPreferences("data", MODE_PRIVATE);
                editor = preferences.edit();
                editor.remove("resource_id");
                editor.commit();
                CustomInfo.getInfo().setResource_landing(false);
                Intent landingIntent = new Intent(ResourceActivity.this, ResourceLandingActivity.class);
                startActivity(landingIntent);
                finish();
                break;*/
        }
    }
}
