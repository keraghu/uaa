package com.cts.login.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.cts.common.domain.Role;
import com.cts.login.dao.UserDao;
import com.cts.login.dao.UserDaoImpl;
import com.cts.login.services.helper.LdapHelper;

@Path("/login")
public class LoginService {
	
	final static Logger logger = Logger.getLogger(LoginService.class);
	
	@GET
    @Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject GetUser( @QueryParam("userName")  String userName,@QueryParam("password")  String password){
//		JSONObject jo = null;
//		String name="";
//		try{
//			Object obj=JSONValue.parse(userName);
//			JSONArray array=(JSONArray)obj;
//			 jo=(JSONObject)array.get(1);
//			name=(String)jo.get("1");
//		}catch (Exception e)
//	     {
//			e.printStackTrace();
//		}
		LdapHelper ldapHelper=new LdapHelper();
		JSONObject obj=new JSONObject();
		if(logger.isDebugEnabled()){
		    logger.debug("input received  Username "+userName);
		    logger.debug("input received password "+password);
		}
		System.out.println("input req from "+userName);
		System.out.println("input req from "+password);
		UserDao userdao=new UserDaoImpl();
		boolean flag=ldapHelper.isValidUser(userName, password);
		System.out.println("ldap "+flag);
		if(flag)
		//if(true)
		{
			System.out.println("inside valid user");
			Role r=userdao.getUserRoles(userName);

			obj.put("success", true);
			obj.put("Role", r.getRoleName());
		}
		else{
			System.out.println("inside valid falsei");
			obj.put("success", false);

		}
		//String res="{success:true";
		return obj;
	}
	
	

}
