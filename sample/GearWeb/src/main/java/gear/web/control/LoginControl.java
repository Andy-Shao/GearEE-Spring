package gear.web.control;

import com.github.andyshaox.servlet.mapping.PageView;
import com.github.andyshaox.servlet.mapping.VariableLevel;
import com.github.andyshaox.servlet.mapping.View;
import com.github.andyshaox.servlet.mapping.annotation.Mapping;
import com.github.andyshaox.servlet.mapping.annotation.Variable;

@Mapping("/login")
public class LoginControl {
    public static final String ALLOW_LOGIN = "allow_login";

    @Mapping
    public String login() {
        return "/login";
    }

    @Mapping("/process")
    public View process(@Variable(required = false) String username , @Variable(required = false) String password) {
        PageView view = new PageView("/index");
        if (username == null || password == null) return view;
        if (username.trim().equals("andyshao") && password.trim().equals("andyshao"))
            view.addInjection(LoginControl.ALLOW_LOGIN , true , VariableLevel.SESSION);
        return view;
    }
}
