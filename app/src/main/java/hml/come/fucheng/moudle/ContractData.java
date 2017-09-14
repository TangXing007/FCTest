package hml.come.fucheng.moudle;

import java.io.Serializable;
import java.util.ArrayList;

import hml.come.fucheng.net_work.BaseResult;

/**
 * Created by TX on 2017/8/11.
 */

public class ContractData extends BaseResult implements Serializable{
    public ArrayList<Data> data;
    public class Data implements Serializable{
        public ArrayList<Image> imgUrl;
        public String carName;
    }
    public class Image implements Serializable{
        public String img_url;
    }
}
