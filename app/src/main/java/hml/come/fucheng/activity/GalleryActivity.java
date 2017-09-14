package hml.come.fucheng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.adapter.GalleryAdapter;
import hml.come.fucheng.moudle.GalleryData;
import hml.come.fucheng.moudle.LocalGalleryData;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/8/3.
 */

public class GalleryActivity extends BaseActivity implements View.OnClickListener{
    private GalleryData galleryData;
    private ViewPager viewPager;
    private LocalGalleryData localGalleryData;
    private TextView appearance, interior, space, illustration, official, film, title_text;
    private View appearance_view, interior_view, space_view, illustration_view, official_view, film_view;
    private LinearLayout one, two, three, four, five, six;
    @Override
    public void onCreate(Bundle savedInstancaState){
        super.onCreate(savedInstancaState);
        setContentView(R.layout.activity_gallery);
        immersion();
        back();
        title_text = (TextView)findViewById(R.id.title_text);
        title_text.setText("图片");
        appearance = (TextView)findViewById(R.id.appearance);
        appearance_view = (View)findViewById(R.id.appearance_view);
        interior = (TextView)findViewById(R.id.interior);
        interior_view = (View)findViewById(R.id.interior_view);
        space = (TextView)findViewById(R.id.space);
        space_view = (View)findViewById(R.id.space_view);
        illustration = (TextView)findViewById(R.id.illustration);
        illustration_view = (View)findViewById(R.id.illustration_view);
        official = (TextView)findViewById(R.id.official);
        official_view = (View)findViewById(R.id.official_view);
        film = (TextView)findViewById(R.id.film);
        film_view = (View)findViewById(R.id.film_view);
        viewPager = (ViewPager)findViewById(R.id.gallery_viewPage);
        one = (LinearLayout)findViewById(R.id.gallery_one);
        one.setOnClickListener(this);
        two = (LinearLayout)findViewById(R.id.gallery_two);
        two.setOnClickListener(this);
        three = (LinearLayout)findViewById(R.id.gallery_three);
        three.setOnClickListener(this);
        four = (LinearLayout)findViewById(R.id.gallery_four);
        four.setOnClickListener(this);
        five = (LinearLayout)findViewById(R.id.gallery_five);
        five.setOnClickListener(this);
        six = (LinearLayout)findViewById(R.id.gallery_six);
        six.setOnClickListener(this);
        http();
    }

