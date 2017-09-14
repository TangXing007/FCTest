package hml.come.fucheng.activity;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hml.come.fucheng.R;
import hml.come.fucheng.adapter.HomeViewPageAdapter;
import hml.come.fucheng.fragment.BuyFragment;
import hml.come.fucheng.fragment.HomeFragment;
import hml.come.fucheng.fragment.LandFragment;
import hml.come.fucheng.fragment.MyselfFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private long exitTime = 0;
    private Fragment[] fragments = new Fragment[]{new HomeFragment(),new LandFragment(),
            new BuyFragment(), new MyselfFragment()};
    private HomeViewPageAdapter adapter;
    private ViewPager home_viewpage;
    private LinearLayout home, land, buy, myself;
    private ImageView home_image, buy_image, myself_image, land_image;
    private TextView home_text, buy_text, myself_text, land_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        immersion();
        init();
        home_image.setImageResource(R.mipmap.home_click);
        home_text.setTextColor(getResources().getColor(R.color.blue_h));
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis() - exitTime > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void init(){
        adapter = new HomeViewPageAdapter(getSupportFragmentManager(), fragments);
        home_viewpage = (ViewPager)findViewById(R.id.home_viewPage);
        home_viewpage.setAdapter(adapter);
        home_viewpage.setOffscreenPageLimit(3);
        home = (LinearLayout)findViewById(R.id.shouye);
        home_image = (ImageView)findViewById(R.id.home_image);
        home_text = (TextView)findViewById(R.id.home_text);
        home.setOnClickListener(this);
        land = (LinearLayout)findViewById(R.id.land);
        land_image = (ImageView)findViewById(R.id.land_image);
        land_text = (TextView)findViewById(R.id.land_text);
        land.setOnClickListener(this);
        buy = (LinearLayout)findViewById(R.id.buy);
        buy_image = (ImageView)findViewById(R.id.buy_image);
        buy_text = (TextView)findViewById(R.id.buy_text);
        buy.setOnClickListener(this);
        myself = (LinearLayout)findViewById(R.id.myself);
        myself_image = (ImageView)findViewById(R.id.myself_image);
        myself_text = (TextView)findViewById(R.id.myself_text);
        myself.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shouye:
                home_image.setImageResource(R.mipmap.home_click);
                home_text.setTextColor(getResources().getColor(R.color.blue_h));
                land_image.setImageResource(R.mipmap.land);
                land_text.setTextColor(getResources().getColor(R.color.text));
                buy_image.setImageResource(R.mipmap.buy);
                buy_text.setTextColor(getResources().getColor(R.color.text));
                myself_image.setImageResource(R.mipmap.myself);
                myself_text.setTextColor(getResources().getColor(R.color.text));
                home_viewpage.setCurrentItem(0);
                break;
            case R.id.land:
                home_image.setImageResource(R.mipmap.home);
                home_text.setTextColor(getResources().getColor(R.color.text));
                land_image.setImageResource(R.mipmap.land_click);
                land_text.setTextColor(getResources().getColor(R.color.blue_h));
                buy_image.setImageResource(R.mipmap.buy);
                buy_text.setTextColor(getResources().getColor(R.color.text));
                myself_image.setImageResource(R.mipmap.myself);
                myself_text.setTextColor(getResources().getColor(R.color.text));
                home_viewpage.setCurrentItem(1);
                break;
            case R.id.buy:
                home_image.setImageResource(R.mipmap.home);
                home_text.setTextColor(getResources().getColor(R.color.text));
                land_image.setImageResource(R.mipmap.land);
                land_text.setTextColor(getResources().getColor(R.color.text));
                buy_image.setImageResource(R.mipmap.buy_click);
                buy_text.setTextColor(getResources().getColor(R.color.blue_h));
                myself_image.setImageResource(R.mipmap.myself);
                myself_text.setTextColor(getResources().getColor(R.color.text));
                home_viewpage.setCurrentItem(2);
                break;
            case R.id.myself:
                home_image.setImageResource(R.mipmap.home);
                home_text.setTextColor(getResources().getColor(R.color.text));
                land_image.setImageResource(R.mipmap.land);
                land_text.setTextColor(getResources().getColor(R.color.text));
                buy_image.setImageResource(R.mipmap.buy);
                buy_text.setTextColor(getResources().getColor(R.color.text));
                myself_image.setImageResource(R.mipmap.myself_click);
                myself_text.setTextColor(getResources().getColor(R.color.blue_h));
                home_viewpage.setCurrentItem(3);
                break;
        }
    }
}
