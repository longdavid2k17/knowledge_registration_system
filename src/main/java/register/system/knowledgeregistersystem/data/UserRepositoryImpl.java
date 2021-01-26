package register.system.knowledgeregistersystem.data;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import register.system.knowledgeregistersystem.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

@Repository
public class UserRepositoryImpl implements UserRepository
{
    private JdbcTemplate jdbc;

    public UserRepositoryImpl(JdbcTemplate jdbc)
    {
        this.jdbc = jdbc;
    }

    @Override
    public User save(User user)
    {
        long userId = saveUser(user);
        user.setUserId(userId);
        return user;
    }

    private long saveUser(User user)
    {
        user.setCreatedAt(new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        return keyHolder.getKey().longValue();
    }
}
