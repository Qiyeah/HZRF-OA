package com.oa.controller.kit;

import java.util.HashMap;
import java.util.Map;

/**
 接收保存消息

 {
    createTime : '1348831860'
    ,msgType : 'save'

    ,modelName : 'model名'
    ,modelProperty ：{
        name : 'abc'
        ,code : 'def'
    }
 }
 */
public class InSaveMsg extends InMsg {

	private String modelName;   // 要保存的Model名
	public  Map<String,String> modelProperty = new HashMap<String,String>(); // Model属性
	public  Map<String,Integer> modelPropertyint = new HashMap<String,Integer>(); // Model属性

    public InSaveMsg() {
    }

    public InSaveMsg(Integer createTime, String msgType) {
        super(createTime, msgType);
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Map<String, String> getModelProperty() {
        return modelProperty;
    }

    public void setModelProperty(Map<String, String> modelProperty) {
        this.modelProperty = modelProperty;
    }
}




