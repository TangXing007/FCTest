package hml.come.fucheng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hml.come.fucheng.R;

/**
 * Created by TX on 2017/7/22.
 */

public class ColorAdapter extends BaseAdapter {
    private Context context;
    private ArrayList list;
    public ColorAdapter(Context context, ArrayList list){
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
           convertView = LayoutInflater.from(context).inflate(R.layout.color_list_item, parent, false);
           viewHold = new ViewHold();
           viewHold.color_text = (TextView)convertView.findViewById(R.id.color_text);
           convertView.setTag(viewHold);
       }else {
           viewHold = (ViewHold) convertView.getTag();
       }
        viewHold.color_text.setText(list.get(position).toString());
        return convertView;
    }
    public class ViewHold{
        private TextView color_text;
    }
}
