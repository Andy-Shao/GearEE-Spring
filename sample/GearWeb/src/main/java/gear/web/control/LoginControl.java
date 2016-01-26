package gear.web.control;

import org.apache.log4j.Logger;

import com.github.andyshaox.servlet.mapping.PageView;
import com.github.andyshaox.servlet.mapping.VariableLevel;
import com.github.andyshaox.servlet.mapping.View;
import com.github.andyshaox.servlet.mapping.annotation.Mapping;
import com.github.andyshaox.servlet.mapping.annotation.Variable;

@Mapping("/login")
public class LoginControl {
    public static final String ALLOW_LOGIN = "allow_login";
    private static final Logger LOG = Logger.getLogger(LoginControl.class);
    
    @Mapping
    public String login(){
        return "/login";
    }

    @Mapping("/process")
    public View process(@Variable(required = false) String username , @Variable(required = false) String password) {
        LOG.info("username is " + username + ", password is " + password);
        PageView view = new PageView("/index.html");
        if (username == null || password == null) return view;
        if (username.trim().equals("andyshao") && password.trim().equals("andyshao"))
            view.addInjection(ALLOW_LOGIN , true , VariableLevel.SESSION);
        return view;
    }
}
