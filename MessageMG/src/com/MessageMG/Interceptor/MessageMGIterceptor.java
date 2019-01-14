package com.MessageMG.Interceptor;

import javax.servlet.http.HttpSession;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.InterceptorStack;
import com.jfinal.aop.Invocation;

public class MessageMGIterceptor implements Interceptor{


	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		HttpSession httpSession = inv.getController().getSession();
		if(httpSession==null){					
		inv.getController().renderTemplate("/mhtml/login/login.html");
		}else{
			String aString=	(String)httpSession.getAttribute("flag");		
			System.out.println("----------flag:"+aString);
			if(aString ==null){
				inv.getController().renderTemplate("/mhtml/login/login.html");;
			}else if(aString.equals("true")){				
				inv.invoke();				
			}else{
				inv.getController().renderTemplate("/mhtml/login/login.html");
			}}
		}
	}
			

