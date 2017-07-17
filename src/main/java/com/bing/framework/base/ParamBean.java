package com.bing.framework.base;

import java.util.List;
import java.util.Map;

public class ParamBean {
	private Map<String, String> param;
	private List<Map<String, String>> list;

	public Map<String, String> getParam() {
		return param;
	}
	public void setParam(Map<String, String> param) {
		this.param = param;
	}
	public List<Map<String, String>> getList() {
		return list;
	}
	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}
}
