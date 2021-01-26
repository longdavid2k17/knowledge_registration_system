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
}
