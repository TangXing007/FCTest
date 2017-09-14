package hml.come.fucheng.singleton;

/**
 * Created by TX on 2017/7/26.
 */

public class CustomInfo {
    private static CustomInfo info = null;
    private boolean islanding;
    private String number;
    private String user_id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;
    public String dealer_id;
    public boolean dealer_landing;
    public String resource_id;
    public boolean resource_landing;
    private CustomInfo(){}
    public static CustomInfo getInfo(){
        if (info == null){
            info = new CustomInfo();
        }
        return info;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean islanding() {
        return islanding;
    }

    public void setIslanding(boolean islanding) {
        this.islanding = islanding;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDealer_id() {
        return dealer_id;
    }

    public void setDealer_id(String dealer_id) {
        this.dealer_id = dealer_id;
    }

    public boolean isDealer_landing() {
        return dealer_landing;
    }

    public void setDealer_landing(boolean dealer_landing) {
        this.dealer_landing = dealer_landing;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public boolean isResource_landing() {
        return resource_landing;
    }

    public void setResource_landing(boolean resource_landing) {
        this.resource_landing = resource_landing;
    }
}
