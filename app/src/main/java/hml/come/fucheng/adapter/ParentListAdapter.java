package hml.come.fucheng.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.BuyChooseCarData;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/7/18.
 */

public class ParentListAdapter extends BaseAdapter {
    private ArrayList<BuyChooseCarData.Content> list;
    private Context context;
    private int type;
    private String search_text;
    public ParentListAdapter(Context context, ArrayList<BuyChooseCarData.Content> list, int type){
        this.context = context;
        this.list = list;
        this.type = type;
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

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BuyChooseCarData.Content item = list.get(position);
        ViewHold viewHold;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.parent_item_view, parent, false);
            viewHold = new ViewHold();
            viewHold.textView = (TextView)convertView.findViewById(R.id.list_parent_text);
            viewHold.imageIcon = (ImageView) convertView.findViewById(R.id.image_icon);
            viewHold.line = (View)convertView.findViewById(R.id.line);
            viewHold.list_view = (LinearLayout)convertView.findViewById(R.id.list_item);
            viewHold.item = (LinearLayout)convertView.findViewById(R.id.choose_list);
            viewHold.back = (LinearLayout)convertView.findViewById(R.id.car_list_back);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold)convertView.getTag();
        }
        if(item.flag){
            viewHold.imageIcon.setVisibility(View.GONE);
            viewHold.line.setVisibility(View.GONE);
            viewHold.textView.setText(item.name);
            viewHold.textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            viewHold.textView.setTextSize(18);
            viewHold.textView.setTextColor(convertView.getResources().getColor(R.color.text));
            viewHold.item.setBackgroundColor(convertView.getResources().getColor(R.color.back));
            viewHold.list_view.setClickable(true);
        }else{if (type == 1){
            viewHold.item.setBackgroundColor(convertView.getResources().getColor(R.color.whrite));
            viewHold.imageIcon.setVisibility(View.GONE);
            viewHold.textView.setTextColor(convertView.getResources().getColor(R.color.blue_h));
        }else {
            viewHold.item.setBackgroundColor(convertView.getResources().getColor(R.color.whrite));
            viewHold.imageIcon.setVisibility(View.VISIBLE);
        }
            viewHold.line.setVisibility(View.VISIBLE);
            Picasso.with(context).load(NetUrl.TEST_LF_HEAD + item.thumbnail)
                    .placeholder(R.mipmap.wrong_h)
                    .error(R.mipmap.wrong_h)
                    .into(viewHold.imageIcon);
            viewHold.textView.setText(item.name);
            viewHold.textView.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            viewHold.list_view.setClickable(false);
            viewHold.item.setBackgroundColor(convertView.getResources().getColor(R.color.whrite));
        }
        if (search_text != null && !search_text.equals("")){
            Pattern r = Pattern.compile(search_text);
            Matcher m = r.matcher(list.get(position).name);
            if (!m.find()){
                viewHold.item.setVisibility(View.GONE);
            }else {
                viewHold.item.setVisibility(View.VISIBLE);
            }
        }else {
            viewHold.item.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
    public void onSearch(String search_text){
        this.search_text = search_text;
    }
    public class ViewHold{
        private TextView textView;
        private ImageView imageIcon;
        private View line;
        private LinearLayout list_view;
        private LinearLayout item;
        private LinearLayout back;
    }
}
