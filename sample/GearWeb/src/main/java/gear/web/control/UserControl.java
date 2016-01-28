package gear.web.control;

import com.github.andyshaox.servlet.mapping.PageView;
import com.github.andyshaox.servlet.mapping.annotation.Mapping;
import com.github.andyshaox.servlet.mapping.annotation.Variable;

import gear.web.valueobject.UserInfo;
import gear.web.valueobject.UserInfoFormat;

@Mapping("/user")
public class UserControl {
    @Mapping("/userInfo")
    public PageView userInfo(@Variable(formatClass = UserInfoFormat.class) UserInfo userInfo) {
        PageView view = new PageView("/userInfo");
        view.addInjection("user_info" , userInfo);
        return view;
    }
}
