package com.cys.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cys.common.annotation.Rest;
import com.cys.common.domain.Query;
import com.cys.common.domain.ResultData;
import com.cys.dto.SysUserDTO;
import com.cys.dto.YuyueOrderDTO;
import com.cys.model.CarInfo;
import com.cys.model.SysUser;
import com.cys.model.SysYuyue;
import com.cys.model.YuyueOrder;
import com.cys.service.ICarInfoService;
import com.cys.service.ISysUserService;
import com.cys.service.ISysYuyueService;
import com.cys.service.YuyueOrderService;
import com.cys.util.DateUtils;
import com.cys.util.SessionUtils;

/**
 * Created by liyuan on 2018/1/24.
 */
@ComponentScan
@RestController
@RequestMapping("/admin/yuyue")
public class AdminYuyueController {

	@Autowired
	private ICarInfoService carInfoService;
	
	@Autowired
	private YuyueOrderService yuyueOrderService;
	@Autowired
    private ISysUserService sysUserService;
	
	@Autowired
    private ISysYuyueService sysYuyueService;
	
	 @RequestMapping(value = "index", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ModelAndView index() throws Exception{
	        return new ModelAndView("index");
	    }
	    @RequestMapping(value = "table", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ModelAndView main() throws Exception{
	        return new ModelAndView("yuyueList");
	    }
	    
	   
	    
	    /**
	     * 查询详情
	     * @param id
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ResultData findDetail(@PathVariable("id") String id) throws Exception {
	        SysUserDTO sysUserDTO = sysUserService.findDtoById(id);
	        return new ResultData(SysUserDTO.class, sysUserDTO);
	    }
	    
	   
	    
	    @RequestMapping(value = "getCarByByNum", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public ResultData getCarByByNum(String carNum) throws Exception {
	    	CarInfo carInfo1 =new CarInfo();
	    	CarInfo carInfo =new CarInfo();
	    	carInfo.setCarNum(carNum);
	    	List<CarInfo> li =carInfoService.find(carInfo);
	    	if(li !=null&&li.size()>0){
	    		carInfo1 = li.get(0);
	    	}
	    	return new ResultData(CarInfo.class, carInfo1);
	    }
	    
	    //deleteUser
	    /**
	     * 确认已完成
	     * @param id
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "/save", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public void save(@RequestBody YuyueOrderDTO yuyueOrderDTO ) throws Exception {
	        
	    	sysYuyueService.saveYuyueOrder(yuyueOrderDTO); 
	       
	        
	    }
	    
	    @RequestMapping(value = "user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	    public void find(Query query,  SysYuyue sysYuyue) throws Exception {
	    	
	    	//query.getPageable().s;
	        //SysUser sysUser= (SysUser) query.getBean(SysUser.class);
	    	SysUser uu = SessionUtils.getCurrentUser();
	    	sysYuyue.setShopId(uu.getShopId());
	        Page<SysYuyue> pageList = sysYuyueService.find(sysYuyue, query);
	        //sysUserService.
	        JSONArray arr = new JSONArray();
	        for(int i=0;i<pageList.getContent().size();i++){
	        	SysYuyue u=pageList.getContent().get(i);
	        	JSONObject o = new JSONObject();
	        	o.put("id", u.getId());
	        	o.put("type", SysYuyue.getYuyueDesc(u.getYyType()));
	        	if(u.getYyQyUser()!=null){
	        		o.put("qxName", u.getYyQyUser().getName());
	        	}else{
	        		o.put("qxName", "");
	        	}
	        	
	        	if(u.getYyCzUser()!=null){
	        		o.put("czName", u.getYyCzUser().getName());
	        		o.put("mobile", u.getYyCzUser().getMobile());
	        	}else{
	        		o.put("czName", "");
	        		o.put("mobile", "");
	        	}
	        	
	        	if(u.getCarNum() !=null){
	        		o.put("carNum", u.getCarNum());
	        	}else{
	        		o.put("carNum", "");
	        	}
	        	if(u.getYyDate()!=null){
	        		o.put("yyTime", DateUtils.format(u.getYyDate(), "yyyy-MM-DD"));
	        		if(u.getYyTime() != null){
	        			String VB="";
	        			if(u.getYyTime().equalsIgnoreCase("1")){
	        				VB="上午";
	        			}else if(u.getYyTime().equalsIgnoreCase("2")){
	        				VB="下午";
	        			}
	        			o.put("yyTime",o.get("yyTime")+":"+VB);
	        		}
	        	}else{
	        		o.put("yyTime", "");
	        	}
	        	
	           
	        	
	        	o.put("status", SysYuyue.getStatusDesc(u.getStatus()));
	        	arr.add(o);
	        }
	        String json = "{\"total\":" + pageList.getTotalElements() + ",\"rows\":" + arr.toString() + "}";  
	        System.out.println(json);
	        SessionUtils.writer(json);
	    }
	    
	    

}
