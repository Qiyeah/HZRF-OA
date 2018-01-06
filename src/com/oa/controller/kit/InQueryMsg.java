package com.oa.controller.kit;

import java.util.Map;

/**
 * 接收查询消息 { createTime : '1348831860' ,msgType : 'query' ,queryName : '查询方法名'
 * ,queryPara ：{ name : 'abc' ,code : 'def' } }
 */
public class InQueryMsg extends InMsg {

    private String queryName; // 查询方法名

    private Map<String, String> queryPara; // 查询方法名

    public InQueryMsg() {
    }

    public InQueryMsg(Integer createTime, String msgType) {
	super(createTime, msgType);
    }

    public InQueryMsg(Integer createTime, String msgType, String key, int id) {
	super(createTime, msgType, key, id);
    }

    public InQueryMsg(Integer createTime, String msgType, String key) {
	super(createTime, msgType, key);
    }

    public String getQueryName() {
	return queryName;
    }

    public void setQueryName(String queryName) {
	this.queryName = queryName;
    }

    public Map<String, String> getQueryPara() {
	return queryPara;
    }

    public void setQueryPara(Map<String, String> queryPara) {
	this.queryPara = queryPara;
    }
}
