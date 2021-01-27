package register.system.knowledgeregistersystem.data;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import register.system.knowledgeregistersystem.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class UserRepositoryImpl implements UserRepository
{
    private JdbcTemplate jdbc;
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserRepositoryImpl.class);

    public UserRepositoryImpl(JdbcTemplate jdbc)
    {
        this.jdbc = jdbc;
    }

    @Override
    public User save(User user)
    {
        saveUser(user);
        user.setUserId((long) 2);
        log.info("Przetworzono "+user);
        return user;
    }

    private boolean saveUser(User user)
    {
        user.setCreatedAt(new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                        "insert into Users (createdAt, name, email) values (?,?,?)", Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR
                ).newPreparedStatementCreator(
                        Arrays.asList(
                                new Timestamp(user.getCreatedAt().getTime()),user.getName(),user.getEmail()));
        jdbc.update(psc, keyHolder);
        return true;
    }
}
