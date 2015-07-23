package com.cts.login.dao;


import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.cts.common.domain.Role;
import com.cts.common.domain.User;

public class HibernateSessionManager {

 private static final SessionFactory sessionFactory = buildSessionFactory();

 private static SessionFactory buildSessionFactory() {
 try {
 return new AnnotationConfiguration().
 configure().buildSessionFactory();
 } catch (Throwable ex) {
 System.err.println("SessionFactory creation failed." + ex);
 throw new ExceptionInInitializerError(ex);
 }
 }

 public static SessionFactory getSessionFactory() {
 return sessionFactory;
 }

 public static void shutdown() {
 // Close caches and connection pools
 getSessionFactory().close();
 }
 
 @SuppressWarnings("unchecked")
 public static void main(String[] args) {
	 insertuseralone();
	
 }
 
 public static void selectuser(){
	 SessionFactory sf = HibernateSessionManager.getSessionFactory();
	 Session session = sf.openSession();
     session.beginTransaction();
     
    // User u=(User)session.load(User.class, new Integer(1));
     String hql = "FROM com.cts.common.domain.User u where u.username=:username";
     Query query = session.createQuery(hql);
     query.setParameter("username", "rajendra");
     //System.out.println(u.getRole().getRoleName());
     System.out.println(((User)(query.list().get(0))).getRole().getRoleDesc());
 }
 
 public static void insertuseralone(){
	 SessionFactory sf = HibernateSessionManager.getSessionFactory();
     Session session = sf.openSession();
     session.beginTransaction();
     
     String hql = "FROM com.cts.common.domain.Role r where r.roleName=:rolename";
     Query query = session.createQuery(hql);
     query.setParameter("rolename", "Associate");
     Role role=(Role)(query.list().get(0));
     
     String uhql = "FROM com.cts.common.domain.User u where u.userName=:username";
     Query uquery = session.createQuery(uhql);
     uquery.setParameter("username", "rajendra");
     User approver=(User)(uquery.list().get(0));
     
     User	user1 = new User();
     user1.setUserName("priya");
     user1.setCreatedBy("svs");
     user1.setCreatedDate(new Date());
     user1.setEmployeeId(222015);
     user1.setApprover(approver);
     User	user2 = new User();
     user2.setUserName("rex");
     user2.setCreatedBy("svs");
     user2.setCreatedDate(new Date());
     user2.setEmployeeId(302015);
     user2.setApprover(approver);

     User	user3 = new User();
     user3.setUserName("subhra");
     user3.setCreatedBy("svs");
     user3.setCreatedDate(new Date());
     user3.setEmployeeId(392015);
     user3.setApprover(approver);
     User	user4 = new User();
     user4.setUserName("anil");
     user4.setCreatedBy("svs");
     user4.setCreatedDate(new Date());
     user4.setEmployeeId(232015);
     user4.setApprover(approver);

     
    user1.setRole(role);
    user2.setRole(role);
    user3.setRole(role);
    user4.setRole(role);
      
     session.save(user1);
     session.save(user2);
     session.save(user3);
     session.save(user4);

     session.getTransaction().commit();
     session.close();

     
     
     
 }
 
 public static void insertuser(){
     SessionFactory sf = HibernateSessionManager.getSessionFactory();
     Session session = sf.openSession();
     session.beginTransaction();

     
     Role role=new Role();
     //role.setDescription("it will be less M grade");
     //role.setRolename("Associate");
     role.setRoleDesc("will be less M grade");
     role.setRoleName("Associate");
     session.save(role);
     Role mrole=new Role();
     mrole.setRoleDesc("will be above M grade");
     mrole.setRoleName("TrackAdmin");
     session.save(mrole);
      
     User approver=new User();
      approver.setUserName("sathya");
      approver.setCreatedBy("desh1");
      approver.setCreatedDate(new Date());
      approver.setEmployeeId(192015);
      approver.setRole(mrole);
     // approver.setRole(role);
      approver.setApprover(null);
      session.save(approver);
      
      
     User	user1 = new User();
     user1.setUserName("sudar");
     user1.setCreatedBy("svs");
     user1.setCreatedDate(new Date());
     user1.setEmployeeId(292015);
     user1.setApprover(approver);
     User	user2 = new User();
     user2.setUserName("baskar");
     user2.setCreatedBy("svs");
     user2.setCreatedDate(new Date());
     user2.setEmployeeId(402015);
     user2.setApprover(approver);

     User	user3 = new User();
     user3.setUserName("divya");
     user3.setCreatedBy("svs");
     user3.setCreatedDate(new Date());
     user3.setEmployeeId(272015);
     user3.setApprover(approver);
     User	user4 = new User();
     user4.setUserName("mohamed");
     user4.setCreatedBy("svs");
     user4.setCreatedDate(new Date());
     user4.setEmployeeId(262015);
     user4.setApprover(approver);

     
    user1.setRole(role);
    user2.setRole(role);
     
      
     session.save(user1);
     session.save(user2);

     session.getTransaction().commit();
     session.close();

 }

}