package com.MessageMG.common.Model;

import javax.sql.DataSource;

import com.MessageMG.common.MessageMGConfig;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

public class MessageMGGenerrator {
	private static  DataSource dataSource(){
		PropKit.use("config.properties");
		DruidPlugin druidPlugin = MessageMGConfig.createDruidPlugin();
		druidPlugin.start();
		return druidPlugin.getDataSource();
	}
	public static void main(String[] args) {
		String baseModelPkg = "com.MessageMG.common.basemodel";
	// base model 文件保存路径
	String baseModelDir ="D:/workspace/MessageMG/src/com/MessageMG/common/basemodel";
	// model 所使用的包名
	String modelPkg = "com.MessageMG.common.Model";
	// model 文件保存路径
	String modelDir = "D:/workspace/MessageMG/src/com/MessageMG/common/Model";
	Generator gernerator = new Generator(dataSource(), baseModelPkg, baseModelDir, modelPkg, modelDir);
	gernerator.setGenerateChainSetter(false);
	// 添加不需要生成的表名
	gernerator.addExcludedTable("adv");
	// 设置是否在 Model 中生成 dao 对象
	gernerator.setGenerateDaoInModel(true);
	// 设置是否生成链式 setter 方法
	gernerator.setGenerateChainSetter(true);
	// 设置是否生成字典文件
	gernerator.setGenerateDataDictionary(false);
	// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
	gernerator.setRemovedTableNamePrefixes("t_");
	gernerator.generate();
		
	}
	
}
