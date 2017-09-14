package hml.come.fucheng.activityResourceChild;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import hml.come.fucheng.R;
import hml.come.fucheng.activity.BaseActivity;


/**
 * Created by Administrator on 2017/9/8.
 */

public class ContractImageShow extends BaseActivity {
    private LinearLayout include_title, inclede_back;
    private ImageView back_iamge, image;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contract_image_show_activity);
        back();
        immersion();
        inclede_back = (LinearLayout)findViewById(R.id.include_back);
        inclede_back.setBackgroundColor(getResources().getColor(R.color.huiXXX));
        include_title = (LinearLayout)findViewById(R.id.include_back_title);
        include_title.setBackgroundColor(getResources().getColor(R.color.huiXXX));
        back_iamge = (ImageView)findViewById(R.id.back_image);
        back_iamge.setImageResource(R.mipmap.w_back);
        image = (ImageView) findViewById(R.id.contract_show_image);
        Intent intent = getIntent();
        String Suri = intent.getStringExtra("uri");
        Picasso.with(this).load(Suri).into(image);
    }
}
