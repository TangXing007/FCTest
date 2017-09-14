package hml.come.fucheng.activity;

import android.content.Intent;
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

public class CooperationActivity extends BaseActivity {
    private LinearLayout back_button;
    private TextView textView, apply_for_join;
    @Override
    public void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_cooperation);
        immersion();
        back_button = (LinearLayout)findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textView = (TextView)findViewById(R.id.title_text);
        textView.setText("合作加盟");
        apply_for_join = (TextView)findViewById(R.id.apply_for_join);
        apply_for_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CooperationActivity.this, ApplyForJoin.class);
                startActivity(i);
            }
        });
    }
}
