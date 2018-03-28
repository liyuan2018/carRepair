package com.cys.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cys.common.domain.Query;
import com.cys.common.domain.ResultData;
import com.cys.dto.SysUserDTO;
import com.cys.model.SysUser;
import com.cys.service.ISysUserService;
import com.cys.util.DateUtils;
import com.cys.util.SessionUtils;

/**
 * Created by liyuan on 2018/1/24.
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {

	@Autowired
    private ISysUserService sysUserService;
	
	 @RequestMapping(value = "index", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ModelAndView index() throws Exception{
	        return new ModelAndView("index");
	    }
	    @RequestMapping(value = "table", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ModelAndView main() throws Exception{
	        return new ModelAndView("table");
	    }
	    
	    @RequestMapping(value = "usertest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    private void usertest(HttpServletRequest request,  
	            HttpServletResponse response) throws IOException {  
	        response.setCharacterEncoding("utf-8");  
	        PrintWriter pw = response.getWriter();  
	          
	        //得到客户端传递的页码和每页记录数，并转换成int类型  
	       /* int pageSize = Integer.parseInt(request.getParameter("pageSize"));  
	        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));  
	        String orderNum = request.getParameter("orderNum");  */
	          
	        //分页查找商品销售记录，需判断是否有带查询条件  
	        /*List<SimsSellRecord> sellRecordList = null;  
	        sellRecordList = sellRecordService.querySellRecordByPage(pageNumber, pageSize, orderNum);  
	        */ 
	        JSONArray arr = new JSONArray();
	        JSONObject O1 =new JSONObject();
	        O1.put("name", "123");
	        O1.put("moble", "1232");
	        O1.put("canYuyue", "1232");
	        
	        
	        JSONObject O2 =new JSONObject();
	        O2.put("name", "123T");
	        O2.put("moble", "1232T");
	        O2.put("canYuyue", "1232T");
	        arr.add(O1);
	        arr.add(O2);
	        //将商品销售记录转换成json字符串  
	        //String sellRecordJson = sellRecordService.getSellRecordJson(sellRecordList);  
	        //得到总记录数  
	        int total = 2;  
	          
	        //需要返回的数据有总记录数和行数据  
	        String json = "{\"total\":" + total + ",\"rows\":" + arr.toString() + "}";  
	        pw.print(json);  
	    }  
	    
	    
	    @RequestMapping(value = "user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public void find(Query query,SysUser sysUser) throws Exception {
	    	
	    	//query.getPageable().s;
	        //SysUser sysUser= (SysUser) query.getBean(SysUser.class);

	        Page<SysUser> pageList = sysUserService.adminFind(sysUser, query);
	        //sysUserService.
	        JSONArray arr = new JSONArray();
	        for(int i=0;i<pageList.getContent().size();i++){
	        	SysUser u=pageList.getContent().get(i);
	        	JSONObject o = new JSONObject();
	        	o.put("id", u.getId());
	        	o.put("name", u.getName());
	        	o.put("mobile", u.getMobile());
	        	o.put("createTime", DateUtils.format(u.getCreatorTime(), "yyyy-MM-DD"));
	            String kyy ="可预约";
	            if(u.getCanYuyue()=="0"){
	            	kyy ="不可预约";
	            }
	        	o.put("canYuyue", kyy);
	        	arr.add(o);
	        }
	        String json = "{\"total\":" + pageList.getTotalElements() + ",\"rows\":" + arr.toString() + "}";  
	        System.out.println(json);
	        SessionUtils.writer(json);
	    }
	    
	    

}
