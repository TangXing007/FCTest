package hml.come.fucheng.moudle;

import java.util.ArrayList;
import java.util.List;

import hml.come.fucheng.net_work.BaseResult;

/**
 * Created by TX on 2017/7/24.
 */

public class BuyChooseCarData extends BaseResult{
    public Data data;
    public class Data{
        public ArrayList<Content> A;
        public ArrayList<Content> B;
        public ArrayList<Content> C;
        public ArrayList<Content> D;
        public ArrayList<Content> E;
        public ArrayList<Content> F;
        public ArrayList<Content> G;
        public ArrayList<Content> H;
        public ArrayList<Content> I;
        public ArrayList<Content> J;
        public ArrayList<Content> K;
        public ArrayList<Content> L;
        public ArrayList<Content> M;
        public ArrayList<Content> N;
        public ArrayList<Content> O;
        public ArrayList<Content> P;
        public ArrayList<Content> Q;
        public ArrayList<Content> R;
        public ArrayList<Content> S;
        public ArrayList<Content> T;
        public ArrayList<Content> U;
        public ArrayList<Content> V;
        public ArrayList<Content> W;
        public ArrayList<Content> X;
        public ArrayList<Content> Y;
        public ArrayList<Content> Z;
    }
    public class Content{
        public String id;
        public String name;
        public String thumbnail;
        public boolean flag;
    }

    public Content getContent(String name){
        Content content = new Content();
        content.id = "";
        content.name = name;
        content.thumbnail = "";
        content.flag = true;
        return content;
    }
}
