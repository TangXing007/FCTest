package hml.come.fucheng.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import hml.come.fucheng.R;

/**
 * Created by TX on 2017/7/19.
 */

public class MenDianActivity extends BaseActivity implements View.OnClickListener{
    private TextView title_text;
    private LinearLayout back_button;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mendian);
        immersion();
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("门店信息");
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_button:
                finish();
        }
    }
}
