package register.system.knowledgeregistersystem.Models;

import lombok.Data;

import java.util.Date;

@Data
public class ScholarshipRegistration
{
    private long registrationId;
    private Date placedAt;
    private long userId;
    private long scholarshipId;
    private User registeredUser;

    public User getRegisteredUser()
    {
        return registeredUser;
    }

    public void setRegisteredUser(User registeredUser)
    {
        this.registeredUser = registeredUser;
    }


}
