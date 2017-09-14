package hml.come.fucheng.moudle;

import java.util.ArrayList;

import hml.come.fucheng.net_work.BaseResult;

/**
 * Created by TX on 2017/7/22.
 */

public class CarWatchData extends BaseResult{
    public Data data;
    public class Data{
        public String aid;
        public String sid;
        public String car_type;
        public String brand;
        public String thumbnail;
        public String cars;
        public String manufacturers_price;
        public String dealer_pricing;
        public String car_name;
        //public String content;
        public String creatime;
        public String car_number;
        //public String likes;
        //public String recommend;
        public String address;
    }
}
