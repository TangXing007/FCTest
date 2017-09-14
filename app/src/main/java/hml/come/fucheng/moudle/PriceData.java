package hml.come.fucheng.moudle;

import java.util.ArrayList;

/**
 * Created by TX on 2017/7/21.
 */

public class PriceData {
    public int code;
    public String msg;
    public ArrayList<Data> data;
    public class Data{
        public String aid;
        //public String brand;
        public String thumbnail;
        public String car_name;
        public String manufacturers_price;
        public String dealer_pricing;
    }
    public Data getdate(){
        return new Data();
    }
}
