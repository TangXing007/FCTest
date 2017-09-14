package hml.come.fucheng.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hml.come.fucheng.R;
import hml.come.fucheng.moudle.BankCardData;

/**
 * Created by TX on 2017/8/8.
 */

public class BankCardAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BankCardData.Data> list;
    public BankCardAdapter(Context context, ArrayList<BankCardData.Data> list){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.bank_card_list_item, parent, false);
            viewHold = new ViewHold();
            viewHold.cardNumber = (TextView)convertView.findViewById(R.id.bank_card_number);
            viewHold.cardAdress = (TextView)convertView.findViewById(R.id.bank_card_adress);
            convertView.setTag(viewHold);
        }else {
            viewHold = (ViewHold) convertView.getTag();
        }

        String number = list.get(position).where_account;
        if (number.length() > 4){
            String Snum = number.substring(number.length() - 4);
            viewHold.cardNumber.setText("**** **** **** " + Snum);
        }
        viewHold.cardAdress.setText(list.get(position).opening_bank);
        return convertView;
    }

    public class ViewHold{
        private TextView cardNumber;
        private TextView cardAdress;
    }
}
