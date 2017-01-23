package com.gm;

import com.gm.Controller.IndexController;
import com.gm.Model.Jia;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;

/**
 * Created by Administrator on 2017-01-12.
 */
public class TTConfig extends JFinalConfig {
    @Override
    public void configConstant(Constants constants) {
        constants.setDevMode(true);
        constants.setBaseViewPath("/WEB-INF/views");
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/", IndexController.class,"/home");
    }

    @Override
    public void configPlugin(Plugins plugins) {
        this.loadPropertyFile("jdbc.properties");

        DruidPlugin dp = new DruidPlugin(getProperty("jdbc.url"),getProperty("jdbc.user"),getProperty("jdbc.password"));
        plugins.add(dp);

        ActiveRecordPlugin ap=new ActiveRecordPlugin(dp);
        ap.setDialect(new AnsiSqlDialect());
        ap.setContainerFactory(new CaseInsensitiveContainerFactory());
        plugins.add(ap);
        ap.addMapping("co_times","autoid", Jia.class);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }

    public static void main(String[] args) {
        JFinal.start("src/main/webapp",8090,"/",5);
    }
}
