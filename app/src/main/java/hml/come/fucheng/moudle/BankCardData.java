package hml.come.fucheng.moudle;

import java.util.ArrayList;

import hml.come.fucheng.net_work.BaseResult;

/**
 * Created by TX on 2017/8/8.
 */

public class BankCardData extends BaseResult{
    public ArrayList<Data> data;
    public class Data{
        public String id;
        public String userid;
        public String opening;
        public String opening_bank;
        public String where_account;
        public String name;
        public String phone;
        public String address;
        public String creatime;
    }
}
