package com.cts.login.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.cts.common.domain.Role;
import com.cts.common.domain.User;

public class UserDaoImpl implements UserDao {
//	private LdapTemplate ldapTemplate;

	public Role getUserRoles(String userName) {
		 SessionFactory sf = HibernateSessionManager.getSessionFactory();
		 Session session = sf.openSession();
	     session.beginTransaction();
	     String hql = "FROM com.cts.common.domain.User u where u.userName=:username";
	     Query query = session.createQuery(hql);
	     query.setParameter("username", userName);
	     System.out.println(userName);
	     User u=null;
	     if(query.list().size()>0)
	    	 u= ((User)(query.list().get(0)));
	     else
	    	  u=new User();
	     
		return u.getRole();
	}
	public boolean isValidUser(String username,String password) {
		return true;
	}
	
//	public boolean isValidUser(String username,String password) {
//		 AndFilter filter = new AndFilter();
//		 System.out.println("inside isValidUser");
//		 ldapTemplate.setIgnorePartialResultException(true);
//		 filter.and(new EqualsFilter("userPrincipalName", "John Fryer"));
//
//		 return  ldapTemplate.authenticate("cn=John Fryer,ou=people,o=ccsCts", filter.toString(), "");
//
//		 //return  ldapTemplate.authenticate(base, filter, password);
//
//	    }

//	public LdapTemplate getLdapTemplate() {
//		return ldapTemplate;
//	}
//
//	public void setLdapTemplate(LdapTemplate ldapTemplate) {
//		this.ldapTemplate = ldapTemplate;
//	}
//	
	


}
