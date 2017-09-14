package hml.come.fucheng.adapter;

import android.content.Context;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.SalesManagementListData;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/8/10.
 */

public class SalesListAdapter extends BaseAdapter {
    private List<SalesManagementListData.Data> list;
    private String search_text;
    private Context context;
    public SalesListAdapter(Context context, List<SalesManagementListData.Data> list){
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold viewHold;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.sales_list_item, parent, false);
            viewHold = new ViewHold();
            viewHold.imageView = (ImageView)convertView.findViewById(R.id.sales_list_image);
            viewHold.car_name = (TextView)convertView.findViewById(R.id.sales_car_name);
            viewHold.new_car_price = (TextView)convertView.findViewById(R.id.sales_new_car_guided);
            viewHold.recycl_price = (TextView)convertView.findViewById(R.id.recycl_price);
            viewHold.time = (TextView)convertView.findViewById(R.id.sales_time);
            viewHold.rootview = (LinearLayout)convertView.findViewById(R.id.root_item);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(context).load(NetUrl.TEST_LF_HEAD + "/" + list.get(position).certificate_photos)
                .placeholder(R.mipmap.wrong_h)
                .error(R.mipmap.wrong_h)
                .into(viewHold.imageView);
        viewHold.car_name.setText(list.get(position).car_name);
        viewHold.new_car_price.setText(list.get(position).estimate_price);
        viewHold.recycl_price.setText(list.get(position).mat_endowment);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long times = Long.parseLong(list.get(position).creatime);
        String str = dateFormat.format(new Date(times * 1000));
        viewHold.time.setText(str);
        if (search_text != null && !search_text.equals("")){
            Pattern r = Pattern.compile(search_text);
            Matcher m = r.matcher(list.get(position).car_name);
            if (!m.find()){
                viewHold.rootview.setVisibility(View.GONE);
            }else {
                viewHold.rootview.setVisibility(View.VISIBLE);
            }
        }else {
            viewHold.rootview.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    public void onSearch(String search_text){
        this.search_text = search_text;
    }

    public class ViewHold{
        private ImageView imageView;
        private TextView car_name;
        private TextView new_car_price;
        private TextView recycl_price;
        private TextView time;
        private LinearLayout rootview;
    }
}
