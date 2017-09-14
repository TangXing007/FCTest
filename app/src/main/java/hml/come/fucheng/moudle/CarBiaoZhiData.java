package hml.come.fucheng.moudle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TX on 2017/7/20.
 */

public class CarBiaoZhiData {
    public int code;
    public String msg;
    public List<Data> data;
    public class Data{
        public int id;
        public String thumbnail;
        public String name;
    }
    public Data getData(){
        return new Data();
    }
}
