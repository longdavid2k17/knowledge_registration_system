package register.system.knowledgeregistersystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import register.system.knowledgeregistersystem.Models.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/scholarship")
public class ScholarshipController
{
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ScholarshipController.class);

    @RequestMapping(value = "/mysql", method = RequestMethod.GET)
    public String mysql(Model model)
    {
        model.addAttribute("user", new User("mysql"));
        return "scholarships/mysql";
    }

    @RequestMapping(value = "/spring",method = RequestMethod.GET)
    public String spring(Model model)
    {
        model.addAttribute("user", new User("spring"));
        return "scholarships/spring";
    }

    @RequestMapping(value = "/patterns",method = RequestMethod.GET)
    public String patterns(Model model)
    {
        model.addAttribute("user", new User("patterns"));
        return "scholarships/patterns";
    }

    @RequestMapping(value = "/algorithms",method = RequestMethod.GET)
    public String algorithms(Model model)
    {
        model.addAttribute("user", new User("algorithms"));
        return "scholarships/algorithms";
    }

    @RequestMapping(value = "/hibernate",method = RequestMethod.GET)
    public String hibernate(Model model)
    {
        model.addAttribute("user", new User("hibernate"));
        return "scholarships/hibernate";
    }

    @RequestMapping(value = "/htmlcss",method = RequestMethod.GET)
    public String htmlcss(Model model)
    {
        model.addAttribute("user", new User("htmlcss"));
        return "scholarships/htmlcss";
    }

    @PostMapping
    public String processRegistration(@Valid User user, Errors errors)
    {
        if(errors.hasErrors())
        {
            return "/scholarships/"+user.getInterestedAt();
        }
        log.info("Przetwarzanie "+user);
        //sendEmail(contact);
        //return "redirect:/finished";
        return "redirect:/";
    }

}
