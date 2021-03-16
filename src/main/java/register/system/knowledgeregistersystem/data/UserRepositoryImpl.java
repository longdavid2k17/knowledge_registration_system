package register.system.knowledgeregistersystem.data;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import register.system.knowledgeregistersystem.Models.ScholarshipRegistration;
import register.system.knowledgeregistersystem.Models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        long userId = saveUser(user);
        user.setUserId(userId);
        log.info("Zapisano "+user);
        return user;
    }

    public User getUser(String email, String interestedIn)
    {
        String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
        return jdbc.queryForObject(sql, new Object[]{email}, (rs, rowNum) ->
                new User(
                        rs.getLong("id"),
                        rs.getDate("createdAt"),
                        rs.getString("name"),
                        rs.getString("email"),
                        interestedIn
                ));
    }

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM USERS WHERE ID = ?";
        return jdbc.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new User(
                        rs.getLong("id"),
                        rs.getDate("createdAt"),
                        rs.getString("name"),
                        rs.getString("email"),
                        ""
                ));
    }

    @Override
    public List<User> getUsers() {
        return jdbc.query("select * from Users",new RowMapper<User>()
        {
            @Override
            public User mapRow(ResultSet rs, int rownumber) throws SQLException
            {
                User user=new User();
                user.setUserId(rs.getLong(1));
                user.setCreatedAt(rs.getDate(2));
                user.setName(rs.getString(3));
                user.setEmail(rs.getString(4));
                return user;
            }
        });
    }

    @Override
    public boolean isAccountExist(String email)
    {
        String sql = "SELECT count(*) FROM USERS WHERE EMAIL = ?";
        boolean exists = false;
        int count = jdbc.queryForObject(sql, new Object[] { email }, Integer.class);
        exists = count > 0;
        return exists;
    }

    private long saveUser(User user)
    {
        user.setCreatedAt(new Date());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                "insert into Users (createdAt, name, email) values (?,?,?)",
                Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR);
        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc =
                preparedStatementCreatorFactory.newPreparedStatementCreator(
                        Arrays.asList(new Timestamp(user.getCreatedAt().getTime()),user.getName(),user.getEmail()));
        jdbc.update(psc,keyHolder);

        return keyHolder.getKey().longValue();
    }
}
