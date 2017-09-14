package hml.come.fucheng.moudle;

import hml.come.fucheng.net_work.BaseResult;

/**
 * Created by TX on 2017/7/20.
 */

public class BankData extends BaseResult {
    public class Data{
        public int id;
        public String bank_category;
        public String name;
        public String bank_name;
        public String content;
    }
    public Data getData(){
        return new Data();
    }
}
