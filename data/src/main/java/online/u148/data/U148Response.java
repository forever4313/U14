package online.u148.data;


import online.u148.domain.beans.Page;

/**
 * @author: Kevin Dong
 * @date:2016/4/5
 * @email:dongkai@nodescm.com
 */
public class U148Response {

    public static final int CODE_NETWORK_ERROR = Integer.MIN_VALUE;
    public static final int CODE_UNKNOWN_ERROR = Integer.MAX_VALUE;
    public static final int CODE_OK = 1;
    public static final int CODE_ERROR = 0;

    private int code;

    private String msg;

    private String datas;

    private Page page;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String content) {
        this.datas = content;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
