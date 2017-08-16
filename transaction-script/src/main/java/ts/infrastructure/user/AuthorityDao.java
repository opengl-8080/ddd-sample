package ts.infrastructure.user;

import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthorityDao extends AbstractDao<Authority> {

    public AuthorityDao(JdbcTemplate jdbc) {
        super(jdbc, Authority.class);
    }

    public ImmutableList<Authority> readByUserId(long userId) {
        return this.read("readByUserId", userId);
    }
}
