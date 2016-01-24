package gear.web.control;

import com.github.andyshaox.servlet.mapping.View;
import com.github.andyshaox.servlet.mapping.annotation.Mapping;

@Mapping("/index")
public class IndexControl {

    public View doPost(){
        return View.defaultView("/index");
    }
    
    public View doGet(){
        return this.doPost();
    }
}
