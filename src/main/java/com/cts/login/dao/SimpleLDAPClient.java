package com.cts.login.dao;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class SimpleLDAPClient {
    public static void main(String[] args) {
        Hashtable env = new Hashtable();

        env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.251.67.117:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        //env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        env.put(Context.SECURITY_PRINCIPAL, "cn=user1,ou=openshift,dc=ad,dc=privatepaas,dc=com");
        env.put(Context.SECURITY_CREDENTIALS, "Passw0rd");
        DirContext ctx = null;
        NamingEnumeration results = null;
        try {
            ctx = new InitialDirContext(env);
            System.out.println("user is valid");
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            results = ctx.search("", "(objectclass=person)", controls);
            while (results.hasMore()) {
                SearchResult searchResult = (SearchResult) results.next();
                Attributes attributes = searchResult.getAttributes();
                Attribute attr = attributes.get("cn");
                String cn = (String) attr.get();
                System.out.println(" Person Common Name = " + cn);
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        } finally {
            if (results != null) {
                try {
                    results.close();
                } catch (Exception e) {
                }
            }
            if (ctx != null) {
                try {
                    ctx.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
