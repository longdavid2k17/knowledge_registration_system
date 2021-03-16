package register.system.knowledgeregistersystem.Controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import register.system.knowledgeregistersystem.Models.*;
import register.system.knowledgeregistersystem.Services.MailService;
import register.system.knowledgeregistersystem.data.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class AdminPanelController
{
    private UserRepository userRepository;
    private ScholarshipRepository scholarshipRepository;
    private AdminRepo adminRepo;
    private RegistrationRepository registrationRepository;
    private MailService mailService;

    List<ScholarshipRegistration> scholarshipRegistrations;
    List<Scholarship> scholarshipList;


    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AdminPanelController.class);

    public AdminPanelController(UserRepository userRepository, ScholarshipRepository scholarshipRepository, AdminRepo adminRepo, RegistrationRepository registrationRepository, MailService mailService)
    {
        this.userRepository = userRepository;
        this.scholarshipRepository = scholarshipRepository;
        this.adminRepo = adminRepo;
        this.registrationRepository = registrationRepository;
        this.mailService = mailService;
    }

    @GetMapping("/admin")
    public String logged(Principal principal, Model model)
    {
        if(principal.getName()==null)
        {
            return "admin_panel/error";
        }
        else
        {
            model.addAttribute("name", principal.getName());
            Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            model.addAttribute("authorities", authorities);
            model.addAttribute("details", details);
            return "admin_panel/logged";
        }
    }

    @GetMapping("/admin/events")
    public String events(Model model)
    {
        List<Scholarship> scholarshipList = scholarshipRepository.getScholarships();
        log.info("Pobrałem listę! Rozmiar: "+scholarshipList.size());
        model.addAttribute("scholarships",scholarshipList);
        return "admin_panel/events";
    }

    @GetMapping("/admin/users")
    public String users(Model model)
    {
        List<User> userList = userRepository.getUsers();
        model.addAttribute("userList",userList);
        return "admin_panel/usersPreview";
    }

    @GetMapping("/admin/sendEmail")
    public String emailSenderWebsite(Model model)
    {
        scholarshipRegistrations = registrationRepository.getAll();
        scholarshipList = scholarshipRepository.getScholarships();
        log.info("Bez filtru - scholarshipRegistrations.size()="+scholarshipRegistrations.size()+"\nscholarshipList.size()="+scholarshipList.size());
        model.addAttribute("registrations",scholarshipRegistrations);
        model.addAttribute("scholarshipList",scholarshipList);
        model.addAttribute("mess",new Message("Wpisz tutaj swoją wiadmość"));
        return "admin_panel/sendMessage";
    }

    @GetMapping("/admin/sendEmail/{scholarshipId}")
    public String emailSenderFilteredWebsite(@PathVariable(value = "scholarshipId") Long scholarshipId, Model model)
    {
        if (scholarshipId==null)
        {
            log.info("Nie przekazano parametru!");
            return "redirect:/admin";
        }
        else

        log.info("Szukam kursu o id="+scholarshipId);
        scholarshipRegistrations = registrationRepository.filterByEventId(scholarshipId);
        scholarshipList = scholarshipRepository.getScholarships();
        log.info("Filtrowanie - scholarshipRegistrations.size()="+scholarshipRegistrations.size()+"\nscholarshipList.size()="+scholarshipList.size());
        model.addAttribute("registrations",scholarshipRegistrations);
        model.addAttribute("scholarshipList",scholarshipList);
        model.addAttribute("mess",new Message("Wpisz tutaj swoją wiadmość"));
        return "admin_panel/sendMessage";
    }

    @PostMapping("/admin/sendEmail")
    public String sendEmails(Model model, @RequestParam String mess) throws MessagingException
    {
            log.info("Tworzę liste użytkowników końcowych");
            List<User> endUsersList = new ArrayList<>();

            for(ScholarshipRegistration scholarshipRegistration : scholarshipRegistrations)
            {
                endUsersList.add(userRepository.getUserById(scholarshipRegistration.getUserId()));
            }
            log.info("Wysyłam wiadomości");
            for(User user:endUsersList)
            {
                mailService.sendMail(user.getEmail(),"Nowa wiadomość od administracji",mess,true);
            }
            return "redirect:/admin";

    }


    @GetMapping("/admin/addAdmin")
    public String admins(Model model)
    {
        model.addAttribute("admin", new Admin());
        return "admin_panel/admins";
    }

    @PostMapping("/admin/addAdmin")
    public String addAdmin(@Valid Admin admin, BindingResult result)
    {
        if (result.hasErrors())
        {
            return "admin_panel/admins";
        }
        else if(admin.getPassword()==null)
        {
            return "admin_panel/admins";
        }
        else
        log.info("Zapisuje administratora "+admin.toString());
        adminRepo.save(admin);
        return "redirect:/admin";
    }

    @GetMapping("/admin/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
        {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            log.info("Wylogowano");
        }
        return "redirect:/";
    }
}
