package register.system.knowledgeregistersystem.data;

import register.system.knowledgeregistersystem.Models.ScholarshipRegistration;
import register.system.knowledgeregistersystem.Models.User;

public interface RegistrationRepository
{
    ScholarshipRegistration save(User user);
    boolean isUserRegistered(User user);
}
