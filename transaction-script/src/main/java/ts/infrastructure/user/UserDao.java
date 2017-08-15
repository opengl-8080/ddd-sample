package ts.infrastructure.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

@Component
public class UserDao {
    
    private JdbcTemplate jdbc;

    public UserDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    public Optional<User> read(String loginId) {
        String sql = "SELECT * FROM USERS WHERE LOGIN_ID=?";

        Map<String, Object> record = this.jdbc.queryForMap(sql, loginId);

        if (record == null) {
            return Optional.empty();
        }
        
        User user = new User();
        user.setId((Long)record.get("ID"));
        user.setLoginId((String)record.get("LOGIN_ID"));
        user.setName((String)record.get("NAME"));
        user.setPassword((String)record.get("PASSWORD"));
        
        return Optional.of(user);
    }
}
