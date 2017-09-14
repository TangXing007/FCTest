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
 * Created by Administrator on 2017/8/28.
 */

public class SearchAdapter extends BaseAdapter{
    private Context context;
    private String[] list;
    public SearchAdapter(Context context, String[] list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold;
        if (convertView == null){
            viewHold = new ViewHold();
            convertView = LayoutInflater.from(context).inflate(R.layout.search_list_item, parent, false);
            viewHold.search_text = (TextView)convertView.findViewById(R.id.search_list_text);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
            viewHold.search_text.setText(list[position]);
        return convertView;
    }

    private class ViewHold{
        public TextView search_text;
    }
}
