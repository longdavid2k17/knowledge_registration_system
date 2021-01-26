package register.system.knowledgeregistersystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/scholarship/registered")
public class RegisteredController
{
    @GetMapping
    public String finished()
    {
        return "finished";
    }
}
