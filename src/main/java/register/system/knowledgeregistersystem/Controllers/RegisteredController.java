package register.system.knowledgeregistersystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import register.system.knowledgeregistersystem.Models.User;

@Controller
@RequestMapping("/scholarship/registered")
public class RegisteredController
{
    @GetMapping
    public String finished(Model model)
    {
        model.addAttribute("user", new User("patterns"));
        return "finished";
    }
}
