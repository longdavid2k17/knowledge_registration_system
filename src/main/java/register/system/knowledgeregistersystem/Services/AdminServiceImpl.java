package register.system.knowledgeregistersystem.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import register.system.knowledgeregistersystem.data.AdminRepo;

@Service
public class AdminServiceImpl implements UserDetailsService
{
    private AdminRepo adminRepo;

    public AdminServiceImpl(AdminRepo adminRepo)
    {
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return adminRepo.findByUsername(s).get();
    }
}
