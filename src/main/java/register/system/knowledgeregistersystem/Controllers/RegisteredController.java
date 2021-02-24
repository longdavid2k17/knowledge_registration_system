package register.system.knowledgeregistersystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import register.system.knowledgeregistersystem.Models.User;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/scholarship/registered")
@SessionAttributes("user")
public class RegisteredController
{
    @GetMapping
    public String finished(@Valid User passedUser, Model model, SessionStatus sessionStatus)
    {
        model.addAttribute("user", passedUser);
        sessionStatus.setComplete();
        return "finished";
    }
}
