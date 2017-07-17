package com.bing.framework.base;

import java.util.Map;

public class BaseController {

	/**
	 * 获取分页参数
	 * @param param，sortName、sortOrder、pageSize、pageNo
	 * @param numFields，类似"#id##sum#"
	 * @return
	 */
	public Map<String, String> pageParam(Map<String, String> param, String numFields){
		String sortName = param.get("sortName");
		//判断按数字排序还是按字符排序
		if (sortName!=null) {
			String sortNameCase = "#"+sortName+"#";
			if (numFields.indexOf(sortNameCase) < 0) {
				param.put("sortStr", "1");
			}else{
				param.put("sortNum", "1");
			}
		}
		//获取startCount
		if (!param.get("pageSize").equals("全部")) {
			int pageNo = Integer.parseInt(param.get("pageNo"));//当前页码
			int pageSize = Integer.parseInt(param.get("pageSize"));//每页条数
			int startCount = (pageNo-1)*pageSize;//开始查找的记录
			param.put("startCount", startCount+"");
		}
		return param;
	}
}
