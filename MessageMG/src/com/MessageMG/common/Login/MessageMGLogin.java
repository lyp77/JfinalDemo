package com.MessageMG.common.Login;

import java.awt.List;

import com.MessageMG.common.MessageMGService;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

public class MessageMGLogin extends Controller{
	MessageMGService messageMGService = new MessageMGService();

	public void index() {
		//setSessionAttr("flag", "false");	
		render("/mHtml/login/login.html");	
	} 
	
	public void login() {
		setSessionAttr("flag", "false");
		String flag = getSessionAttr("flag");
		String username = getPara("username");
		String password  = getPara("password");
		String a = "";
		int aList = messageMGService.login(username, password);
		if(aList >0){
		a = messageMGService.FUBNAP(username, password);
		}
		if(aList ==1 && a!=""){	
			int userid = messageMGService.FIBN(username);
			System.out.println("");
			setSessionAttr("flag", "true");
			setSessionAttr("power",a);
			setSessionAttr("username", username);
			setSessionAttr("userid", userid);
			redirect("/show/"+1);						
		}else{
			System.out.println("false");
			renderHtml("<script>alert('滚啊 盗号狗！');history.back()</script>");
		}
	}
}
