package hml.come.fucheng.moudle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TX on 2017/7/20.
 */

public class JXSJinRongData {
    public int code;
    public String msg;
    public List<Data> datas = new ArrayList<>();
    public class Data{
        public int id;
        public String name;
        public String yaoqiu;
        public String edu;
        public String qixian;
        public String content;
    }
    public Data getData(){
        return new Data();
    }
}
