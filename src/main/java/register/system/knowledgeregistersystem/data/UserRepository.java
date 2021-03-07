package register.system.knowledgeregistersystem.data;

import register.system.knowledgeregistersystem.Models.User;

public interface UserRepository
{
    User save(User user);
    User getUser(String email,String interestedIn);
    boolean isAccountExist(String email);

}
