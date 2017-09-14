package hml.come.fucheng.net_work;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by TX on 2017/8/15.
 */

public class NetBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            /*intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);*/
            if(NetworkInfo.State.CONNECTED==info.getState()){
                String a= "aaa";
            }else{
                intent = new Intent(context, NoNetActivity.class);
                context.startActivity(intent);
            }
        }
    }
}
