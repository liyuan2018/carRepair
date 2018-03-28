package com.cys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.cys.common.domain.ResultData;
import com.cys.model.GlobleUtil;
import com.cys.model.MessageDTO;
import com.cys.model.SysUser;
import com.cys.service.ISysUserService;
import com.cys.util.MD5Util;
import com.cys.util.SessionUtils;


/**
 * 登陆初始化控制器
 * 
 * @author 张代浩
 * 
 */
@RestController
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	private ISysUserService sysUserService;

	@RequestMapping(params = "goPwdInit")
	public String goPwdInit() {
		return "login/pwd_init";
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ModelAndView say() throws Exception{
        return new ModelAndView("login");
    }
	

	/**
	 * admin账户密码初始化
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "pwdInit")
	public ModelAndView pwdInit(HttpServletRequest request) {
		ModelAndView modelAndView = null;
		SysUser user = new SysUser();
		user.setAccount("admin");
		String newPwd = MD5Util.md5Password("123456");
		//sysUserService.pwdInit(user, newPwd);
		user.setPassword(newPwd);
		modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
		return modelAndView;
	}

	/**
	 * 检查用户名称
	 * 
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "checkuser")
	@ResponseBody
	public ResultData checkuser(SysUser user, HttpServletRequest req) {
		
		/*String randCode = req.getParameter("randCode");
		// 默认输入验证码
		randCode = String.valueOf(session.getAttribute("randCode"));*/
		/*if (StringUtils.isEmpty(randCode)) {
			j.setMsg("请输入验证码");
			j.setSuccess(false);
		} else if (!randCode.equalsIgnoreCase(String.valueOf(session.getAttribute("randCode")))) {
			// todo "randCode"和验证码servlet中该变量一样，通过统一的系统常量配置比较好，暂时不知道系统常量放在什么地方合适
			j.setMsg("验证码错误！");
			j.setSuccess(false);
		}*/ 
		MessageDTO json =new MessageDTO();
		user.setPassword(MD5Util.md5Password(user.getPassword()));
		SysUser uq= new SysUser();
		uq.setAccount(user.getAccount());
		SysUser uu =  sysUserService.findOne(uq);
		String message ="";
		String status ="fail";
		if (uu == null) {
			message ="账号不存在";
		} else {
			SysUser u = sysUserService.findOne(user);
			if (u == null) {
				message = "用户名或密码错误!";
				
			}else{
				req.getSession().setAttribute(GlobleUtil.SESSION_USER, u);
				message = "登录成功!";
				status = "success";
			}

			json.setMsg(message);
			json.setStatus(status);
			
		}
		return new ResultData(MessageDTO.class, json);
	}


	/*private String getAllRoles(String userId) {
		String roles = "";
		List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", userId);
		for (TSRoleUser ru : rUsers) {
			TSRole role = ru.getTSRole();
			roles += role.getRoleName() + ",";
		}
		if (roles.length() > 0) {
			roles = roles.substring(0, roles.length() - 1);
		}
		return roles;
	}*/

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "login")
	public ModelAndView login(ModelMap modelMap, HttpServletRequest request) {
	
		//ModelAndView modelAndView = null;
		SysUser user = SessionUtils.getCurrentUser();
		System.out.println("--tttt-------" + user);
		String roles = "";
		// 越秀项目登录访问参数设置
		//String loginParam = request.getParameter("loginParam");
		//modelMap.put("loginParam", loginParam);
		if (user != null) {
			//WeixinAccountEntity weixinAccountEntity = weixinAccountService.findLoginWeixinAccount();
			//request.getSession().setAttribute(WeiXinConstants.WEIXIN_ACCOUNT, weixinAccountEntity);

			

			// 根据角色进行判断，不同角色进入不同的主页面
			if ((user.getUserType()!= null && equals(user.getUserType()==1))
					) {
				return new ModelAndView("main/main");
			} else if (user.getUserType()!= null && equals(user.getUserType()==2)) {
				return new ModelAndView("main/index");
			} else {
				return new ModelAndView("main/index");
			}

			// 要添加自己的风格，复制下面三行即可
			// if (StringUtils.isNotEmpty(indexStyle)
			// && indexStyle.equalsIgnoreCase("helpdesk_main")) {
			// return "main/helpdesk_main";
			// }
			// if (StringUtils.isNotEmpty(indexStyle)
			// && indexStyle.equalsIgnoreCase("bootstrap")) {
			// return "main/bootstrap_main";
			// }
			// if (StringUtils.isNotEmpty(indexStyle)
			// && indexStyle.equalsIgnoreCase("shortcut")) {
			// return "main/shortcut_main";
			// }
			// if (StringUtils.isNotEmpty(indexStyle)
			// && indexStyle.equalsIgnoreCase("sliding")) {
			// return "main/sliding_main";
			// }
			// if (StringUtils.isNotEmpty(indexStyle)
			// && indexStyle.equalsIgnoreCase("shamcey")) {
			// return "main/shamcey_main";
			// }
			//
			// return "main/main";
		} else {
			return new ModelAndView("login/newlogin");
		}

	}

	/**
	 * 退出系统
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "logout")
	public ModelAndView logout(HttpServletRequest request) {
		/*HttpSession session = ContextHolderUtils.getSession();
		TSUser user = ResourceUtil.getSessionUserName();
		systemService.addLog("用户" + user.getUserName() + "已退出", Globals.Log_Type_EXIT, Globals.Log_Leavel_INFO);
		ClientManager.getInstance().removeClinet(session.getId());
		ModelAndView modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));*/
		ModelAndView modelAndView = new ModelAndView(new RedirectView("loginController.do?login"));
		request.getSession().removeAttribute(GlobleUtil.SESSION_USER);
		return modelAndView;
	}

	/**
	 * 菜单跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "left")
	public ModelAndView left(HttpServletRequest request) {
		SysUser user = SessionUtils.getCurrentUser();
		HttpSession session = request.getSession();
		ModelAndView modelAndView = new ModelAndView();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute("");
			modelAndView.setView(new RedirectView("loginController.do?login"));
		} else {
			/*List<TSConfig> configs = userService.loadAll(TSConfig.class);
			for (TSConfig tsConfig : configs) {
				request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
			}*/
			modelAndView.setViewName("main/left");
			//request.setAttribute("menuMap", getFunctionMap(user));
		}
		return modelAndView;
	}

	/**
	 * 获取权限的map
	 * 
	 * @param user
	 * @return
	 */
	/*private Map<Integer, List<TSFunction>> getFunctionMap(TSUser user) {
		Map<Integer, List<TSFunction>> functionMap = new HashMap<Integer, List<TSFunction>>();
		Map<String, TSFunction> loginActionlist = getUserFunction(user);
		if (loginActionlist.size() > 0) {
			Collection<TSFunction> allFunctions = loginActionlist.values();
			for (TSFunction function : allFunctions) {
				if (!functionMap.containsKey(function.getFunctionLevel() + 0)) {
					functionMap.put(function.getFunctionLevel() + 0, new ArrayList<TSFunction>());
				}
				functionMap.get(function.getFunctionLevel() + 0).add(function);
			}
			// 菜单栏排序
			Collection<List<TSFunction>> c = functionMap.values();
			for (List<TSFunction> list : c) {
				Collections.sort(list, new NumberComparator());
			}
		}
		return functionMap;
	}*/

	/**
	 * 获取用户菜单列表
	 * 
	 * @param user
	 * @return
	 */
	/*private Map<String, TSFunction> getUserFunction(TSUser user) {
		HttpSession session = ContextHolderUtils.getSession();
		Client client = ClientManager.getInstance().getClient(session.getId());
		if (client.getFunctions() == null || client.getFunctions().size() == 0) {
			Map<String, TSFunction> loginActionlist = new HashMap<String, TSFunction>();
			List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			for (TSRoleUser ru : rUsers) {
				TSRole role = ru.getTSRole();
				List<TSRoleFunction> roleFunctionList = systemService.findByProperty(TSRoleFunction.class, "TSRole.id",
						role.getId());
				for (TSRoleFunction roleFunction : roleFunctionList) {
					TSFunction function = roleFunction.getTSFunction();
					loginActionlist.put(function.getId(), function);
				}
			}
			client.setFunctions(loginActionlist);
		}
		return client.getFunctions();
	}*/

	/**
	 * 首页跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "home")
	public ModelAndView home(HttpServletRequest request) {
		return new ModelAndView("main/home");
	}

	/**
	 * 无权限页面提示跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "noAuth")
	public ModelAndView noAuth(HttpServletRequest request) {
		return new ModelAndView("common/noAuth");
	}

	/**
	 * @Title: top @Description: bootstrap头部菜单请求 @param request @return
	 *         ModelAndView @throws
	 */
	/*@RequestMapping(params = "top")
	public ModelAndView top(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
			return new ModelAndView(new RedirectView("loginController.do?login"));
		}
		request.setAttribute("menuMap", getFunctionMap(user));
		List<TSConfig> configs = userService.loadAll(TSConfig.class);
		for (TSConfig tsConfig : configs) {
			request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
		}
		return new ModelAndView("main/bootstrap_top");
	}*/

	/**
	 * @Title: shamceytop @Description: shamcey头部菜单请求 @param request @return
	 *         ModelAndView @throws
	 */
	/*@RequestMapping(params = "shamceytop")
	public ModelAndView shamceytop(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
			return new ModelAndView(new RedirectView("loginController.do?login"));
		}
		request.setAttribute("menuMap", getFunctionMap(user));
		List<TSConfig> configs = userService.loadAll(TSConfig.class);
		for (TSConfig tsConfig : configs) {
			request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
		}
		return new ModelAndView("main/shamcey_top");
	}*/

	/**
	 * @Title: top @author gaofeng @Description: shortcut头部菜单请求 @param
	 *         request @return ModelAndView @throws
	 */
	/*@RequestMapping(params = "shortcut_top")
	public ModelAndView shortcut_top(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUserName();
		HttpSession session = ContextHolderUtils.getSession();
		// 登陆者的权限
		if (user.getId() == null) {
			session.removeAttribute(Globals.USER_SESSION);
			return new ModelAndView(new RedirectView("loginController.do?login"));
		}
		request.setAttribute("menuMap", getFunctionMap(user));
		List<TSConfig> configs = userService.loadAll(TSConfig.class);
		for (TSConfig tsConfig : configs) {
			request.setAttribute(tsConfig.getCode(), tsConfig.getContents());
		}
		return new ModelAndView("main/shortcut_top");
	}*/

	/**
	 * @Title: top @author:gaofeng @Description:
	 *         shortcut头部菜单一级菜单列表，并将其用ajax传到页面，实现动态控制一级菜单列表 @return
	 *         AjaxJson @throws
	 */
	/*@RequestMapping(params = "primaryMenu")
	@ResponseBody
	public String getPrimaryMenu() {
		List<TSFunction> primaryMenu = getFunctionMap(ResourceUtil.getSessionUserName()).get(0);
		String floor = "";
		for (TSFunction function : primaryMenu) {
			if (function.getFunctionLevel() == 0) {

				if ("Online 开发".equals(function.getFunctionName())) {

					floor += " <li><img class='imag1' src='plug-in/login/images/online.png' /> "
							+ " <img class='imag2' src='plug-in/login/images/online_up.png' style='display: none;' />"
							+ " </li> ";
				} else if ("统计查询".equals(function.getFunctionName())) {

					floor += " <li><img class='imag1' src='plug-in/login/images/guanli.png' /> "
							+ " <img class='imag2' src='plug-in/login/images/guanli_up.png' style='display: none;' />"
							+ " </li> ";
				} else if ("系统管理".equals(function.getFunctionName())) {

					floor += " <li><img class='imag1' src='plug-in/login/images/xtgl.png' /> "
							+ " <img class='imag2' src='plug-in/login/images/xtgl_up.png' style='display: none;' />"
							+ " </li> ";
				} else if ("常用示例".equals(function.getFunctionName())) {

					floor += " <li><img class='imag1' src='plug-in/login/images/cysl.png' /> "
							+ " <img class='imag2' src='plug-in/login/images/cysl_up.png' style='display: none;' />"
							+ " </li> ";
				} else if ("系统监控".equals(function.getFunctionName())) {

					floor += " <li><img class='imag1' src='plug-in/login/images/xtjk.png' /> "
							+ " <img class='imag2' src='plug-in/login/images/xtjk_up.png' style='display: none;' />"
							+ " </li> ";
				} else {
					// 其他的为默认通用的图片模式
					String s = "";
					if (function.getFunctionName().length() >= 5 && function.getFunctionName().length() < 7) {
						s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"
								+ function.getFunctionName() + "</span></div>";
					} else if (function.getFunctionName().length() < 5) {
						s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'>"
								+ function.getFunctionName() + "</div>";
					} else if (function.getFunctionName().length() >= 7) {
						s = "<div style='width:67px;position: absolute;top:40px;text-align:center;color:#909090;font-size:12px;'><span style='letter-spacing:-1px;'>"
								+ function.getFunctionName().substring(0, 6) + "</span></div>";
					}
					floor += " <li style='position: relative;'><img class='imag1' src='plug-in/login/images/default.png' /> "
							+ " <img class='imag2' src='plug-in/login/images/default_up.png' style='display: none;' />"
							+ s + "</li> ";
				}
			}
		}

		return floor;
	}*/

	/**
	 * 返回数据
	 */
	/*@RequestMapping(params = "getPrimaryMenuForWebos")
	@ResponseBody
	public AjaxJson getPrimaryMenuForWebos() {
		AjaxJson j = new AjaxJson();
		// 将菜单加载到Session，用户只在登录的时候加载一次
		Object getPrimaryMenuForWebos = ContextHolderUtils.getSession().getAttribute("getPrimaryMenuForWebos");
		if (oConvertUtils.isNotEmpty(getPrimaryMenuForWebos)) {
			j.setMsg(getPrimaryMenuForWebos.toString());
		} else {
			String PMenu = ListtoMenu.getWebosMenu(getFunctionMap(ResourceUtil.getSessionUserName()));
			ContextHolderUtils.getSession().setAttribute("getPrimaryMenuForWebos", PMenu);
			j.setMsg(PMenu);
		}
		return j;
	}*/

	/**
	 * 根据用户id获取所有权限名称
	 */
	/*public String getAllFunctionsByUserId(String userId) {
		StringBuffer bf = new StringBuffer();
		if (StringUtils.isNotEmpty(userId)) {
			String sql = "SELECT DISTINCT f.functionname AS functionname FROM t_s_function AS f, t_s_role_function AS rf, t_s_role_user AS ru WHERE f.id = rf.functionid AND rf.roleid = ru.roleid AND ru.userid = ?";
			List<Map<String, Object>> fns = systemService.findForJdbc(sql, userId);
			if (fns != null && fns.size() > 0) {
				for (Map<String, Object> fnMap : fns) {
					if (fnMap != null && fnMap.size() > 0) {
						bf.append(fnMap.get("functionname") + ",");
					}
				}
			}
		}
		return bf.toString();
	}*/
}
