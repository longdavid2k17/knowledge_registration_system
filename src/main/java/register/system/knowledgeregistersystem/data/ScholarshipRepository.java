package register.system.knowledgeregistersystem.data;

import register.system.knowledgeregistersystem.Models.Scholarship;

import java.util.List;

public interface ScholarshipRepository
{
    List<Scholarship> getScholarships();
    Scholarship save(Scholarship scholarship);
}
