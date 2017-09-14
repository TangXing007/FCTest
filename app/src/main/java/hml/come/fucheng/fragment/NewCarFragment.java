package hml.come.fucheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.activity.BiaoZhiActivity;
import hml.come.fucheng.activity.LIstShowActivity;
import hml.come.fucheng.activity.PriceActivity;
import hml.come.fucheng.adapter.ParentListAdapter;
import hml.come.fucheng.adapter.SearchAdapter;
import hml.come.fucheng.moudle.BuyChooseCarData;
import hml.come.fucheng.moudle.CarBiaoZhiData;
import hml.come.fucheng.moudle.CarShowImageData;
import hml.come.fucheng.net_work.FuChenHttpHandler;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/7/18.
 */

public class NewCarFragment extends Fragment implements View.OnClickListener{
    private LinearLayout  price1, price2, price3, price4, price5, price6, price7, price8,show_one,
            show_two, show_three, brand1, brand2, brand3, brand4, include_image ;
    private ImageView brand1_image, brand2_image, brand3_image, brand4_image, show_image_one,
            show_image_two, showo_image_three;
    private TextView brand1_text, brand2_text, brand3_text, brand4_text, show_text_one, show_text_two,
            show_text_three;
    private View headView;
    private ListView parent_list_view, child_list_view;
    private BuyChooseCarData chooseCarData;
    private ParentListAdapter parentAdapter;
    private ArrayList<BuyChooseCarData.Content> chose_list = new ArrayList<>();
    private CarBiaoZhiData biaoZhiData;
    private CarShowImageData showImageData;
    private List<CarBiaoZhiData.Data> biaozhi_list = new ArrayList<>();
    private List<CarShowImageData.ImageData> list = new ArrayList<>();
    String[] searchList = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private ListView searchView;
    private SearchAdapter searchAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInsatanceState){
        View view = inflater.inflate(R.layout.fragment_new_car, viewGroup, false);
        parent_list_view = (ListView)view.findViewById(R.id.new_car_parent_listview);
        headView = inflater.inflate(R.layout.head_view, null);
        brand1_image = (ImageView)headView.findViewById(R.id.brand1_image);
        brand1_text = (TextView)headView.findViewById(R.id.brand1_text);
        brand2_image = (ImageView)headView.findViewById(R.id.brand2_image);
        brand2_text = (TextView)headView.findViewById(R.id.brand2_text);
        brand3_image = (ImageView)headView.findViewById(R.id.brand3_image);
        brand3_text = (TextView)headView.findViewById(R.id.brand3_text);
        brand4_image = (ImageView)headView.findViewById(R.id.brand4_iamge);
        brand4_text = (TextView)headView.findViewById(R.id.brand4_text);
        show_image_one = (ImageView)headView.findViewById(R.id.show_iamge_one);
        show_image_two = (ImageView)headView.findViewById(R.id.show_iamge_two);
        showo_image_three = (ImageView)headView.findViewById(R.id.show_iamge_three);
        show_text_one = (TextView)headView.findViewById(R.id.show_text_one);
        show_text_two = (TextView)headView.findViewById(R.id.show_text_two);
        show_text_three = (TextView)headView.findViewById(R.id.show_text_three);
        show_one = (LinearLayout)headView.findViewById(R.id.show_one);
        show_two = (LinearLayout)headView.findViewById(R.id.show_two);
        show_three = (LinearLayout)headView.findViewById(R.id.show_three);
        include_image = (LinearLayout)headView.findViewById(R.id.include_iamge2);
        show_one.setOnClickListener(this);
        show_two.setOnClickListener(this);
        show_three.setOnClickListener(this);
        brand1 = (LinearLayout)headView.findViewById(R.id.brand1);
        brand2 = (LinearLayout)headView.findViewById(R.id.brand2);
        brand3 = (LinearLayout)headView.findViewById(R.id.brand3);
        brand4 = (LinearLayout)headView.findViewById(R.id.brand4);
        brand1.setOnClickListener(this);
        brand2.setOnClickListener(this);
        brand3.setOnClickListener(this);
        brand4.setOnClickListener(this);
        price1 = (LinearLayout)headView.findViewById(R.id.price1);
        price1.setOnClickListener(this);
        price2 = (LinearLayout)headView.findViewById(R.id.price2);
        price2.setOnClickListener(this);
        price3 = (LinearLayout)headView.findViewById(R.id.price3);
        price3.setOnClickListener(this);
        price4 = (LinearLayout)headView.findViewById(R.id.price4);
        price4.setOnClickListener(this);
        price5 = (LinearLayout)headView.findViewById(R.id.price5);
        price5.setOnClickListener(this);
        price6 = (LinearLayout)headView.findViewById(R.id.price6);
        price6.setOnClickListener(this);
        price7 = (LinearLayout)headView.findViewById(R.id.price7);
        price7.setOnClickListener(this);
        price8 = (LinearLayout)headView.findViewById(R.id.price8);
        price8.setOnClickListener(this);
        searchView = (ListView)view.findViewById(R.id.search_list);
        onSearch();
        onHttp();
        onHttpClient();
        TwoHttp();
        return view;
    }

    public void onHttpClient(){
        RequestParams params = new RequestParams();
        params.put("pingpai",1);
        HttpClient.get_istance().get(NetUrl.SHOU_YE_CAR_BIAO_ZHI, params, new FuChenHttpHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                super.onSuccess(statusCode, headers, responseBody);
                if (responseStr != null && !responseBody.equals("")){
                    Gson biaozhiGosn = new Gson();
                    biaoZhiData = biaozhiGosn.fromJson(responseStr, CarBiaoZhiData.class);
                }
                if (biaoZhiData.data != null && biaoZhiData.data.size() != 0){
                    Picasso.with(getContext()).load(NetUrl.TEST_LF_HEAD + biaoZhiData.data.get(0).thumbnail)
                            .into(brand1_image);
                    brand1_text.setText(biaoZhiData.data.get(0).name);
                    Picasso.with(getContext()).load(NetUrl.TEST_LF_HEAD + biaoZhiData.data.get(1).thumbnail)
                            .into(brand2_image);
                    brand2_text.setText(biaoZhiData.data.get(1).name);
                    Picasso.with(getContext()).load(NetUrl.TEST_LF_HEAD + biaoZhiData.data.get(2).thumbnail)
                            .into(brand3_image);
                    brand3_text.setText(biaoZhiData.data.get(2).name);
                    Picasso.with(getContext()).load(NetUrl.TEST_LF_HEAD + biaoZhiData.data.get(3).thumbnail)
                            .into(brand4_image);
                    brand4_text.setText(biaoZhiData.data.get(3).name);
                }
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

    private void onHttp(){
        /*RequestParams params = new RequestParams();
        params.put("car_pinpai", "pinpai");*/
        String a = NetUrl.BUY_CHOOSE_CAR;
        HttpClient.get_istance().get(NetUrl.BUY_CHOOSE_CAR, null, new FuChenHttpHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                super.onSuccess(statusCode, headers, responseBody);
                if(responseStr != null && !responseBody.equals("")){
                    Gson pinpaiGson = new Gson();
                    chooseCarData = pinpaiGson.fromJson(responseStr, BuyChooseCarData.class);
                }
                chose_list.clear();
                if(chooseCarData.data.A != null && chooseCarData.data.A.size() != 0){
                    chooseCarData.data.A.add(0, new BuyChooseCarData().getContent("A"));
                    chose_list.addAll(chooseCarData.data.A);
                }
                if(chooseCarData.data.B != null && chooseCarData.data.B.size() != 0){
                    chooseCarData.data.B.add(0, new BuyChooseCarData().getContent("B"));
                    chose_list.addAll(chooseCarData.data.B);
                }
                if(chooseCarData.data.C != null && chooseCarData.data.C.size() != 0){
                    chooseCarData.data.C.add(0, new BuyChooseCarData().getContent("C"));
                    chose_list.addAll(chooseCarData.data.C);
                }
                if(chooseCarData.data.D != null && chooseCarData.data.D.size() != 0){
                    chooseCarData.data.D.add(0, new BuyChooseCarData().getContent("D"));
                    chose_list.addAll(chooseCarData.data.D);
                }
                if(chooseCarData.data.E != null && chooseCarData.data.E.size() != 0){
                    chooseCarData.data.E.add(0, new BuyChooseCarData().getContent("E"));
                    chose_list.addAll(chooseCarData.data.E);
                }
                if(chooseCarData.data.F != null && chooseCarData.data.F.size() != 0){
                    chooseCarData.data.F.add(0, new BuyChooseCarData().getContent("F"));
                    chose_list.addAll(chooseCarData.data.F);
                }
                if(chooseCarData.data.G != null && chooseCarData.data.G.size() != 0){
                    chooseCarData.data.G.add(0, new BuyChooseCarData().getContent("G"));
                    chose_list.addAll(chooseCarData.data.G);
                }
                if(chooseCarData.data.H != null && chooseCarData.data.H.size() != 0){
                    chooseCarData.data.H.add(0, new BuyChooseCarData().getContent("H"));
                    chose_list.addAll(chooseCarData.data.H);
                }
                if(chooseCarData.data.I != null && chooseCarData.data.I.size() != 0){
                    chooseCarData.data.I.add(0, new BuyChooseCarData().getContent("I"));
                    chose_list.addAll(chooseCarData.data.I);
                }
                if(chooseCarData.data.J != null && chooseCarData.data.J.size() != 0){
                    chooseCarData.data.J.add(0, new BuyChooseCarData().getContent("J"));
                    chose_list.addAll(chooseCarData.data.J);
                }
                if(chooseCarData.data.K != null && chooseCarData.data.K.size() != 0){
                    chooseCarData.data.K.add(0, new BuyChooseCarData().getContent("K"));
                    chose_list.addAll(chooseCarData.data.K);
                }
                if(chooseCarData.data.L != null && chooseCarData.data.L.size() != 0){
                    chooseCarData.data.L.add(0, new BuyChooseCarData().getContent("L"));
                    chose_list.addAll(chooseCarData.data.L);
                }
                if(chooseCarData.data.M != null && chooseCarData.data.M.size() != 0){
                    chooseCarData.data.M.add(0, new BuyChooseCarData().getContent("M"));
                    chose_list.addAll(chooseCarData.data.M);
                }
                if(chooseCarData.data.N != null && chooseCarData.data.N.size() != 0){
                    chooseCarData.data.N.add(0, new BuyChooseCarData().getContent("N"));
                    chose_list.addAll(chooseCarData.data.N);
                }
                if(chooseCarData.data.O != null && chooseCarData.data.O.size() != 0){
                    chooseCarData.data.O.add(0, new BuyChooseCarData().getContent("O"));
                    chose_list.addAll(chooseCarData.data.O);
                }if(chooseCarData.data.P != null && chooseCarData.data.P.size() != 0){
                    chooseCarData.data.P.add(0, new BuyChooseCarData().getContent("P"));
                    chose_list.addAll(chooseCarData.data.P);
                }
                if(chooseCarData.data.Q != null && chooseCarData.data.Q.size() != 0){
                    chooseCarData.data.Q.add(0, new BuyChooseCarData().getContent("Q"));
                    chose_list.addAll(chooseCarData.data.Q);
                }
                if(chooseCarData.data.R != null && chooseCarData.data.R.size() != 0){
                    chooseCarData.data.R.add(0, new BuyChooseCarData().getContent("R"));
                    chose_list.addAll(chooseCarData.data.R);
                }
                if(chooseCarData.data.S != null && chooseCarData.data.S.size() != 0){
                    chooseCarData.data.S.add(0, new BuyChooseCarData().getContent("S"));
                    chose_list.addAll(chooseCarData.data.S);
                }
                if(chooseCarData.data.T != null && chooseCarData.data.T.size() != 0){
                    chooseCarData.data.T.add(0, new BuyChooseCarData().getContent("T"));
                    chose_list.addAll(chooseCarData.data.T);
                }
                if(chooseCarData.data.U != null && chooseCarData.data.U.size() != 0){
                    chooseCarData.data.U.add(0, new BuyChooseCarData().getContent("U"));
                    chose_list.addAll(chooseCarData.data.U);
                }
                if(chooseCarData.data.V != null && chooseCarData.data.V.size() != 0){
                    chooseCarData.data.V.add(0, new BuyChooseCarData().getContent("V"));
                    chose_list.addAll(chooseCarData.data.V);
                }
                if(chooseCarData.data.W != null && chooseCarData.data.W.size() != 0){
                    chooseCarData.data.W.add(0, new BuyChooseCarData().getContent("W"));
                    chose_list.addAll(chooseCarData.data.W);
                }
                if(chooseCarData.data.X != null && chooseCarData.data.X.size() != 0){
                    chooseCarData.data.X.add(0, new BuyChooseCarData().getContent("X"));
                    chose_list.addAll(chooseCarData.data.X);
                }
                if(chooseCarData.data.Y != null && chooseCarData.data.Y.size() != 0){
                    chooseCarData.data.Y.add(0, new BuyChooseCarData().getContent("Y"));
                    chose_list.addAll(chooseCarData.data.Y);
                }
                if(chooseCarData.data.Z != null && chooseCarData.data.Z.size() != 0){
                    chooseCarData.data.Z.add(0, new BuyChooseCarData().getContent("Z"));
                    chose_list.addAll(chooseCarData.data.Z);
                }
                parentAdapter = new ParentListAdapter(getContext(), chose_list, 0);
                parent_list_view.addHeaderView(headView);
                parent_list_view.setAdapter(parentAdapter);
                parent_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), BiaoZhiActivity.class);
                        intent.putExtra("title",chose_list.get(position - 1).name);
                        intent.putExtra("aid", chose_list.get(position - 1).id + "");
                        int type = 1;
                        intent.putExtra("type",type + "");
                        startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        int type = 1;
        switch (v.getId()){
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
                imageIntent1.putExtra("aid", showImageData.data.get(0).aid + "");
                startActivity(imageIntent1);
                break;
            case R.id.show_two:
                Intent imageIntent2 = new Intent(getActivity(), LIstShowActivity.class);
                imageIntent2.putExtra("aid", showImageData.data.get(1).aid + "");
                startActivity(imageIntent2);
                break;
            case R.id.show_three:
                Intent imageIntent3 = new Intent(getActivity(), LIstShowActivity.class);
                imageIntent3.putExtra("aid", showImageData.data.get(2).aid + "");
                startActivity(imageIntent3);
                break;
            case R.id.brand1:
                Intent brandIntent1 = new Intent(getActivity(), BiaoZhiActivity.class);
                brandIntent1.putExtra("title", biaoZhiData.data.get(0).name);
                brandIntent1.putExtra("aid", biaoZhiData.data.get(0).id + "");
                brandIntent1.putExtra("type",type + "");
                startActivity(brandIntent1);
                break;
            case R.id.brand2:
                Intent brandIntent2 = new Intent(getActivity(), BiaoZhiActivity.class);
                brandIntent2.putExtra("title", biaoZhiData.data.get(1).name);
                brandIntent2.putExtra("aid", biaoZhiData.data.get(1).id + "");
                brandIntent2.putExtra("type",type + "");
                startActivity(brandIntent2);
                break;
            case R.id.brand3:
                Intent brandIntent3 = new Intent(getActivity(), BiaoZhiActivity.class);
                brandIntent3.putExtra("title", biaoZhiData.data.get(2).name);
                brandIntent3.putExtra("aid", biaoZhiData.data.get(2).id + "");
                brandIntent3.putExtra("type",type + "");
                startActivity(brandIntent3);
                break;
            case R.id.brand4:
                Intent brandIntent4 = new Intent(getActivity(), BiaoZhiActivity.class);
                brandIntent4.putExtra("title", biaoZhiData.data.get(3).name);
                brandIntent4.putExtra("aid", biaoZhiData.data.get(3).id + "");
                brandIntent4.putExtra("type",type + "");
                startActivity(brandIntent4);
                break;
        }
    }

    private void onSearch(){
        searchAdapter = new SearchAdapter(getContext(), searchList);
        searchView.setAdapter(searchAdapter);
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Stext = searchList[position];
                for (int i = 0; i < chose_list.size(); i++){
                    if (chose_list.get(i).flag){
                        if (Stext.equals(chose_list.get(i).name)){
                            parent_list_view.smoothScrollToPosition(i + 1);
                        }
                    }
                }
            }
        });
    }
}
