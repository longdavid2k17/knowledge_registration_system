package register.system.knowledgeregistersystem.Models;

import lombok.Data;

import java.util.Date;

@Data
public class Scholarship
{
    private long scholarshipId;
    private Date takePlace;
    private String codeName;
    private String scholarshipName;
    private String description;

    public long getScholarshipId() {
        return scholarshipId;
    }

    public void setScholarshipId(long scholarshipId) {
        this.scholarshipId = scholarshipId;
    }

    public Date getTakePlace() {
        return takePlace;
    }

    public void setTakePlace(Date takePlace) {
        this.takePlace = takePlace;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
