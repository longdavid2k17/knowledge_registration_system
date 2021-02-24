package register.system.knowledgeregistersystem.Models;

import lombok.Data;

import java.util.Date;

@Data
public class Scholarship
{
    private long scholarshipId;
    private Date takePlace;
    private String scholarshipName;
    private String description;
}
