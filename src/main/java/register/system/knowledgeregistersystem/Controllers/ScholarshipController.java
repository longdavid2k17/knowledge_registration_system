package register.system.knowledgeregistersystem.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import register.system.knowledgeregistersystem.Models.ScholarshipRegistration;
import register.system.knowledgeregistersystem.Models.User;
import register.system.knowledgeregistersystem.data.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/scholarship")
public class ScholarshipController
{
    private final UserRepository userRepository;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ScholarshipController.class);

    @Autowired
    public ScholarshipController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

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
    public String processRegistration(@Valid User user, Errors errors, @ModelAttribute ScholarshipRegistration scholarshipRegistration)
    {
        if(errors.hasErrors())
        {
            return "/scholarships/"+user.getInterestedAt();
        }
        log.info("Przetwarzanie "+user);
        User savedUser = userRepository.save(user);
        scholarshipRegistration.setRegisteredUser(savedUser);
        //return "redirect:/finished";
        return "redirect:/scholarship/registered";
    }

}
