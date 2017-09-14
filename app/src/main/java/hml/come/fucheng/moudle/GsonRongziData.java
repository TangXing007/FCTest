package hml.come.fucheng.moudle;

import java.util.ArrayList;

import hml.come.fucheng.net_work.BaseResult;

/**
 * Created by TX on 2017/7/20.
 */

public class GsonRongziData extends BaseResult{
    public ArrayList<RongZiData> data;
    public class RongZiData {
            public int id;
            public String name;
            public String description;
            public String content;
    }

}
