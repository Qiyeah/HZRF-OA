package com.oa.controller.json;

/**
 * 访问令牌
 * @Author Andersen
 * mail: yawen199@163.com
 * Date: 2016-9-7 11:01
 */
public class AccessToken {
    private String accessToken;	// 正确获取到 access_token 时有值
    private Integer expiresIn;		// 正确获取到 access_token 时有值,有效时长，单位为秒
    private String key;		        // 正确获取到 access_token 时有值,加解密key
    private Integer errCode;		// 出错时有值
    private String errMsg;			// 出错时有值

    public AccessToken() {
    }

    public AccessToken(String accessToken, Integer expiresIn, Integer errCode, String errMsg) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
