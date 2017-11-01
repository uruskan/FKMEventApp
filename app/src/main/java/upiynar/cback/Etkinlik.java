package upiynar.cback;

/**
 * Created by shock on 11/1/2017.
 */

public class Etkinlik {
    private String etkinlik_name;
    private String etkinlik_date;
    private String etkinlik_time;
    private String etkinlik_imgURL;
public Etkinlik(String etkinlik_name,String etkinlik_date,String etkinlik_time,String etkinlik_imgURL){
    this.etkinlik_name = etkinlik_name;
    this.etkinlik_date = etkinlik_date;
    this.etkinlik_time = etkinlik_time;
    this.etkinlik_imgURL = etkinlik_imgURL;
}
public String getEtkinlik_name(){
    return  etkinlik_name;
}
public String getEtkinlik_date(){
    return etkinlik_date;
}
public String getEtkinlik_time(){
    return etkinlik_time;
}
public String getEtkinlik_imgURL(){
    return etkinlik_imgURL;
}
public void setEtkinlik_name(String etkinlik_name){
    this.etkinlik_name = etkinlik_name;
}

    public void setEtkinlik_date(String etkinlik_date) {
        this.etkinlik_date = etkinlik_date;
    }

    public void setEtkinlik_time(String etkinlik_time) {
        this.etkinlik_time = etkinlik_time;
    }

    public void setEtkinlik_imgURL(String etkinlik_imgURL) {
        this.etkinlik_imgURL = etkinlik_imgURL;
    }

}
