package ts.infrastructure.user;

import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao extends AbstractDao<User> {
    
    public UserDao(JdbcTemplate jdbc) {
        super(jdbc, User.class);
    }
    
    public Optional<User> readByLoginId(String loginId) {
        ImmutableList<User> users = this.read("readByLoginId", loginId);
        return users.getFirstOptional();
    }
}
