package com.github.andyshaox.spring.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.XmlWebApplicationContext;

import com.github.andyshao.lang.StringOperation;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 27, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class SpringContextLoaderListener implements ServletContextListener {
    private static final String PATH_NAME = "contextConfigLocation";

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String[] pathes = StringOperation.split(context.getInitParameter(SpringContextLoaderListener.PATH_NAME) , ",");
        //Do something...
        @SuppressWarnings("resource")
        XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
        applicationContext.setConfigLocations(pathes);
        applicationContext.setServletContext(context);
        applicationContext.refresh();
    }

}
