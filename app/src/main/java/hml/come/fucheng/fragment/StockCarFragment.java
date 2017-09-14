package hml.come.fucheng.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import hml.come.fucheng.R;
import hml.come.fucheng.activity.BiaoZhiActivity;
import hml.come.fucheng.adapter.ParentListAdapter;
import hml.come.fucheng.adapter.SearchAdapter;
import hml.come.fucheng.moudle.BuyChooseCarData;
import hml.come.fucheng.net_work.HttpClient;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/7/18.
 */

public class StockCarFragment extends Fragment {
    private ListView parent_list_view;
    private BuyChooseCarData chooseCarData;
    private ParentListAdapter parentAdapter;
    private ArrayList<BuyChooseCarData.Content> chose_list = new ArrayList<>();
    String[] searchList = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private ListView searchView;
    private SearchAdapter searchAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInsatanceState){
        View view = inflater.inflate(R.layout.fragment_new_car, viewGroup, false);
        parent_list_view = (ListView)view.findViewById(R.id.new_car_parent_listview);
        searchView = (ListView)view.findViewById(R.id.search_list);
        onSearch();
        onHttp();
        return view;
    }

    private void onHttp(){
        RequestParams params = new RequestParams();
        params.put("car_pinpai", "pinpai");
        HttpClient.get_istance().get(NetUrl.BUY_CHOOSE_CAR, null, new JsonHttpResponseHandler(){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                if(statusCode == 200){
                    Gson gson = new Gson();
                    chooseCarData = gson.fromJson(response.toString(), BuyChooseCarData.class);
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
                parent_list_view.setAdapter(parentAdapter);
                parent_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), BiaoZhiActivity.class);
                        intent.putExtra("title",chose_list.get(position).name);
                        intent.putExtra("aid", chose_list.get(position).id + "");
                        int type = 2;
                        intent.putExtra("type",type + "");
                        startActivity(intent);
                    }
                });
            }
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {

            }
        });
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
                            parent_list_view.smoothScrollToPosition(i);
                        }
                    }
                }
            }
        });
    }
}
