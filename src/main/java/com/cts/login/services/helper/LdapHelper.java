package com.cts.login.services.helper;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.log4j.Logger;

import com.cts.login.services.LoginService;

public class LdapHelper {
	final static Logger logger = Logger.getLogger(LdapHelper.class);
	
	public boolean isValidUser(String userName,String password){
		Hashtable env = new Hashtable();
		System.out.println("entering ldap context");
        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        //env.put(Context.PROVIDER_URL, "ldap://54.208.101.241:389");
        env.put(Context.PROVIDER_URL, "ldap://192.168.40.175:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        //CN=sudarsanan,OU=openshift,DC=testad,DC=privatepaas,DC=com
        //final String FQUserName = "CN="+userName.trim()+",OU=openshift,DC=testad,DC=privatepaas,DC=com";
        final String FQUserName ="uid="+userName.trim()+",OU=users,ou=system";
        System.out.println("UserDN "+FQUserName);
        System.out.println("password "+password);
        env.put(Context.SECURITY_PRINCIPAL, FQUserName);
        env.put(Context.SECURITY_CREDENTIALS, password);
        DirContext ctx = null;
        NamingEnumeration results = null;
        boolean isvalid=false;
        try {
        	System.out.println("creating ldap context");
            ctx = new InitialDirContext(env);
            isvalid=true;
        } catch (NamingException e) {
        	logger.error("This is error : " + e);
        	System.out.println(" ldap context"+e.getMessage());
        	isvalid=false;
        	e.printStackTrace();
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception e) {
                	
                	e.printStackTrace();
                }
            }
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                	e.printStackTrace();
                }
            }
        }
        
        
        return isvalid;

	}
}
