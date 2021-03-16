package register.system.knowledgeregistersystem.data;

import register.system.knowledgeregistersystem.Models.ScholarshipRegistration;
import register.system.knowledgeregistersystem.Models.User;

import java.util.List;

public interface RegistrationRepository
{
    ScholarshipRegistration save(User user);
    boolean isUserRegistered(User user);
    List<ScholarshipRegistration> getAll();
    List<ScholarshipRegistration> filterByEventId(Long id);
}