    private void http(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("car_id");
        int car_id = Integer.parseInt(id);
        RequestParams params = new RequestParams();
        params.put("car_id", car_id);
        HttpClient.get_istance().post(NetUrl.GALLERY, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Gson gson = new Gson();
                galleryData = gson.fromJson(response.toString(), GalleryData.class);
                ArrayList<LocalGalleryData> datas = new ArrayList<>();
                localGalleryData = new LocalGalleryData();
                localGalleryData.arrayList.add(galleryData.data.waiguan1);
                localGalleryData.arrayList.add(galleryData.data.waiguan2);
                localGalleryData.arrayList.add(galleryData.data.waiguan3);
                localGalleryData.arrayList.add(galleryData.data.waiguan4);
                datas.add(localGalleryData);
                localGalleryData = new LocalGalleryData();
                localGalleryData.arrayList.add(galleryData.data.neishi1);
                localGalleryData.arrayList.add(galleryData.data.neishi2);
                localGalleryData.arrayList.add(galleryData.data.neishi3);
                localGalleryData.arrayList.add(galleryData.data.neishi4);
                datas.add(localGalleryData);
                localGalleryData = new LocalGalleryData();
                localGalleryData.arrayList.add(galleryData.data.kongjian1);
                localGalleryData.arrayList.add(galleryData.data.kongjian2);
                localGalleryData.arrayList.add(galleryData.data.kongjian3);
                localGalleryData.arrayList.add(galleryData.data.kongjian4);
                datas.add(localGalleryData);
                localGalleryData = new LocalGalleryData();
                localGalleryData.arrayList.add(galleryData.data.tujie1);
                localGalleryData.arrayList.add(galleryData.data.tujie2);
                localGalleryData.arrayList.add(galleryData.data.tujie3);
                localGalleryData.arrayList.add(galleryData.data.tujie4);
                datas.add(localGalleryData);
                localGalleryData = new LocalGalleryData();
                localGalleryData.arrayList.add(galleryData.data.guanfang1);
                localGalleryData.arrayList.add(galleryData.data.guanfang2);
                localGalleryData.arrayList.add(galleryData.data.guanfang3);
                localGalleryData.arrayList.add(galleryData.data.guanfang4);
                datas.add(localGalleryData);
                localGalleryData = new LocalGalleryData();
                localGalleryData.arrayList.add(galleryData.data.shipai1);
                localGalleryData.arrayList.add(galleryData.data.shipai2);
                localGalleryData.arrayList.add(galleryData.data.shipai3);
                localGalleryData.arrayList.add(galleryData.data.shipai4);
                datas.add(localGalleryData);
                viewPager.setAdapter(new GalleryAdapter(getSupportFragmentManager(), datas));
                page();
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {

            }
        });
    }

    private void page(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        appearance.setTextColor(getResources().getColor(R.color.blue_s));
                        appearance_view.setVisibility(View.VISIBLE);
                        interior.setTextColor(getResources().getColor(R.color.text));
                        interior_view.setVisibility(View.INVISIBLE);
                        space.setTextColor(getResources().getColor(R.color.text));
                        space_view.setVisibility(View.INVISIBLE);
                        illustration.setTextColor(getResources().getColor(R.color.text));
                        illustration_view.setVisibility(View.INVISIBLE);
                        official.setTextColor(getResources().getColor(R.color.text));
                        official_view.setVisibility(View.INVISIBLE);
                        film.setTextColor(getResources().getColor(R.color.text));
                        film_view.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        appearance.setTextColor(getResources().getColor(R.color.text));
                        appearance_view.setVisibility(View.INVISIBLE);
                        interior.setTextColor(getResources().getColor(R.color.blue_h));
                        interior_view.setVisibility(View.VISIBLE);
                        space.setTextColor(getResources().getColor(R.color.text));
                        space_view.setVisibility(View.INVISIBLE);
                        illustration.setTextColor(getResources().getColor(R.color.text));
                        illustration_view.setVisibility(View.INVISIBLE);
                        official.setTextColor(getResources().getColor(R.color.text));
                        official_view.setVisibility(View.INVISIBLE);
                        film.setTextColor(getResources().getColor(R.color.text));
                        film_view.setVisibility(View.INVISIBLE);
                        break;
                    case 2:
                        appearance.setTextColor(getResources().getColor(R.color.text));
                        appearance_view.setVisibility(View.INVISIBLE);
                        interior.setTextColor(getResources().getColor(R.color.text));
                        interior_view.setVisibility(View.INVISIBLE);
                        space.setTextColor(getResources().getColor(R.color.blue_h));
                        space_view.setVisibility(View.VISIBLE);
                        illustration.setTextColor(getResources().getColor(R.color.text));
                        illustration_view.setVisibility(View.INVISIBLE);
                        official.setTextColor(getResources().getColor(R.color.text));
                        official_view.setVisibility(View.INVISIBLE);
                        film.setTextColor(getResources().getColor(R.color.text));
                        film_view.setVisibility(View.INVISIBLE);
                        break;
                    case 3:
                        appearance.setTextColor(getResources().getColor(R.color.text));
                        appearance_view.setVisibility(View.INVISIBLE);
                        interior.setTextColor(getResources().getColor(R.color.text));
                        interior_view.setVisibility(View.INVISIBLE);
                        space.setTextColor(getResources().getColor(R.color.text));
                        space_view.setVisibility(View.INVISIBLE);
                        illustration.setTextColor(getResources().getColor(R.color.blue_h));
                        illustration_view.setVisibility(View.VISIBLE);
                        official.setTextColor(getResources().getColor(R.color.text));
                        official_view.setVisibility(View.INVISIBLE);
                        film.setTextColor(getResources().getColor(R.color.text));
                        film_view.setVisibility(View.INVISIBLE);
                        break;
                    case 4:
                        appearance.setTextColor(getResources().getColor(R.color.text));
                        appearance_view.setVisibility(View.INVISIBLE);
                        interior.setTextColor(getResources().getColor(R.color.text));
                        interior_view.setVisibility(View.INVISIBLE);
                        space.setTextColor(getResources().getColor(R.color.text));
                        space_view.setVisibility(View.INVISIBLE);
                        illustration.setTextColor(getResources().getColor(R.color.text));
                        illustration_view.setVisibility(View.INVISIBLE);
                        official.setTextColor(getResources().getColor(R.color.blue_h));
                        official_view.setVisibility(View.VISIBLE);
                        film.setTextColor(getResources().getColor(R.color.text));
                        film_view.setVisibility(View.INVISIBLE);
                        break;
                    case 5:
                        appearance.setTextColor(getResources().getColor(R.color.text));
                        appearance_view.setVisibility(View.INVISIBLE);
                        interior.setTextColor(getResources().getColor(R.color.text));
                        interior_view.setVisibility(View.INVISIBLE);
                        space.setTextColor(getResources().getColor(R.color.text));
                        space_view.setVisibility(View.INVISIBLE);
                        illustration.setTextColor(getResources().getColor(R.color.text));
                        illustration_view.setVisibility(View.INVISIBLE);
                        official.setTextColor(getResources().getColor(R.color.text));
                        official_view.setVisibility(View.INVISIBLE);
                        film.setTextColor(getResources().getColor(R.color.blue_h));
                        film_view.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gallery_one:
                viewPager.setCurrentItem(0);
                break;
            case R.id.gallery_two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.gallery_three:
                viewPager.setCurrentItem(2);
                break;
            case R.id.gallery_four:
                viewPager.setCurrentItem(3);
                break;
            case R.id.gallery_five:
                viewPager.setCurrentItem(4);
                break;
            case R.id.gallery_six:
                viewPager.setCurrentItem(5);
                break;
        }
    }
}
