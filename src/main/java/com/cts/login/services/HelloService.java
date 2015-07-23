package com.cts.login.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.cts.common.domain.Role;
import com.cts.login.dao.UserDao;
import com.cts.login.dao.UserDaoImpl;
import com.cts.login.services.helper.LdapHelper;

@Path("/message")
public class HelloService {
	
/*	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) {
 
		String result = "Restful example : " + msg;
 
		return Response.status(200).entity(result).build();
 
	}*/
	
	@GET
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
		System.out.println("input req from "+userName);
		UserDao userdao=new UserDaoImpl();
		//if(ldapHelper.isValidUser(userName, password))
		if(true)
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
