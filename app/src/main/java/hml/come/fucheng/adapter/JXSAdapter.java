package hml.come.fucheng.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.JXSJinRongData;

/**
 * Created by TX on 2017/7/20.
 */

public class JXSAdapter extends BaseAdapter {
    private List<JXSJinRongData.Data> list;
    private Context context;
    public JXSAdapter(Context context, List<JXSJinRongData.Data> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.jxs_list_item, parent, false);
            viewHold = new ViewHold();
            viewHold.name = (TextView)convertView.findViewById(R.id.list_name);
            viewHold.content = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.name.setText(list.get(position).name);
        viewHold.content.setText(Html.fromHtml(list.get(position).content).toString());
        return convertView;
    }
    public class ViewHold{
        private TextView name;
        private TextView content;
    }
}
