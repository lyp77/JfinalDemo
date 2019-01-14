package com.MessageMG.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.registry.SaveException;

import com.alibaba.druid.stat.TableStat.Name;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.mysql.fabric.xmlrpc.base.Data;

public class MessageMGService{
	
			public int  login(String username,String password) {
				List<Record> list = Db.find("select * from mage_user where username=? and password=?",username,password);
				if(list.size()>0){
					return 1;
				}else{
					return 0;
				}
			}
			public List<Record> Finduser(){
				return Db.find("SELECT username FROM mage_user");
				
			}
			public Page<Record> SearchbyData(int PageNumber,Integer id) {
				return Db.paginate(PageNumber,10,"select *","from mage_message where userid >=?",id);
			}
			public String FUBNAP(String username,String password) {
				List<Record>list = Db.find("select * from mage_user where username=? and password =?",username,password);		
				String aString=  list.get(0).get("power").toString();
				return aString;
				
			}
			public void AUBUAP(String username,String password) {
			SimpleDateFormat data =new SimpleDateFormat("yy-MM-dd");
			Record record = new Record().set("username", username).set("password", password).set("power", "normal").set("createtime",data.format(new Date()));	
			Db.save("mage_user", record);
			}
			public void DUBYN(String username) {
				Db.delete("DELETE  from mage_user where username = ?", username);
			}
			public void APBP(String password,Integer userid) {
				// TODO Auto-generated method stub
					Record	record = new Record();
					record = Db.findById("userid", userid).set("password",password);
				Db.update("mage_user", record);
			}
			public int FIBN(String username) {
				// TODO Auto-generated method stub
				List<Record>list = Db.find("select userid from mage_user where username = ?",username);
				list.toString();
				int userid = list.get(0).getInt("userid");
				return userid;
			}
			public List<Record> FUBU(String username) {
				// TODO Auto-generated method stub
				return Db.find("select * from mage_user where username = ?",username);
			}
		
}
	