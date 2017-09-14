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
import hml.come.fucheng.moudle.BankData;

/**
 * Created by TX on 2017/7/20.
 */

public class BankAdapter extends BaseAdapter {
    private Context context;
    private List<BankData.Data> list;
    public BankAdapter(Context context, List<BankData.Data> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.bank_list_item, parent, false);
            viewHold = new ViewHold();
            viewHold.number = (TextView)convertView.findViewById(R.id.bank_number);
            viewHold.name = (TextView)convertView.findViewById(R.id.bank_name);
            viewHold.content = (WebView) convertView.findViewById(R.id.bank_content);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.number.setText(list.get(position).bank_category);
        viewHold.name.setText(list.get(position).bank_name);
        viewHold.content.loadDataWithBaseURL(null, Html.fromHtml(list.get(position).content).toString(),
                "text/html", "utf-8", null);
        return convertView;
    }
    public class ViewHold{
        private TextView number;
        private TextView name;
        private WebView content;
    }
}
