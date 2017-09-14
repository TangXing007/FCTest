package hml.come.fucheng.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.RongZiData;

/**
 * Created by TX on 2017/7/20.
 */

public class RongZiAdapter extends BaseAdapter {
    private Context context;
    private List<RongZiData.Data> list;
    public RongZiAdapter(Context context, List<RongZiData.Data> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.rongzi_list_item, parent, false);
            viewHold = new ViewHold();
            viewHold.name = (TextView)convertView.findViewById(R.id.rognzi_list_name);
            viewHold.content = (WebView)convertView.findViewById(R.id.rongzi_list_content);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.name.setText(list.get(position).name);
        viewHold.content.loadDataWithBaseURL(null, Html.fromHtml(list.get(position).description)
                .toString(), "text/html", "UTF-8", null);
        return convertView;
    }
    public class ViewHold{
        private TextView name;
        private WebView content;
    }
}
