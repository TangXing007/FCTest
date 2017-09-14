
package hml.come.fucheng.net_work;

/**
 * Created by TX on 2017/7/24.
 */

public class BaseResult {
    public String code;
    public String msg;

    public boolean isOK(){
        if(code.equals("200")){
            return true;
        }
        return false;
    }
}
