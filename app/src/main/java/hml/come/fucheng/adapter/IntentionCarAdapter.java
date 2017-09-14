package hml.come.fucheng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.IntentionCarData;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/7/27.
 */

public class IntentionCarAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<IntentionCarData.Data> list;
    public IntentionCarAdapter(Context context, ArrayList<IntentionCarData.Data> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_intention_car, parent, false);
            viewHold = new ViewHold();
            viewHold.intention_car_title = (TextView)convertView.findViewById(R.id.intention_car_title);
            viewHold.intention_car_price = (TextView)convertView.findViewById(R.id.intention_car_price);
            viewHold.intention_car_image = (ImageView)convertView.findViewById(R.id.intention_car_image);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.intention_car_title.setText(list.get(position).car_name);
        viewHold.intention_car_price.setText("￥" + list.get(position).manufacturers_price);
        Picasso.with(context).load(NetUrl.TEST_LF_HEAD + list.get(position).thumbnail)
                .placeholder(R.mipmap.wrong_h)
                .error(R.mipmap.wrong_h)
                .into(viewHold.intention_car_image);
        return convertView;
    }
    public class ViewHold{
        private TextView intention_car_title;
        private TextView intention_car_price;
        private ImageView intention_car_image;
    }
}
