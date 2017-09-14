package hml.come.fucheng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hml.come.fucheng.R;
import hml.come.fucheng.net_work.NetUrl;
import hml.come.fucheng.moudle.PriceData;

/**
 * Created by TX on 2017/7/21.
 */

public class PriceAdater extends BaseAdapter {
    private List<PriceData.Data> list;
    private Context context;
    private int type;
    private String search_text;
    public PriceAdater(Context context, List<PriceData.Data> list, int type){
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.price_list_item, parent, false);
            viewHold = new ViewHold();
            viewHold.car_image = (ImageView)convertView.findViewById(R.id.car_image);
            viewHold.car_name = (TextView)convertView.findViewById(R.id.car_name);
            viewHold.manufacturers_price = (TextView)convertView.findViewById(R.id.manufacturers_price);
            viewHold.dealer_pricing = (TextView)convertView.findViewById(R.id.dealer_pricing);
            viewHold.item = (LinearLayout)convertView.findViewById(R.id.cars_list);
            viewHold.line = (View)convertView.findViewById(R.id.line_price);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        if (type == 1){
            viewHold.car_image.setVisibility(View.GONE);
            viewHold.manufacturers_price.setVisibility(View.GONE);
            viewHold.dealer_pricing.setVisibility(View.GONE);
            viewHold.car_name.setText(list.get(position).car_name);
            viewHold.line.setVisibility(View.VISIBLE);
            viewHold.car_name.setTextColor(convertView.getResources().getColor(R.color.blue_h));
        }else {
            viewHold.line.setVisibility(View.VISIBLE);
            viewHold.car_image.setVisibility(View.VISIBLE);
            viewHold.manufacturers_price.setVisibility(View.VISIBLE);
            viewHold.dealer_pricing.setVisibility(View.VISIBLE);
            Picasso.with(context).load(NetUrl.TEST_LF_HEAD + list.get(position).thumbnail)
                    .placeholder(R.mipmap.wrong_h)
                    .error(R.mipmap.wrong_h)
                    .into(viewHold.car_image);
            viewHold.car_name.setText(list.get(position).car_name);
            viewHold.manufacturers_price.setText("新车市场价：" + list.get(position).manufacturers_price + ".00");
            viewHold.dealer_pricing.setText(list.get(position).dealer_pricing);
        }
        if (search_text != null && !search_text.equals("")){
            Pattern r = Pattern.compile(search_text);
            Matcher m = r.matcher(list.get(position).car_name);
            if (!m.find()){
                viewHold.item.setVisibility(View.GONE);
                viewHold.line.setVisibility(View.GONE);
            }else {
                viewHold.item.setVisibility(View.VISIBLE);
                viewHold.line.setVisibility(View.VISIBLE);
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
        private ImageView car_image;
        private TextView car_name;
        private TextView manufacturers_price;
        private TextView dealer_pricing;
        private LinearLayout item;
        private View line;
    }
}
