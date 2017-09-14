package hml.come.fucheng.net_work;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TX on 2017/7/20.
 */

public class HttpClient {
    private static HttpClient istance = null;
    private AsyncHttpClient client;
    public HttpClient(){
        client = new AsyncHttpClient();
    }
    public static HttpClient get_istance(){
        if (istance == null){
            istance =new HttpClient();
        }
        return istance;
    }
    public void get(String Url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(Url, params, responseHandler);
    }

    public void post(String Url, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.post(Url, params, responseHandler);
    }


    public void TwoHttp(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){;
        params.put("chexin",1);
        HttpClient.get_istance().get(NetUrl.SHOU_YE_CAR_IMAGE, params, responseHandler);
    }

}
