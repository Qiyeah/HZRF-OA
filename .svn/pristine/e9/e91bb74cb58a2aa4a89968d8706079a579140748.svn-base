package com.base.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

public class BaseController extends Controller {
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void toBJUIJson(Integer statusCode, String message, String navTabId, String dialogId, String divId, String closeCurrent, String forward) {
		Map jsonMap = new HashMap();
		jsonMap.put("statusCode", statusCode);
		if (StrKit.notBlank(message))
			jsonMap.put("message", message);
		if (StrKit.notBlank(navTabId))
			jsonMap.put("tabid", navTabId);
		if (StrKit.notBlank(dialogId))
			jsonMap.put("dialogid", dialogId);
		if (StrKit.notBlank(divId))
			jsonMap.put("divid", divId);
		if (StrKit.notBlank(closeCurrent))
			jsonMap.put("closeCurrent", closeCurrent);
		if (StrKit.notBlank(forward))
			jsonMap.put("forward", forward);
		renderJson(jsonMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void toErrorJson(String message) {
		Map jsonMap = new HashMap();
		jsonMap.put("statusCode", 300);
		if (message != null)
			jsonMap.put("message", message);
		renderJson(jsonMap);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// divInfoList 格式 "div1Id:url1;div2Id:url2;div3Id:url3..."
	public void toRefreshDivJson(Integer statusCode, String message, String closeCurrent, String divInfoList) {
		Map jsonMap = new HashMap();

		jsonMap.put("statusCode", statusCode);
		if (message != null)
			jsonMap.put("message", message);
		jsonMap.put("tabid", "");
		jsonMap.put("dialogid", "");
		jsonMap.put("divid", "");
		jsonMap.put("datagrid", "");
		if (closeCurrent != null)
			jsonMap.put("closeCurrent", closeCurrent);
		if (divInfoList != null)
			jsonMap.put("divInfoList", divInfoList);
		renderJson(jsonMap);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void toUploadJson(Integer statusCode,String message,String filename,String refreshedDivId) {
		Map jsonMap = new HashMap();
		jsonMap.put("statusCode", statusCode);
		if (message != null)
			jsonMap.put("message", message);
		if (filename != null)
			jsonMap.put("filename", filename);
		if (refreshedDivId != null)
			jsonMap.put("refreshedDivId", refreshedDivId);
		renderJson(jsonMap);
	}
	


	public boolean complexSearch(Integer simpleH, Integer complexH) {
		boolean simple = getParaToBoolean("simple") == null ? true : getParaToBoolean("simple").booleanValue();
		setAttr("layoutH", simple ? simpleH : complexH);
		setAttr("searchText", simple ? "高级查询" : "简单查询");
		setAttr("simple", Boolean.valueOf(simple));
		return simple;
	}

	/**
	 * by zh_new Get para for search for keyword
	 */
	public Map<String, String> getSearchPara(String keyWord) {
		String searchKey = null;
		String searchVal = null;
		Map<String, String[]> paras = getParaMap();
		Map<String, String> search = new HashMap<String, String>();
		Iterator<Entry<String, String[]>> iter = paras.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String[]> entry = iter.next();
			String key = entry.getKey();
			String[] val = entry.getValue();
			if (key != null && !"".equals(key) && key.contains(keyWord) && key.contains(".")) {
				if (val.length > 0) {
					searchKey = (key.split("\\."))[1];
					searchVal = val[0];
					if (searchVal != null && !"".equals(searchVal)) {
						search.put(searchKey, searchVal);
					}
				}
			}
		}
		return search;
	}

	/**
	 * by zh_new Get para for search for deault keyword
	 */
	public Map<String, String> getSearchPara() {
		String keyWord = "Search";
		String searchKey = null;
		String searchVal = null;
		Map<String, String[]> paras = getParaMap();
		Map<String, String> search = new HashMap<String, String>();
		Iterator<Entry<String, String[]>> iter = paras.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String[]> entry = iter.next();
			String key = entry.getKey();
			String[] val = entry.getValue();
			if (key != null && !"".equals(key) && key.contains(keyWord) && key.contains(".")) {
				if (val.length > 0) {
					searchKey = (key.split("\\."))[1];
					searchVal = val[0];
					if (searchVal != null && !"".equals(searchVal)) {
						search.put(searchKey, searchVal);
					}
				}
			}
		}
		return search;
	}
	
	

}