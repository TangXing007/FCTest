package hml.come.fucheng.net_work;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by TX on 2017/7/31.
 */

public class SaleManagementChildHttp {
    private String status;
    public SaleManagementChildHttp(String status){
        this.status = status;
    }
    public void Http(){
        RequestParams params = new RequestParams();
        params.put("status", status);
        HttpClient.get_istance().post(NetUrl.SALES_MANAGEMENT_CHILD, params, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });
    }
}
