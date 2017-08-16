package ts.infrastructure.user;

import org.eclipse.collections.api.list.ImmutableList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class LoginUser implements UserDetails {
    private final User user;
    private final ImmutableList<GrantedAuthority> grantedAuthorities;

    public LoginUser(User user, ImmutableList<Authority> authorities) {
        this.user = user;
        this.grantedAuthorities = authorities.collect(auth -> new SimpleGrantedAuthority(auth.getAuthority()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities.castToList();
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getLoginId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
