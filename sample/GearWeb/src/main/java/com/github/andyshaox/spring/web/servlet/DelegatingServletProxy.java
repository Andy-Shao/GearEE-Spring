package com.github.andyshaox.spring.web.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 * Title:<br>
 * Descript:<br>
 * Copyright: Copryright(c) Jan 22, 2016<br>
 * Encoding:UNIX UTF-8
 * 
 * @author Andy.Shao
 *
 */
public class DelegatingServletProxy extends GenericServlet {
    private static final long serialVersionUID = -6983213124640943640L;
    private Servlet proxy;
    private String targetBean;

    @Override
    public void init() throws ServletException {
        super.init();
        this.targetBean = this.getServletName();
        WebApplicationContext wac =
            WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        this.proxy = (Servlet) wac.getBean(this.targetBean);
        this.proxy.init(this.getServletConfig());
    }

    @Override
    public void service(ServletRequest req , ServletResponse res) throws ServletException , IOException {
        this.proxy.service(req , res);
    }

}
