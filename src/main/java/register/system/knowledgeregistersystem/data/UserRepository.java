package register.system.knowledgeregistersystem.data;

import register.system.knowledgeregistersystem.Models.User;

import java.util.List;

public interface UserRepository
{
    User save(User user);
    User getUser(String email,String interestedIn);
    User getUserById(Long id);
    List<User> getUsers();
    boolean isAccountExist(String email);

}
