package hml.come.fucheng.appApplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Field;

import hml.come.fucheng.singleton.CustomInfo;

/**
 * Created by TX on 2017/7/26.
 */

public class AppAplication extends Application{
    private SharedPreferences preferences;
    private String user_id, dealer_id, resource_id, type;
    private String number;
    private static Context instence = null;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(){
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        //FontsOverride.setDefaultFont(this, "sans-serif", "fonts/Roboto-Light.ttf");
        preferences = getSharedPreferences("data", MODE_PRIVATE);
        user_id = preferences.getString("user_id", "");
        number = preferences.getString("number", "");
        dealer_id = preferences.getString("dealer_id", "");
        resource_id = preferences.getString("resource_id", "");
        type = preferences.getString("type", "");
        if (user_id == null || user_id.equals("")){
            CustomInfo.getInfo().setIslanding(false);
        }else {
            CustomInfo.getInfo().setIslanding(true);
            CustomInfo.getInfo().setUser_id(user_id);
            CustomInfo.getInfo().setNumber(number);
            CustomInfo.getInfo().setType(type);
        }

        if (dealer_id == null || dealer_id.equals("")){
            CustomInfo.getInfo().setDealer_landing(false);
        }else {
            CustomInfo.getInfo().setDealer_id(dealer_id);
            CustomInfo.getInfo().setDealer_landing(true);
        }

        if (resource_id == null || resource_id.equals("")){
            CustomInfo.getInfo().setResource_landing(false);
        }else {
            CustomInfo.getInfo().setResource_id(resource_id);
            CustomInfo.getInfo().setResource_landing(true);
        }
        instence = this;
    }
    public static Context getInstence(){
        return instence;
    }
}


