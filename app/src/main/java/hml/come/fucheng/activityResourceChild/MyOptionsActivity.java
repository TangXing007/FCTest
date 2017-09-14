package hml.come.fucheng.activityResourceChild;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import hml.come.fucheng.R;
import hml.come.fucheng.activity.BaseActivity;
import hml.come.fucheng.adapter.MyOptionsAdapter;
import hml.come.fucheng.custom.MainViewPage;
import hml.come.fucheng.fragment.HaveSalesFragment;
import hml.come.fucheng.fragment.NoSalesFragment;

/**
 * Created by TX on 2017/8/11.
 */

public class MyOptionsActivity extends BaseActivity implements View.OnClickListener{
    private MainViewPage viewPage;
    private Fragment[]fragments = {new HaveSalesFragment(), new NoSalesFragment()};
    private MyOptionsAdapter adapter;
    private TextView HaveSales, Nosales, title_text;
    @Override
    public void onCreate(Bundle savedInsatanceState){
        super.onCreate(savedInsatanceState);
        setContentView(R.layout.activity_my_options);
        back();
        immersion();
        viewPage = (MainViewPage)findViewById(R.id.car_viewPage);
        adapter = new MyOptionsAdapter(getSupportFragmentManager(), fragments);
        viewPage.setAdapter(adapter);
        HaveSales = (TextView)findViewById(R.id.have_sales);
        HaveSales.setOnClickListener(this);
        Nosales = (TextView)findViewById(R.id.no_sales);
        Nosales.setOnClickListener(this);
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("我的车源");
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.have_sales:
                HaveSales.setTextColor(getResources().getColor(R.color.whrite));
                HaveSales.setBackground(getResources().getDrawable(R.color.blue_h));
                Nosales.setBackground(getResources().getDrawable(R.drawable.car_border));
                Nosales.setTextColor(getResources().getColor(R.color.text));
                viewPage.setCurrentItem(0);
                break;
            case R.id.no_sales:
                Nosales.setTextColor(getResources().getColor(R.color.whrite));
                HaveSales.setTextColor(getResources().getColor(R.color.text));
                HaveSales.setBackground(getResources().getDrawable(R.drawable.car_border));
                Nosales.setBackground(getResources().getDrawable(R.color.blue_h));
                viewPage.setCurrentItem(1);
                break;
        }
    }
}
