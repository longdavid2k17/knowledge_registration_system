package register.system.knowledgeregistersystem.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import register.system.knowledgeregistersystem.Models.ScholarshipRegistration;
import register.system.knowledgeregistersystem.Models.User;
import register.system.knowledgeregistersystem.Services.MailService;
import register.system.knowledgeregistersystem.data.RegistrationRepository;
import register.system.knowledgeregistersystem.data.UserRepository;

import javax.mail.MessagingException;
import javax.validation.Valid;

@Controller
@RequestMapping("/scholarship")
@SessionAttributes("user")
public class ScholarshipController
{
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ScholarshipController.class);
    private MailService mailService;

    @Autowired
    public ScholarshipController(UserRepository userRepository,RegistrationRepository registrationRepository, MailService mailService)
    {
        this.userRepository = userRepository;
        this.registrationRepository = registrationRepository;
        this.mailService = mailService;
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
    public String processRegistration(@Valid User user, Errors errors, @ModelAttribute ScholarshipRegistration scholarshipRegistration) throws MessagingException
    {
        String email_text = null;
        boolean isValid;

        if(errors.hasErrors())
        {
            return "/scholarships/"+user.getInterestedAt();
        }

        else if(!userRepository.isAccountExist(user.getEmail()))
        {
            log.info("Przetwarzanie "+user);
            User savedUser = userRepository.save(user);
            scholarshipRegistration.setRegisteredUser(savedUser);
            registrationRepository.save(savedUser);
            switch (savedUser.getInterestedAt())
            {
                case "mysql":
                    email_text = "Witaj w kursie MySQL!\nDostaniesz wkrótce link to twojego wydarzenia.\nMamy zapisanego twojego maila i wyślemy ci informacje o szkoleniu bazodanowym";
                    isValid = true;
                    break;

                case "spring":
                    email_text = "Witaj w kursie Spring!\nDostaniesz wkrótce link to twojego wydarzenia.\nMamy zapisanego twojego maila i wyślemy ci informacje o szkoleniu z frameworku SPRING";
                    isValid = true;
                    break;

                case "patterns":
                    email_text = "Witaj w kursie ''Wzorce projektowe!''\nDostaniesz wkrótce link to twojego wydarzenia.\nMamy zapisanego twojego maila i wyślemy ci informacje o szkoleniu o wzorcach projektowych";
                    isValid = true;
                    break;

                case "algorithms":
                    email_text = "Witaj w kursie algorytmów!\nDostaniesz wkrótce link to twojego wydarzenia.\nMamy zapisanego twojego maila i wyślemy ci informacje o szkoleniu algortytmowym";
                    isValid = true;
                    break;

                case "hibernate":
                    email_text = "Witaj w kursie Hibernate!\nDostaniesz wkrótce link to twojego wydarzenia.\nMamy zapisanego twojego maila i wyślemy ci informacje o szkoleniu z zakresu technologii Hibernate";
                    isValid = true;
                    break;

                case "htmlcss":
                    email_text = "Witaj w kursie HTML!\nDostaniesz wkrótce link to twojego wydarzenia.\nMamy zapisanego twojego maila i wyślemy ci informacje o szkoleniu z tworzenia stron internetowych";
                    isValid = true;
                    break;

                default:
                    isValid = false;
                    break;

            }
            if(isValid)
            {
                mailService.sendMail(savedUser.getEmail(),savedUser.getName()+", zarejestrowałeś się na wydarzenie "+savedUser.getInterestedAt()+"!",email_text,true);
            }
            else
            {
                log.info("Nie wysłano wiadomości z powodu błędów obiektu");
            }

            return "redirect:/scholarship/registered";
        }
        else
        {
            log.info("Konto o podanym emailu istnieje. Dodaję użytkownika do drugiego wydarzenia");
            User userFromDb = userRepository.getUser(user.getEmail(), user.getInterestedAt());
            scholarshipRegistration.setRegisteredUser(userFromDb);
            registrationRepository.save(userFromDb);
            switch (userFromDb.getInterestedAt())
            {
                case "mysql":
                    email_text = "Witaj w kursie MySQL!\nDostaniesz wkrótce link to twojego wydarzenia. \nPosiadasz już u nas konto i wyślemy ci informacje o szkoleniu bazodanowym";
                    isValid = true;
                    break;

                case "spring":
                    email_text = "Witaj w kursie Spring!\nDostaniesz wkrótce link to twojego wydarzenia.\nPosiadasz już u nas konto i wyślemy ci informacje o szkoleniu z frameworku SPRING";
                    isValid = true;
                    break;

                case "patterns":
                    email_text = "Witaj w kursie ''Wzorce projektowe!''\nDostaniesz wkrótce link to twojego wydarzenia.\nPosiadasz już u nas konto i wyślemy ci informacje o szkoleniu o wzorcach projektowych";
                    isValid = true;
                    break;

                case "algorithms":
                    email_text = "Witaj w kursie algorytmów!\nDostaniesz wkrótce link to twojego wydarzenia.\nPosiadasz już u nas konto i wyślemy ci informacje o szkoleniu algortytmowym";
                    isValid = true;
                    break;

                case "hibernate":
                    email_text = "Witaj w kursie Hibernate!\nDostaniesz wkrótce link to twojego wydarzenia.\nPosiadasz już u nas konto i wyślemy ci informacje o szkoleniu z zakresu technologii Hibernate";
                    isValid = true;
                    break;

                case "htmlcss":
                    email_text = "Witaj w kursie HTML!\nDostaniesz wkrótce link to twojego wydarzenia.\nPosiadasz już u nas konto i wyślemy ci informacje o szkoleniu z tworzenia stron internetowych";
                    isValid = true;
                    break;

                default:
                    isValid = false;
                    break;

            }
            if(isValid)
            {
                mailService.sendMail(userFromDb.getEmail(),userFromDb.getName()+", zarejestrowałeś się na wydarzenie "+userFromDb.getInterestedAt()+"!",email_text,true);
            }
            else
            {
                log.info("Nie wysłano wiadomości z powodu błędów obiektu");
            }

            return "redirect:/scholarship/registered";
        }
    }

}
