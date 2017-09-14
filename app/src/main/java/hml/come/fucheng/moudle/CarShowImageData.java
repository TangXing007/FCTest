package hml.come.fucheng.moudle;

import java.util.ArrayList;
import java.util.List;

import hml.come.fucheng.net_work.BaseResult;

/**
 * Created by TX on 2017/7/20.
 */

public class CarShowImageData {
    public int code;
    public String msg;
    public List<ImageData> data;
    public class ImageData{
        public int aid;
        public String thumbnail;
        public String brand;
        public String car_name;
        public String tated_thumbnail;
    }
    public ImageData getData(){
        return new ImageData();
    }
}
