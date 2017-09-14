package hml.come.fucheng.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.ContractData;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by Administrator on 2017/9/7.
 */

public class ContractImageAdapter extends BaseAdapter {
    private ArrayList<ContractData.Image> list;
    private Context context;
    public ContractImageAdapter(ArrayList<ContractData.Image> list, Context context){
        this.list = list;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_view_item, parent, false);
            viewHold = new ViewHold();
            viewHold.imageView = (ImageView)convertView.findViewById(R.id.contract_image);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        //viewHold.imageView.setImageResource(list.get(position));
        Picasso.with(context).load(NetUrl.TEST_LF_HEAD +"/" +  list.get(position).img_url).resize(100, 90)
                .centerCrop()
                .into(viewHold.imageView);
        return convertView;
    }
    public class ViewHold{
        ImageView imageView;
    }
}
