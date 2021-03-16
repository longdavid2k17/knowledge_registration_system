package register.system.knowledgeregistersystem.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import register.system.knowledgeregistersystem.Models.Scholarship;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ScholarshipRepositoryImpl implements ScholarshipRepository
{
    private JdbcTemplate jdbc;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ScholarshipRepositoryImpl.class);

    public ScholarshipRepositoryImpl(JdbcTemplate jdbc)
    {
        this.jdbc = jdbc;
    }

    @Override
    public List<Scholarship> getScholarships()
    {return jdbc.query("select * from Scholarships",new RowMapper<Scholarship>()
    {
        @Override
        public Scholarship mapRow(ResultSet rs, int rownumber) throws SQLException
        {
            Scholarship scholarship=new Scholarship();
            scholarship.setScholarshipId(rs.getInt(1));
            scholarship.setTakePlace(rs.getDate(2));
            scholarship.setCodeName(rs.getString(3));
            scholarship.setScholarshipName(rs.getString(4));
            scholarship.setDescription(rs.getString(5));
            return scholarship;
        }
    });
    }

    @Override
    public Scholarship save(Scholarship scholarship) {
        return null;
    }
}
