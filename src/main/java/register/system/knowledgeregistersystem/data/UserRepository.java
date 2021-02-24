package register.system.knowledgeregistersystem.data;

import register.system.knowledgeregistersystem.Models.User;

public interface UserRepository
{
    User save(User user);
    boolean isAccountExist(String email);

}
