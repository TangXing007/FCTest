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

public class FuChengActivity extends BaseActivity {
    private TextView title_text;
    private LinearLayout inclede_back, include_title;
    private ImageView back_image;
    private View include_view;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fucheng);
        immersion();
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("公司简介");
        title_text.setTextColor(getResources().getColor(R.color.whrite));
        inclede_back = (LinearLayout)findViewById(R.id.include_back) ;
        inclede_back.setBackgroundColor(getResources().getColor(R.color.huiXXX));
        include_title = (LinearLayout)findViewById(R.id.include_back_title);
        include_title.setBackgroundColor(getResources().getColor(R.color.huiXXX));
        back_image = (ImageView)findViewById(R.id.back_image);
        back_image.setImageResource(R.mipmap.w_back);
        include_view = (View)findViewById(R.id.include_view);
        include_view.setVisibility(View.GONE);
        back();
    }
}
