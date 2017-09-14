package hml.come.fucheng.moudle;

import java.util.ArrayList;

import hml.come.fucheng.net_work.BaseResult;

/**
 * Created by TX on 2017/8/9.
 */

public class CarListData {
    public String msg;
    public int code;
    public ArrayList<Data> data;
    public class Data{
        public boolean check;
        public String aid;
        public String car_name;
        public String dealer_pricing;
    }
}
