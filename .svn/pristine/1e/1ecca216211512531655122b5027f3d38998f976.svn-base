package com.base.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jacob.com.ComThread;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.SqlServerDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.render.ViewType;
import com.zhilian.config.Constant;
import com.zhilian.plugin.AutoTableBindPlugin;
import com.zhilian.plugin.MyRoutesUtil;
import com.zhilian.plugin.TableNameStyle;
import com.zhilian.util.DateUtils;

/** API引导式配置 */
public class JFWebConfig extends JFinalConfig {

    /** 配置常量 */
    public void configConstant(Constants me) {
	// me.setDevMode(Constant.ACTION_REPORT_SWITCH);
	me.setDevMode(true);
	me.setEncoding("UTF-8");
	me.setViewType(ViewType.JSP);
	me.setBaseViewPath("/WEB-INF/jsp/");
	me.setBaseUploadPath(Constant.UPLOAD_DEFAULT_PATH);
    }

    /** 配置路由 */
    public void configRoute(Routes me) {
	MyRoutesUtil.add(me);
    }

    /** 配置插件 */
    public void configPlugin(Plugins me) {
	loadPropertyFile("config.properties");

	// DruidPlugin
	DruidPlugin dp = new DruidPlugin(getProperty("jdbc.url").trim(), getProperty("jdbc.username").trim(),
		getProperty("jdbc.password").trim());
	dp.addFilter(new StatFilter());
	WallFilter wall = new WallFilter();
	wall.setDbType(getProperty("jdbc.dbType"));
	dp.addFilter(wall);
	me.add(dp);

	AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(dp, TableNameStyle.LOWER,
		getProperty("jdbc.key").trim());
	autoTableBindPlugin.setShowSql(getPropertyToBoolean("jdbc.sql"));
	autoTableBindPlugin.setDialect(new SqlServerDialect());
	autoTableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
	me.add(autoTableBindPlugin);
    }

    /** 配置全局拦截器 */
    public void configInterceptor(Interceptors me) {

    }

    /** 配置处理器 */
    public void configHandler(Handlers me) {
	DruidStatViewHandler dvh = new DruidStatViewHandler("/druid");
	me.add(dvh);
    }

    /** 系统启动后回调 */
    public void afterJFinalStart() {
	//ComThread.startMainSTA();// 初始化word主线程

	loadPropertyFile("parameter.properties");

	Constant.MY_MESSAGE_TIPS = getProperty("my_message_tips");
	Constant.PROJECT_NAME = getProperty("project_name");

	Constant.SMTP_SERVER = getProperty("smtpServer");
	Constant.SMTP_PASSWORD = getProperty("password");
	Constant.SMTP_USER = getProperty("sender");

	Constant.SYSTEM_TITLE = getProperty("system_title");
	Constant.SYSTEM_VERSION_YEAR = getProperty("system_version_year");
	Constant.SYSTEM_VERSION_COMPANY = getProperty("system_version_company");
	Constant.SYSTEM_COMPANY_WEBSITE = getProperty("system_company_website");

	Constant.REPORT_TIME_HOUR = getPropertyToInt("report_time_hour");
	Constant.REPORT_AUTO_SWITCH = getPropertyToBoolean("report_auto_switch");
    }

    /** 系统关闭前回调 */
    public void beforeJFinalStop() {
	//ComThread.quitMainSTA();// 关闭word主线程
	
	System.out.println("系统准备关闭……  时间：" + DateUtils.getNowTime());
    }

}
