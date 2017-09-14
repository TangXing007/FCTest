package hml.come.fucheng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.SalesManagementListData;

/**
 * Created by Administrator on 2017/9/14.
 */

public class OptionsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SalesManagementListData.Data> list;
    public OptionsAdapter(Context context, ArrayList<SalesManagementListData.Data> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.options_fragment, parent, false);
            viewHold = new ViewHold();
            viewHold.serial = (TextView)convertView.findViewById(R.id.serial);
            viewHold.time = (TextView)convertView.findViewById(R.id.options_time);
            viewHold.models = (TextView)convertView.findViewById(R.id.models);
            viewHold.color = (TextView)convertView.findViewById(R.id.options_color);
            viewHold.recycle_price = (TextView)convertView.findViewById(R.id.options_recycl_price);
            viewHold.sales_price = (TextView)convertView.findViewById(R.id.options_sale_price);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.serial.setText(list.get(position).modules);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long times = Long.parseLong(list.get(position).creatime);
        String str = dateFormat.format(new Date(times * 1000));
        viewHold.time.setText(str);
        viewHold.models.setText(list.get(position).car_name);
        viewHold.color.setText(list.get(position).color);
        viewHold.recycle_price.setText(list.get(position).estimate_price + "元");
        viewHold.sales_price.setText(list.get(position).deprice  + "元");
        return convertView;
    }

    public class ViewHold{
        TextView serial;
        TextView time;
        TextView models;
        TextView color;
        TextView recycle_price;
        TextView sales_price;
    }
}
