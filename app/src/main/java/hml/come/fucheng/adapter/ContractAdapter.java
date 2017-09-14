package hml.come.fucheng.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.ContractData;
import hml.come.fucheng.net_work.NetUrl;

/**
 * Created by TX on 2017/8/11.
 */

public class ContractAdapter extends BaseAdapter {
    private ArrayList<ContractData.Data> list;
    private Context context;
    private OnItemClickListener monItemClickListener = null;
    public ContractAdapter(ArrayList<ContractData.Data> list, Context context){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.contract_list_item, parent, false);
            viewHold = new ViewHold();
            viewHold.textView = (TextView) convertView.findViewById(R.id.contract_text);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.textView.setText(list.get(position).carName);
        return convertView;
    }
    public class ViewHold{
        TextView textView;
    }
}
