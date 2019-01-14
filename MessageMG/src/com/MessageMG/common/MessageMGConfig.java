package com.MessageMG.common;

import com.MessageMG.common.Login.MessageMGLogin;
import com.MessageMG.common.Model.MageDepartment;
import com.MessageMG.common.Model.MageMessage;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;

public class MessageMGConfig extends JFinalConfig {
	public static String  relativePath = "upload/images/";
	private static String absolutePath = PathKit.getWebRootPath() + "/"+relativePath;
	
	public static void main(String[] args) {			
		JFinal.start("WebRoot",8080,"/",5);
}

	@Override
	public void configConstant(Constants me) {		
		PropKit.use("config.properties");
		me.setDevMode(PropKit.getBoolean("dexMode",false));
		me.setDevMode(true);
		me.setBaseUploadPath("upload");
		me.setViewType(ViewType.JSP);
		PropKit.use("config.properties");
	}

	@Override
	public void configRoute(Routes me) {
			me.add("/",MessageMGController.class);	
			me.add("/test",MessageMGLogin.class);
	}

	@Override
	public void configEngine(Engine me) {
		me.setDevMode(true);

	}
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"),
				PropKit.get("user"), PropKit.get("password").trim());
	}
	@Override
	public void configPlugin(Plugins me) {
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		me.add(arp);
		arp.addMapping("mage_user",MageDepartment.class);
		arp.addMapping("mage_department", MageDepartment.class);
		arp.addMapping("mage_message", MageMessage.class);
	}

	@Override
	public void configInterceptor(Interceptors me) {}

	@Override
	public void configHandler(Handlers me) {}


	


}
