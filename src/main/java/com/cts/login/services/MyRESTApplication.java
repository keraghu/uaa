package com.cts.login.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class MyRESTApplication extends Application{
	
	private Set<Object> singletons=new HashSet<Object>();
	private Set<Class<?>> empty=new HashSet<Class<?>>();
	
	public MyRESTApplication(){
		singletons.add(new LoginService());
	}

	@Override
	public Set<Class<?>> getClasses(){
		return empty;
	}
	
	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
	
	

}
