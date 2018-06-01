package cn.vangelis.rxremanager;

/**
 * Comment: 服务器返回的广告数据体
 *
 * @author Vangelis.Wang in Make1
 * @date 2018/5/8
 * Email:Vangelis.wang@make1.cn
 */

public class AdEntity {

    /**
     * adv_id : 1
     * run_interface : 是沃尔沃
     * show_del : 1
     * status : 1
     * title : 标题
     * description : 内容简介
     * url : www.baidu.com
     * urlpic : http://n.sinaimg.cn/news/1_img/upload/cf3881ab/762/w1000h562/20180503/Zr1V-fzyqqiq3362910.jpg
     * m_id : 1
     * etime : null
     * ctime : 2018-05-07 10:37:29
     * utime : 2018-05-08 15:51:13
     * show_type : 1
     */

    private int adv_id;
    private String run_interface;
    private int show_del;
    private int status;
    private String title;
    private String description;
    private String url;
    private String urlpic;
    private int m_id;
    private Object etime;
    private String ctime;
    private String utime;
    private int show_type;

    public int getAdv_id() {
        return adv_id;
    }

    public void setAdv_id(int adv_id) {
        this.adv_id = adv_id;
    }

    public String getRun_interface() {
        return run_interface;
    }

    public void setRun_interface(String run_interface) {
        this.run_interface = run_interface;
    }

    public int getShow_del() {
        return show_del;
    }

    public void setShow_del(int show_del) {
        this.show_del = show_del;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlpic() {
        return urlpic;
    }

    public void setUrlpic(String urlpic) {
        this.urlpic = urlpic;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    public Object getEtime() {
        return etime;
    }

    public void setEtime(Object etime) {
        this.etime = etime;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public int getShow_type() {
        return show_type;
    }

    public void setShow_type(int show_type) {
        this.show_type = show_type;
    }
}
