package hml.come.fucheng.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.LinkedList;

import cz.msebera.android.httpclient.Header;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import hml.come.fucheng.R;
import hml.come.fucheng.activity.CircleImageView;
import hml.come.fucheng.activity.LandingActivity;
import hml.come.fucheng.activityDealerChild.DealerActivity;
import hml.come.fucheng.activityDealerChild.DealerLandingActivity;
import hml.come.fucheng.activityMyselfChild.PersonDataActivity;
import hml.come.fucheng.activityMyselfChild.UserFeedbackActivity;
import hml.come.fucheng.activityResourceChild.ResourceActivity;
import hml.come.fucheng.activityResourceChild.ResourceLandingActivity;
import hml.come.fucheng.eventbus.FirstEventBus;
import hml.come.fucheng.activityMyselfChild.SettingActivity;
import hml.come.fucheng.moudle.PersonData;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/7/18.
 */

public class MyselfFragment extends Fragment implements View.OnClickListener{
    private LinearLayout person_center, call_for_me, clear_cache, user_feedback, dealer, resource;
    private TextView user_phone;
    private String dealer_id, resource_id;
    private CircleImageView user_avatar;
    private File file;
    private FileInputStream fis;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Handler handler = new Handler();
    private View pop;
    private PopupWindow popupWindow;
    private ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_myself, viewGroup, false);
        EventBus.getDefault().register(this);
        person_center = (LinearLayout)view.findViewById(R.id.personal_center);
        person_center.setOnClickListener(this);
        user_feedback = (LinearLayout)view.findViewById(R.id.user_feedback);
        user_feedback.setOnClickListener(this);
        user_phone = (TextView)view.findViewById(R.id.user_phone);
        user_phone.setText(CustomInfo.getInfo().getNumber());
        call_for_me = (LinearLayout)view.findViewById(R.id.call_for_me);
        call_for_me.setOnClickListener(this);
        clear_cache = (LinearLayout)view.findViewById(R.id.clear_cache);
        clear_cache.setOnClickListener(this);
        user_avatar = (CircleImageView)view.findViewById(R.id.user_avatar);
        /*dealer = (LinearLayout)view.findViewById(R.id.dealer);
        dealer.setOnClickListener(this);
        resource = (LinearLayout)view.findViewById(R.id.zi_yuan_fang);
        resource.setOnClickListener(this);*/
        pop = LayoutInflater.from(getContext()).inflate(R.layout.prograssbar_pop, null);
        popupWindow = new PopupWindow(pop, 900, 900);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.alph));
        popupWindow.setFocusable(false);
        popupWindow.update();
        progressBar = (ProgressBar)pop.findViewById(R.id.cache_bar);
        personData();
        return view;
    }

    @Subscribe
    public void onEventMainThread(FirstEventBus eventBus){
        Intent intent = new Intent(getActivity(), LandingActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /*case R.id.dealer:
                if (CustomInfo.getInfo().isDealer_landing() || !dealer_id.equals("")){
                    Intent dealerIntent = new Intent(getActivity(), DealerActivity.class);
                    startActivity(dealerIntent);
                }else {
                    Intent dealerLandingIntent = new Intent(getActivity(), DealerLandingActivity.class);
                    startActivity(dealerLandingIntent);
                }
                break;
            case R.id.zi_yuan_fang:
                if (CustomInfo.getInfo().isResource_landing() || !resource_id.equals("")){
                    Intent resourceIntent = new Intent(getActivity(), ResourceActivity.class);
                    startActivity(resourceIntent);
                }else {
                    Intent resourceLandingIntent = new Intent(getActivity(), ResourceLandingActivity.class);
                    startActivity(resourceLandingIntent);
                }
                break;*/
            case R.id.personal_center:
                Intent settingIntent = new Intent(getActivity(), SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.user_feedback:
                Intent feedbackIntent = new Intent(getActivity(), UserFeedbackActivity.class);
                startActivity(feedbackIntent);
                break;
            case R.id.call_for_me:
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:4008082229"));
                startActivity(i);
                break;
            case R.id.clear_cache:
                popupWindow.showAtLocation(clear_cache, Gravity.CENTER, 0, 0);
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 0.9f;
                getActivity().getWindow().setAttributes(lp);
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        popupWindow.dismiss();
                    }
                };
                handler.postDelayed(runnable, 1500);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                        lp.alpha = 1f;
                        getActivity().getWindow().setAttributes(lp);
                        Toast.makeText(getActivity(), "清除缓存成功", Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        personData();
        sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        dealer_id = sharedPreferences.getString("dealer_id", "");
        resource_id = sharedPreferences.getString("resource_id", "");
    }

    private void personData(){
        RequestParams params = new RequestParams();
        params.put("id", CustomInfo.getInfo().getUser_id());
        HttpClient.get_istance().post(NetUrl.PERSON_DATA, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Type listType = new TypeToken<LinkedList<PersonData>>(){}.getType();
                Gson gson = new Gson();
                LinkedList<PersonData> list = gson.fromJson(response.toString(), listType);
                    if (list.size() != 0 && list.get(0).head != null && !list.get(0).head.equals("")){
                        String a = NetUrl.TEST_LF_HEAD + list.get(0).head;
                        Picasso.with(getContext()).load(NetUrl.TEST_LF_HEAD +
                                list.get(0).head)
                                .into(user_avatar);
                        user_phone.setText(list.get(0).phone);
                }
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable,
                                  JSONArray errorResponse) {

            }
        });
    }
}
