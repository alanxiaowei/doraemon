package com.mxw.doraemon;


import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: crypto-ex-quantitation
 * @description: 自动生成代码
 * @author: AlanMa
 * @create: 2019-07-10 18:11
 */
public class MysqlGenerator {

	static final String MODULE_NAME ="quantitation";
	// static final String[] tables ={"ACCOUNT","ACCOUNT_SNAPSHOT","API_KEY","COMMON_PARAM","CURRENCY","ORDER","SYMBOL"};
	static final String[] tables ={"SYMBOL"};
	static final String OUT_PUT_DIR = "/quantitation-common/src/main/java/";
	static final String AUTHOR ="Alan Ma";
	static final String DB_URL = "jdbc:mysql://localhost:3306/QUATITATION?useUnicode=true&useSSL=false&characterEncoding=utf8";
	static final String USER_NAME ="root";
	static final String PASSWORD ="password";
	static final String PARENT_PACKAGE = "com.mxw.quantitation.common.db";
	static final String SUPER_ENTITY_CLASS="com.mxw.quantitation.common.db.store.BaseEntity";
	static final String SUPER_CONTROLLER_CLASS = "com.mxw.quantitation.common.db.store.BaseController";
	static final String MAPPER_PATH = "/quantitation-common/src/main/resources/mapper/";
	static final String[] SUPER_ENTITY_COLUME = {"ID","CREATE_TIME","UPDATED_TIME","VERSION"};


	// static final String MODULE_NAME = "ex";
	// static final String[] tables = {"orders"};
	// static final String OUT_PUT_DIR = "/quantitation-common/src/main/java/";
	// static final String AUTHOR = "Alan Ma";
	// static final String DB_URL = "jdbc:mysql://localhost:3306/ex?useUnicode=true&useSSL=false&characterEncoding=utf8";
	// static final String USER_NAME = "root";
	// static final String PASSWORD = "password";
	// static final String PARENT_PACKAGE = "com.mxw.quantitation.common.db";
	// static final String MAPPER_PATH = "/quantitation-common/src/main/resources/mapper/";

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	/**
	 * RUN THIS
	 */
	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + OUT_PUT_DIR);
		gc.setAuthor(AUTHOR);
		gc.setOpen(false);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(DB_URL);
		// dsc.setSchemaName("public");
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername(USER_NAME);
		dsc.setPassword(PASSWORD);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		// pc.setModuleName(scanner("模块名"));
		pc.setModuleName(MODULE_NAME);
		pc.setParent(PARENT_PACKAGE);
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + MAPPER_PATH + pc.getModuleName()
						+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setRestControllerStyle(false);
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		strategy.setSuperEntityClass(SUPER_ENTITY_CLASS);
		strategy.setSuperEntityColumns(SUPER_ENTITY_COLUME);
		strategy.setEntityLombokModel(true);
		// strategy.setSuperControllerClass(SUPER_CONTROLLER_CLASS);
		// strategy.setInclude(scanner("表名"));
		strategy.setInclude(tables);
		strategy.setControllerMappingHyphenStyle(true);
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}
}
