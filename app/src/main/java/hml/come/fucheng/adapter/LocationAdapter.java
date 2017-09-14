package hml.come.fucheng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.LocationData;

/**
 * Created by Administrator on 2017/9/9.
 */

public class LocationAdapter extends BaseAdapter {
    private ArrayList<LocationData.Data> list;
    private Context context;
    public LocationAdapter(ArrayList<LocationData.Data> list, Context context){
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
        ViewHold viewHold;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.car_list_item, parent, false);
            viewHold = new ViewHold();
            viewHold.location = (TextView)convertView.findViewById(R.id.car_list_name);
            viewHold.button = (RadioButton)convertView.findViewById(R.id.car_list_button);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.location.setText(list.get(position).cityname);
        if (list.get(position).check){
            viewHold.button.setChecked(true);
        }else {
            viewHold.button.setChecked(false);
        }
        return convertView;
    }
    public class ViewHold{
        private TextView location;
        private RadioButton button;
    }
}
