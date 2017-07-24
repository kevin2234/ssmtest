package com.bing.ddup.web;

import com.bing.ddup.model.User;
import com.bing.ddup.service.UserService;
import com.bing.framework.base.BaseController;
import com.bing.framework.base.ParamBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	private UserService userService;
	
	@POST
	@RequestMapping("login")
    @ResponseBody
	public Map<String, Object> login(@ModelAttribute User user,
			HttpServletRequest request, Model model) throws Exception{
		Map<String, Object> reMap = new HashMap<String, Object>();
		user = userService.login(user);
		if (user!=null) {
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("powers", user.getPowers());
			reMap.put("url", "index.do");
		} else {
			reMap.put("msg", "用户名或密码不正确！");
		}
		return reMap;
	}
	
	@RequestMapping("logout")
	public String logout(
			HttpServletRequest request, Model model) throws Exception{
		request.getSession().removeAttribute("user");
		return "index";
	}
	
	@RequestMapping("/info")
	public String info(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "mymap", required = false) Map<String,String> mymap,
			HttpServletRequest request, Model model){
		User user = new User();
		if (id!=null) {
			user.setId(id);
			user = userService.infoUser(user);
		}
        System.out.println("user info!");
        model.addAttribute("model", user);
		return "user/infoUser";
	}
	
	@RequestMapping("view")
	public String view(@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request, Model model) throws Exception{
		User user = new User();
		user.setId(id);
		user = userService.infoUser(user);
		model.addAttribute("model", user);
		return "user/viewUser";
	}
	
	@RequestMapping("list")
	public String list(){
		return "user/user-list";
	}
	
	@POST
	@RequestMapping("save")
    @ResponseBody
	public Map<String, Object> save(@ModelAttribute User user,
			HttpServletRequest request, Model model) throws Exception{
		Map<String, Object> reMap = new HashMap<String, Object>();
		
		if (user.getAccount()==null || user.getAccount().equals("")) {
			reMap.put("err", "请输入登录帐号！"); return reMap;
		}
		if (user.getName()==null || user.getName().equals("")) {
			reMap.put("err", "请输入姓名！"); return reMap;
		}
		
		if (user.getId()==null) {
			userService.saveUser(user);
		} else {
			userService.updateUser(user);
		}
		reMap.put("msg", "保存成功！");
		reMap.put("url", "user/list.do");
		reMap.put("id", user.getId());
		return reMap;
	}
	
	@POST
	@RequestMapping("delete")
    @ResponseBody
	public Map<String, Object> delete(@ModelAttribute User user,
			@RequestParam(value = "id", required = true) String id, 
			HttpServletRequest request, Model model) throws Exception{
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (id==null || id.trim()=="") {
			reMap.put("err", "请选择删除的数据项！"); return reMap;
		}
		userService.deleteUser(Integer.parseInt(id));
		reMap.put("msg", "删除成功！");
		return reMap;
	}
	
	@RequestMapping("ajaxList")
    @ResponseBody
	public List<User> ajaxList(@ModelAttribute User user,
			HttpServletRequest request, Model model) throws Exception{
		user = new User();
		return userService.listUser(user);
	}
	
	@RequestMapping("ajaxListPage")
	@ResponseBody
	public Map<String, Object> ajaxListPage(ParamBean paramBean,
			HttpServletRequest request, Model model) throws SQLException{
		Map<String, Object> reMap=new HashMap<String, Object>();//返回json
		Map<String, String> param = paramBean.getParam();
		//参数sortName，sortOrder，pageSize，startCount，keyword
		System.out.println("param : "+param);
		param = pageParam(param, "#id##sum#");
		reMap.put("rows", userService.list(param));
		reMap.put("total", userService.count(param));
		return reMap;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
