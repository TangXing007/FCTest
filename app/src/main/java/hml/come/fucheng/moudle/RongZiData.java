package hml.come.fucheng.moudle;

/**
 * Created by TX on 2017/7/20.
 */

public class RongZiData {
    public String code;
    public String msg;
    public class Data{
        public String id;
        public String name;
        public String description;
        public String content;
    }
    public Data getData(){
        return new Data();
    }
}
