package gear.web.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.github.andyshao.lang.StringOperation;
import com.github.andyshao.util.ObjectOperation;

public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request , ServletResponse response , FilterChain chain) throws IOException , ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String url = StringOperation.replaceFirst(req.getRequestURI() , req.getContextPath() , "");
        boolean isAllow = (Boolean) ObjectOperation.valueOrNull(req.getSession().getAttribute(LoginControl.ALLOW_LOGIN) , false);
        if (url.equals("/login.html") || url.equals("/login/process.html") || isAllow) chain.doFilter(request , response);
        else req.getRequestDispatcher("/login.html").forward(request , response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
