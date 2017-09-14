package hml.come.fucheng.moudle;

import java.util.ArrayList;

import hml.come.fucheng.net_work.BaseResult;

/**
 * Created by TX on 2017/8/10.
 */

public class SalesManagementListData extends BaseResult{
    public ListData data;
    public class ListData{
        public ArrayList<Data> key1;
        public ArrayList<Data> key2;
        public ArrayList<Data> key3;
        public ArrayList<Data> key4;
        public ArrayList<Data> key5;
        public ArrayList<Data> key6;
    }
    public class Data{
        public String id;
        public String dingdanhao;
        public String userid;
        public String head_picture;
        public String side_photo;
        public String rear_photo;
        public String console_photos;
        public String dashboard_photo;
        public String nameplate_photos;
        public String engine_photos;
        public String certificate_photos;
        public String skylight_photos;
        public String modules;
        public String color;
        public String mileage;
        public String chassis_number;
        public String delivery_time;
        public String deprice;
        public String estimate_price;
        public String dianzi;
        public String mat_endowment;
        public String price;
        public String quyu;
        public String status;
        public String sales_status;
        public String creatime;
        public String yewuyuan;
        public String car_name;
    }
    /*public Data list;
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
        public String creatime;
        public String car_number;
        public String recommend;
        public String address;
    }*/
}
