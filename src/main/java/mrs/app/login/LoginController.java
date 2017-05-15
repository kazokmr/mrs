package mrs.app.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SuppressWarnings("SameReturnValue")
public class LoginController {
  
  @RequestMapping("loginForm")
  String loginForm() {
    return "login/loginForm";
  }
}
