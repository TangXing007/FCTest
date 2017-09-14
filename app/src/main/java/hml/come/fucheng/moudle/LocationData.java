package hml.come.fucheng.moudle;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/9.
 */

public class LocationData {
    public String msg;
    public String code;
    public ArrayList<Data> data;
    public class Data{
        public String cityname;
        public int id;
        public boolean check;
    }
}
