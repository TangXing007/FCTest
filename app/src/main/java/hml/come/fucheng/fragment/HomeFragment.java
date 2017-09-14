package hml.come.fucheng.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.activity.BiaoZhiActivity;
import hml.come.fucheng.activity.CooperationActivity;
import hml.come.fucheng.activity.FuChengActivity;
import hml.come.fucheng.activity.JinRongActivity;
import hml.come.fucheng.activity.LIstShowActivity;
import hml.come.fucheng.activity.MenDianActivity;
import hml.come.fucheng.activity.PriceActivity;
import hml.come.fucheng.adapter.LocalImageView;
import hml.come.fucheng.R;
import hml.come.fucheng.moudle.CarBiaoZhiData;
import hml.come.fucheng.moudle.CarShowImageData;
import hml.come.fucheng.net_work.FuChenHttpHandler;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/7/18.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{
    private ConvenientBanner banner_one_page, banner2_viewpage;
    private LinearLayout call_me, fu_cheng, men_dian, jin_rong, he_zuo, price1, price2,
            price3, price4, price5, price6, price7, price8,show_one, show_two, show_three,
    brand1, brand2, brand3, brand4;
    private ImageView brand1_image, brand2_image, brand3_image, brand4_image, show_image_one,
    show_image_two, showo_image_three;
    private TextView brand1_text, brand2_text, brand3_text, brand4_text, show_text_one, show_text_two,
    show_text_three;
    private CarBiaoZhiData biaoZhiData;
    private CarShowImageData showImageData;
    private List<CarShowImageData.ImageData> list = new ArrayList<>();
    private List<CarBiaoZhiData.Data> biaozhi_list = new ArrayList<>();
    private LinearLayout include_brand, include_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, viewGroup, false);
        brand1_image = (ImageView)view.findViewById(R.id.brand1_image);
        brand1_text = (TextView)view.findViewById(R.id.brand1_text);
        brand2_image = (ImageView)view.findViewById(R.id.brand2_image);
        brand2_text = (TextView)view.findViewById(R.id.brand2_text);
        brand3_image = (ImageView)view.findViewById(R.id.brand3_image);
        brand3_text = (TextView)view.findViewById(R.id.brand3_text);
        brand4_image = (ImageView)view.findViewById(R.id.brand4_iamge);
        brand4_text = (TextView)view.findViewById(R.id.brand4_text);
        show_image_one = (ImageView)view.findViewById(R.id.show_iamge_one);
        show_image_two = (ImageView)view.findViewById(R.id.show_iamge_two);
        showo_image_three = (ImageView)view.findViewById(R.id.show_iamge_three);
        show_text_one = (TextView)view.findViewById(R.id.show_text_one);
        show_text_two = (TextView)view.findViewById(R.id.show_text_two);
        show_text_three = (TextView)view.findViewById(R.id.show_text_three);
        banner_one_page = (ConvenientBanner) view.findViewById(R.id.banner1_viewpage);
        //banner2_viewpage = (ConvenientBanner) view.findViewById(R.id.banner2_viewpage);
        show_one = (LinearLayout)view.findViewById(R.id.show_one);
        show_two = (LinearLayout)view.findViewById(R.id.show_two);
        show_three = (LinearLayout)view.findViewById(R.id.show_three);
        show_one.setOnClickListener(this);
        show_two.setOnClickListener(this);
        show_three.setOnClickListener(this);
        brand1 = (LinearLayout)view.findViewById(R.id.brand1);
        brand2 = (LinearLayout)view.findViewById(R.id.brand2);
        brand3 = (LinearLayout)view.findViewById(R.id.brand3);
        brand4 = (LinearLayout)view.findViewById(R.id.brand4);
        brand1.setOnClickListener(this);
        brand2.setOnClickListener(this);
        brand3.setOnClickListener(this);
        brand4.setOnClickListener(this);
        call_me = (LinearLayout)view.findViewById(R.id.telephone);
        call_me.setOnClickListener(this);
        fu_cheng = (LinearLayout)view.findViewById(R.id.fucheng);
        fu_cheng.setOnClickListener(this);
        men_dian = (LinearLayout)view.findViewById(R.id.mendian);
        men_dian.setOnClickListener(this);
        jin_rong = (LinearLayout)view.findViewById(R.id.jinrong);
        jin_rong.setOnClickListener(this);
        he_zuo = (LinearLayout)view.findViewById(R.id.hezuo);
        he_zuo.setOnClickListener(this);
        price1 = (LinearLayout)view.findViewById(R.id.price1);
        price1.setOnClickListener(this);
        price2 = (LinearLayout)view.findViewById(R.id.price2);
        price2.setOnClickListener(this);
        price3 = (LinearLayout)view.findViewById(R.id.price3);
        price3.setOnClickListener(this);
        price4 = (LinearLayout)view.findViewById(R.id.price4);
        price4.setOnClickListener(this);
        price5 = (LinearLayout)view.findViewById(R.id.price5);
        price5.setOnClickListener(this);
        price6 = (LinearLayout)view.findViewById(R.id.price6);
        price6.setOnClickListener(this);
        price7 = (LinearLayout)view.findViewById(R.id.price7);
        price7.setOnClickListener(this);
        price8 = (LinearLayout)view.findViewById(R.id.price8);
        price8.setOnClickListener(this);
        include_brand = (LinearLayout)view.findViewById(R.id.include_brand);
        include_image = (LinearLayout)view.findViewById(R.id.include_image);
        init();
        onHttpClient();
        TwoHttp();
        return view;
    }

    private void init(){
        int[] images = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3, R.mipmap.banner4};
        List<Integer> image = new ArrayList<>();
        for (int i = 0; i < images.length; i++){
            image.add(images[i]);
        }
        banner_one_page.setPages(new CBViewHolderCreator<LocalImageView>() {
            @Override
            public LocalImageView createHolder() {
                return new LocalImageView();
            }
        }, image).setPageIndicator(new int[]{R.drawable.unnormal, R.drawable.normal})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPointViewVisible(true)
                .startTurning(2000).setManualPageable(true);

        /*int[] lunbo_images = {R.mipmap.lunbo1, R.mipmap.lunbo2};
        List<Integer> luobo_image = new ArrayList<>();
        for (int i = 0; i < lunbo_images.length; i++){
            luobo_image.add(lunbo_images[i]);
        }
        banner2_viewpage.setPages(new CBViewHolderCreator<LocalImageView>() {
            @Override
            public LocalImageView createHolder() {
                return new LocalImageView();
            }
        }, luobo_image).setPageIndicator(new int[]{R.drawable.unnormal, R.drawable.normal})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPointViewVisible(true)
                .startTurning(2000).setManualPageable(true);*/
    }

    public void onHttpClient(){
        biaoZhiData = new CarBiaoZhiData();
        RequestParams params = new RequestParams();
        params.put("pingpai", 1);
        HttpClient.get_istance().get(NetUrl.SHOU_YE_CAR_BIAO_ZHI, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if (statusCode == 200){
                    try {
                        biaoZhiData.code = response.getInt("code");
                        biaoZhiData.msg = response.getString("msg");
                        JSONArray jsonArray = response.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            CarBiaoZhiData.Data data = biaoZhiData.getData();
                            data.id = jsonObject.getInt("id");
                            data.thumbnail = NetUrl.TEST_LF_HEAD + jsonObject.getString("thumbnail");
                            data.name = jsonObject.getString("name");
                            biaozhi_list.add(data);
                        }
                        Picasso.with(getContext()).load(biaozhi_list.get(0).thumbnail).into(brand1_image);
                        brand1_text.setText(biaozhi_list.get(0).name);
                        Picasso.with(getContext()).load(biaozhi_list.get(1).thumbnail).into(brand2_image);
                        brand2_text.setText(biaozhi_list.get(1).name);
                        Picasso.with(getContext()).load(biaozhi_list.get(2).thumbnail).into(brand3_image);
                        brand3_text.setText(biaozhi_list.get(2).name);
                        Picasso.with(getContext()).load(biaozhi_list.get(3).thumbnail).into(brand4_image);
                        brand4_text.setText(biaozhi_list.get(3).name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (biaozhi_list != null && biaozhi_list.size() != 0){
                    include_brand.setVisibility(View.VISIBLE);
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONObject errorResponse) {
                System.out.println(errorResponse);
            }
        });
    }
    private void TwoHttp(){
        showImageData = new CarShowImageData();
        String b = NetUrl.SHOU_YE_CAR_IMAGE;
        HttpClient.get_istance().get(NetUrl.SHOU_YE_CAR_IMAGE, null, new FuChenHttpHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                super.onSuccess(statusCode, headers, response);
                Gson gson = new Gson();
                showImageData = gson.fromJson(responseStr, CarShowImageData.class);
                list = showImageData.data;
                if ( list == null && list.size() == 0){
                    include_image.setVisibility(View.GONE);
                }else {
                    include_image.setVisibility(View.VISIBLE);
                    String a = NetUrl.TEST_LF_HEAD + list.get(0).tated_thumbnail;
                    Picasso.with(getContext()).load(NetUrl.TEST_LF_HEAD + list.get(0).tated_thumbnail)
                            .into(show_image_one);
                    show_text_one.setText(list.get(0).brand);
                    Picasso.with(getContext()).load(NetUrl.TEST_LF_HEAD + list.get(1).tated_thumbnail)
                            .into(show_image_two);
                    show_text_two.setText(list.get(1).brand);
                    Picasso.with(getContext()).load(NetUrl.TEST_LF_HEAD + list.get(2).tated_thumbnail)
                            .into(showo_image_three);
                    show_text_three.setText(list.get(2).brand);
                }
                }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        onHttpClient();
        TwoHttp();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.telephone:
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4008082229"));
                startActivity(i);
                break;
            case R.id.fucheng:
                Intent fuchengIntent = new Intent(getActivity(), FuChengActivity.class);
                startActivity(fuchengIntent);
                break;
            case R.id.mendian:
                Intent mendianIntent = new Intent(getActivity(), MenDianActivity.class);
                startActivity(mendianIntent);
                break;
            case R.id.jinrong:
                Intent jinrongIntent = new Intent(getActivity(), JinRongActivity.class);
                startActivity(jinrongIntent);
                break;
            case R.id.hezuo:
                Intent hezuoIntent = new Intent(getActivity(), CooperationActivity.class);
                startActivity(hezuoIntent);
                break;
            case R.id.price1:
                Intent price1_Intent = new Intent(getActivity(), PriceActivity.class);
                int priceup1 = 0;
                int pricetop1 = 50000;
                price1_Intent.putExtra("priceup", priceup1 + "");
                price1_Intent.putExtra("pricetop", pricetop1 + "");
                price1_Intent.putExtra("title","5万以下");
                startActivity(price1_Intent);
                break;
            case R.id.price2:
                Intent price2_Intent = new Intent(getActivity(), PriceActivity.class);
                int priceup2 = 50000;
                int pricetop2 = 80000;
                price2_Intent.putExtra("priceup", priceup2 + "");
                price2_Intent.putExtra("pricetop", pricetop2 + "");
                price2_Intent.putExtra("title", "5-8万");
                startActivity(price2_Intent);
                break;
            case R.id.price3:
                Intent price3_Intent = new Intent(getActivity(), PriceActivity.class);
                int priceup3 = 80000;
                int pricetop3 = 120000;
                price3_Intent.putExtra("priceup", priceup3 + "");
                price3_Intent.putExtra("pricetop", pricetop3 + "");
                price3_Intent.putExtra("title", "8-12万");
                startActivity(price3_Intent);
                break;
            case R.id.price4:
                Intent price4_Intent = new Intent(getActivity(), PriceActivity.class);
                int priceup4 = 120000;
                int pricetop4 = 200000;
                price4_Intent.putExtra("priceup", priceup4 + "");
                price4_Intent.putExtra("pricetop", pricetop4 + "");
                price4_Intent.putExtra("title", "12-20万");
                startActivity(price4_Intent);
                break;
            case R.id.price5:
                Intent price5_Intent = new Intent(getActivity(), PriceActivity.class);
                int priceup5 = 200000;
                int pricetop5 = 300000;
                price5_Intent.putExtra("priceup", priceup5 + "");
                price5_Intent.putExtra("pricetop", pricetop5 + "");
                price5_Intent.putExtra("title", "20-30万");
                startActivity(price5_Intent);
                break;
            case R.id.price6:
                Intent price6_Intent = new Intent(getActivity(), PriceActivity.class);
                int priceup6 = 300000;
                int pricetop6 = 500000;
                price6_Intent.putExtra("priceup", priceup6 + "");
                price6_Intent.putExtra("pricetop", pricetop6 + "");
                price6_Intent.putExtra("title", "30-50万");
                startActivity(price6_Intent);
                break;
            case R.id.price7:
                Intent price7_Intent = new Intent(getActivity(), PriceActivity.class);
                int priceup7 = 500000;
                int pricetop7 = 1000000;
                price7_Intent.putExtra("priceup", priceup7 + "");
                price7_Intent.putExtra("pricetop", pricetop7 + "");
                price7_Intent.putExtra("title", "50-100万");
                startActivity(price7_Intent);
                break;
            case R.id.price8:
                Intent price8_Intent = new Intent(getActivity(), PriceActivity.class);
                int priceup8 = 1000000;
                int pricetop8 = 10000000;
                price8_Intent.putExtra("priceup", priceup8 + "");
                price8_Intent.putExtra("pricetop", pricetop8 + "");
                price8_Intent.putExtra("title", "100万以上");
                startActivity(price8_Intent);
                break;
            case R.id.show_one:
                Intent imageIntent1 = new Intent(getActivity(), LIstShowActivity.class);
                imageIntent1.putExtra("aid", list.get(0).aid + "");
                startActivity(imageIntent1);
                break;
            case R.id.show_two:
                Intent imageIntent2 = new Intent(getActivity(), LIstShowActivity.class);
                imageIntent2.putExtra("aid", list.get(1).aid + "");
                startActivity(imageIntent2);
                break;
            case R.id.show_three:
                Intent imageIntent3 = new Intent(getActivity(), LIstShowActivity.class);
                imageIntent3.putExtra("aid", list.get(2).aid + "");
                startActivity(imageIntent3);
                break;
            case R.id.brand1:
                Intent brandIntent1 = new Intent(getActivity(), BiaoZhiActivity.class);
                brandIntent1.putExtra("title", biaozhi_list.get(0).name);
                brandIntent1.putExtra("aid", biaozhi_list.get(0).id + "");
                int type = 1;
                brandIntent1.putExtra("type",type + "");
                startActivity(brandIntent1);
                break;
            case R.id.brand2:
                Intent brandIntent2 = new Intent(getActivity(), BiaoZhiActivity.class);
                brandIntent2.putExtra("title", biaozhi_list.get(1).name);
                brandIntent2.putExtra("aid", biaozhi_list.get(1).id + "");
                int type1 = 1;
                brandIntent2.putExtra("type",type1 + "");
                startActivity(brandIntent2);
                break;
            case R.id.brand3:
                Intent brandIntent3 = new Intent(getActivity(), BiaoZhiActivity.class);
                brandIntent3.putExtra("title", biaozhi_list.get(2).name);
                brandIntent3.putExtra("aid", biaozhi_list.get(2).id + "");
                int type2 = 1;
                brandIntent3.putExtra("type",type2 + "");
                startActivity(brandIntent3);
                break;
            case R.id.brand4:
                Intent brandIntent4 = new Intent(getActivity(), BiaoZhiActivity.class);
                brandIntent4.putExtra("title", biaozhi_list.get(3).name);
                brandIntent4.putExtra("aid", biaozhi_list.get(3).id + "");
                int type3 = 1;
                brandIntent4.putExtra("type",type3 + "");
                startActivity(brandIntent4);
                break;
        }
    }
}
