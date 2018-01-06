package com.oa.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.oa.model.approve.T_Personal;
import com.zhilian.model.T_User;

/**
 * 发送短信
 */
public class SMSUtil {

    private static int TIME_OUT = 30 * 1000;
    private static final String ERROR = "ERROR";

    private static String appurl = "http://120.27.144.245:9999/smshttp";
    private static String appid = "102814";
    private static String appname = "hzsrmfkb";
    private static String appsecret = "hzsrmfkb@1";

    private static String URL = "https://sms.189ek.com/yktsms/send";
    private static String APPID = "fBRdCqR0JEtTnF5XRRnJYJ2lO4l6JDtd";
    private static String APPKEY = "YjoOoC98EKlAflHYpm32cFjTbXY4023X";

    /**
     * 发送请求数据到服务端接口地址
     * 
     * @param urlstr
     * @param postdata
     * @return
     */
    private static String sendRequest(String urlstr, String postdata) {

	StringBuilder tempStr = new StringBuilder();

	InputStream in = null;
	BufferedReader rd = null;
	HttpURLConnection urlConn = null;
	try {
	    URL url = new URL(urlstr);
	    urlConn = (HttpURLConnection) url.openConnection();

	    urlConn.setRequestMethod("POST");
	    urlConn.setDoOutput(true);
	    urlConn.setReadTimeout(TIME_OUT);
	    // 组装请求包体json
	    urlConn.getOutputStream().write(postdata.getBytes("utf-8"));
	    urlConn.getOutputStream().flush();
	    in = urlConn.getInputStream();
	    rd = new BufferedReader(new InputStreamReader(in, "utf-8"));
	    String tmps = null;
	    while ((tmps = rd.readLine()) != null) {
		tempStr.append(tmps.trim());
	    }
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	    return ERROR;
	} catch (IOException e) {
	    e.printStackTrace();
	    return ERROR;
	} finally {
	    if (rd != null) {
		try {
		    rd.close();
		} catch (IOException e) {

		    e.printStackTrace();
		}
	    }

	    if (in != null) {
		try {
		    in.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	    }
	    if (urlConn != null)
		urlConn.disconnect();
	}
	return tempStr.toString();
    }

    /**
     * 向上送状态报告
     * 
     * @param url
     * @param appid
     * @param appsecret
     * @param taskid
     * @param mobile
     * @param state
     * @param errorcode
     * @param ismgmsgid
     * @return
     */
    public static String postStatus1(String url, String appsecret, String taskid, String mobile, int state,
	    String errorcode, String ismgmsgid) {
	StringBuffer sb = new StringBuffer("");
	sb.append("taskid=").append(taskid);
	sb.append("&mobile=").append(mobile);
	sb.append("&state=").append(state);
	sb.append("&errorcode=").append(errorcode);
	sb.append("&ismgmsgid=").append(ismgmsgid);
	String s = taskid + mobile + state + errorcode + ismgmsgid;
	String sign = Md5.MD5(s + appsecret);
	// System.out.println("秘钥：：：" + appsecret + ",," + s);
	sb.append("&sign=").append(sign);
	return sendRequest(url, sb.toString());
    }

    /**
     * 向上送状态报告
     * 
     * @param url
     * @param appid
     * @param appsecret
     * @param taskid
     * @param mobile
     * @param state
     * @param errorcode
     * @param ismgmsgid
     * @return
     */
    public static String postStatus(String url, String appsecret, String taskid, String mobile, int state,
	    String errorcode, String ismgmsgid) {
	StringBuffer sb = new StringBuffer("");
	sb.append("taskid=").append(taskid);
	sb.append("&mobile=").append(mobile);
	sb.append("&state=").append(state);
	sb.append("&errorcode=").append(errorcode);
	sb.append("&ismgmsgid=").append(ismgmsgid);
	String s = taskid + mobile + state + errorcode + ismgmsgid;
	String sign = Md5.MD5(s + appsecret);
	// System.out.println("秘钥：：：" + appsecret + ",," + s);
	sb.append("&sign=").append(sign);
	return sendRequest(url, sb.toString());

    }

    /**
     * r 发送mo
     * 
     * @param unitid
     * @param username
     * @param passwd
     * @param spnumber
     * @param msg
     * @param phone
     * @param uptime
     * @return
     */
    public static String postMo1(String url, String appsecret, String msgid, String mobile, String servicenumber,
	    String msg, String uptime) {
	StringBuffer sb = new StringBuffer("");
	sb.append("mobile=").append(mobile);
	sb.append("&servicenumber=").append(servicenumber);
	sb.append("&msgid=").append(msgid);
	sb.append("&msg=").append(msg);
	sb.append("&uptime=").append(uptime);

	String s = msgid + mobile + servicenumber + msg + uptime;
	String sign = Md5.MD5(s + appsecret);
	sb.append("&sign=").append(sign);

	return sendRequest(url, sb.toString());
    }

    /**
     * r 发送mo
     * 
     * @param unitid
     * @param username
     * @param passwd
     * @param spnumber
     * @param msg
     * @param phone
     * @param uptime
     * @return
     */
    public static String postMo(String url, String appsecret, String msgid, String mobile, String servicenumber,
	    String msg, String uptime) {
	StringBuffer sb = new StringBuffer("");
	sb.append("mobile=").append(mobile);
	sb.append("&servicenumber=").append(servicenumber);
	sb.append("&msgid=").append(msgid);
	sb.append("&msg=").append(msg);
	sb.append("&uptime=").append(uptime);

	String s = msgid + mobile + servicenumber + msg + uptime;
	String sign = Md5.MD5(s + appsecret);
	// System.out.println("秘钥：：：" + appsecret + ",," + s);

	sb.append("&sign=").append(sign);

	return sendRequest(url, sb.toString());
    }

    public static String sendsms1(String mobile, String content) {
	String result = "";
	try {
	    StringBuffer sb = new StringBuffer("");
	    sb.append("act=sendmsg");
	    sb.append("&unitid=").append(appid);
	    sb.append("&username=").append(appname);
	    sb.append("&passwd=").append(Md5.MD5(appsecret));
	    sb.append("&phone=").append(mobile);
	    sb.append("&msg=").append(content);
	    sb.append("&port=");
	    sb.append("&sendtime=");
	    result = sendRequest(appurl, sb.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    public static String sendsms(String mobile, String content) {
	String result = "";
	try {
	    content = "【惠州市人防办】" + content;
	    StringBuffer sb = new StringBuffer("");
	    sb.append("appid=").append(APPID);
	    sb.append("&mobile=").append(mobile);
	    sb.append("&msg=").append(content);
	    String sign = Md5.MD5(APPID + mobile + content + APPKEY);
	    sb.append("&sign=").append(sign);
	    result = sendRequest(URL, sb.toString());
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }

    public static void main(String[] args) {
	//String mobile = "13502281978,13928353899,18207520275,13923628112,13809661966,13500177697,18819698848,18819989847,13923650753,18122590202,13502226698,18802623140,18928362766,13692772662"; // 综合科
	String mobile = "13502279898,13480586667,13928324998,13516681812,13068292826,13790751572,15018628688"; // 工程科
	//String mobile = "13927366663,13927309129,18802629666,15916363289,13829989510,13414516268,13480555901,13502295338,13923626999"; // 质监站
	//String mobile = "13928312899,13502292809,13068225998,13502260686,18771178228"; // 指通科
	//String mobile = "18003071077,13927339333,13502258333,13818010927,13138398588,15361402068,18819985353,13542707898,13502277190,13809830131,13316398899"; // 信保中心
	String content = "测试短信1234567890，请勿回复！";
	System.out.println(SMSUtil.sendsms(mobile, content));
    }

}
