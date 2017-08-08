package ts.web;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login(Model model, CsrfToken csrfToken) {
        model.addAttribute("csrf", csrfToken);
        return "login";
    }
}