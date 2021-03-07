package register.system.knowledgeregistersystem.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import register.system.knowledgeregistersystem.Models.Scholarship;
import register.system.knowledgeregistersystem.Models.ScholarshipRegistration;
import register.system.knowledgeregistersystem.Models.User;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class RegistrationRepositoryImpl implements RegistrationRepository
{

    private JdbcTemplate jdbc;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationRepositoryImpl.class);

    public RegistrationRepositoryImpl(JdbcTemplate jdbc)
    {
        this.jdbc = jdbc;
    }

    @Override
    public ScholarshipRegistration save(User user)
    {
        ScholarshipRegistration scholarshipRegistration = new ScholarshipRegistration();
        long registrationId = saveRegistration(user, scholarshipRegistration);
        scholarshipRegistration.setRegistrationId(registrationId);
        log.info("Zapisano "+scholarshipRegistration);
        return scholarshipRegistration;
    }

    @Override
    public boolean isUserRegistered(User user)
    {
        String sql = "SELECT count(*) FROM Registrations WHERE userId = ?";
        int count = jdbc.queryForObject(sql, new Object[] { user.getUserId() }, Integer.class);
        boolean exists = count > 0;
        return exists;
    }

    private long getScholarshipId(String scholarshipName)
    {
        String sql = null;

        switch (scholarshipName)
        {
            case "mysql":
                sql = "SELECT id FROM Scholarships WHERE codeName = ?";
                break;
            case "spring":
                sql = "SELECT id FROM Scholarships WHERE codeName = ?";
                break;
            case "patterns":
                sql = "SELECT id FROM Scholarships WHERE codeName = ?";
                break;
            case "algorithms":
                sql = "SELECT id FROM Scholarships WHERE codeName = ?";
                break;
            case "hibernate":
                sql = "SELECT id FROM Scholarships WHERE codeName = ?";
                break;
            case "htmlcss":
                sql = "SELECT id FROM Scholarships WHERE codeName = ?";
                break;
        }

        long id = jdbc.queryForObject(sql, new Object[] { scholarshipName }, Long.class);
        return id;
    }

    private long saveRegistration(User user, ScholarshipRegistration scholarshipRegistration)
    {
        scholarshipRegistration.setPlacedAt(new Date());
        scholarshipRegistration.setUserId(user.getUserId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                "insert into Registrations (placedAt, userId, scholarshipId) values (?,?,?)",
                Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR);
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc =
                preparedStatementCreatorFactory.newPreparedStatementCreator(
                        Arrays.asList(new Timestamp(scholarshipRegistration.getPlacedAt().getTime()),user.getUserId(),getScholarshipId(user.getInterestedAt())));
        jdbc.update(psc,keyHolder);

        return keyHolder.getKey().longValue();
    }
}
