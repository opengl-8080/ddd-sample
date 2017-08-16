package ts.infrastructure.user;

import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDao userDao;
    private AuthorityDao authorityDao;

    public UserDetailsServiceImpl(UserDao userDao, AuthorityDao authorityDao) {
        this.userDao = userDao;
        this.authorityDao = authorityDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userDao.readByLoginId(username)
                           .map(this::mapToLoginUser)
                           .orElse(null);
    }
    
    private LoginUser mapToLoginUser(User user) {
        ImmutableList<Authority> authorities = this.authorityDao.readByUserId(user.getId());
        return new LoginUser(user, authorities);
    }
}
