package gear.web.valueobject;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.andyshaox.servlet.mapping.ParameterFormat;
import com.github.andyshaox.servlet.mapping.Variable;

import gear.web.control.LoginControl;

public class UserInfoFormat implements ParameterFormat {

    @Override
    public Object covert(
        ServletConfig config , HttpServletRequest request , HttpServletResponse response , Variable variable ,
        Class<?> valueType) throws ServletException , IOException {
        UserInfo userInfo = new UserInfo();
        HttpSession session = request.getSession();
        userInfo.setUsername(session.getAttribute(LoginControl.USER_NAME).toString());
        userInfo.setSessionId(session.getId());
        return userInfo;
    }

}
