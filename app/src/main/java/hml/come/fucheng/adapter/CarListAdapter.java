package hml.come.fucheng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.CarListData;

/**
 * Created by TX on 2017/8/9.
 */

public class CarListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CarListData.Data> list;
    HashMap<String,Boolean> states=new HashMap<String,Boolean>();
    private boolean check;
    public CarListAdapter(Context context, ArrayList<CarListData.Data> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHold viewHold;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.car_list_item, parent, false);
            viewHold = new ViewHold();
            viewHold.car_name = (TextView)convertView.findViewById(R.id.car_list_name);
            viewHold.button = (RadioButton)convertView.findViewById(R.id.car_list_button);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.car_name.setText(list.get(position).car_name);
        if (list.get(position).check){
            viewHold.button.setChecked(true);
        }else {
            viewHold.button.setChecked(false);
        }
        return convertView;
    }

    public class ViewHold{
        private TextView car_name;
        private RadioButton button;
    }
}
