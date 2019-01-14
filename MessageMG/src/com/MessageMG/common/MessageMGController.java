package com.MessageMG.common;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.jms.Session;
import javax.json.JsonObject;
import javax.persistence.metamodel.SetAttribute;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.MessageMG.Interceptor.MessageMGIterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
@Before(MessageMGIterceptor.class)
public class MessageMGController extends Controller {
			MessageMGService messageMGService = new MessageMGService();			
			public void index() {
				render("/mHtml/login/login.html");
			}
			public void show() {
			String username = getSessionAttr("user");
			int CurrentPageNumber = getParaToInt(0);
			if(CurrentPageNumber<=0)
			CurrentPageNumber = 1;
			JSONObject datetime =  new JSONObject();
			Page<Record>page = messageMGService.SearchbyData(CurrentPageNumber,1);
			List<Record>list = page.getList();	
			List<Record>aaa = messageMGService.Finduser();
			for (int i = 0; i < list.size(); i++) {
				list.get(i).getColumns().replace("newstime", new SimpleDateFormat("yyyy-MM-dd").format(list.get(i).getColumns().get("newstime")));
				datetime.put("json"+ i, list.get(i).getColumns());
		}
			int allPageNumber = page.getPageNumber();
			setAttr("allPageNumber", allPageNumber);
			setAttr("json", datetime);
			setAttr("page", page);
			setAttr("username", username);
			setAttr("CurrentPageNumber", CurrentPageNumber);
			setAttr("aaa",aaa);
			setAttr("list",list);
			renderTemplate("/mhtml/index1/index1.html");				
	}		
		
			public void exit(){
				setSessionAttr("flag", "false");
				String aString = getSessionAttr("");
				System.out.println();
				renderTemplate("/mhtml/login/login.html");				
		}
			public void add() {
				String username = getPara("username");
				String password = getPara("password");
				messageMGService.AUBUAP(username, password);
				renderText("login succeed!");
			}
			public void deleteUser() {
				String userpower = getSessionAttr("power");
				if(userpower.equals("normal"))
				{	renderHtml("<script>alert(力量不够！！');history.back()</script>");}
				String username = getPara("username");
				messageMGService.DUBYN(username);
				renderText("delete succeed!");
			}
			public void alterPassword() {
				int  userid = getParaToInt("userid");
				String password = getPara("password");
				messageMGService.APBP(password,userid);
			}
			public void searchUser() {
				String username = getPara("username");
			List<Record>list=messageMGService.FUBU(username);
			list.toString();
			String _username = null;
			String createtime = null;
			int departid =0;
			for(int i=0;i<list.size()-1;i++){
				 _username = list.get(i).get("passwrod");
				createtime = list.get(0).get("createtime");
				 departid =list.get(0).get("departid"); 
			}
				setAttr("_username", _username);
				setAttr("createtime", createtime);
				setAttr("departid", departid);
			}
}

				
		

