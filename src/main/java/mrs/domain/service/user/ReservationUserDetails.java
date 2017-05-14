package mrs.domain.service.user;

import java.util.Collection;
import mrs.domain.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class ReservationUserDetails implements UserDetails {
  
  private static final long serialVersionUID = -3209661816788460774L;
  private final User user;
  
  public ReservationUserDetails(User user) {
    this.user = user;
  }
  
  public User getUser() {
    return user;
  }
  
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return AuthorityUtils.createAuthorityList("ROLE_" + this.user.getRoleName().name());
  }
  
  @Override
  public String getPassword() {
    return this.user.getPassword();
  }
  
  @Override
  public String getUsername() {
    return this.user.getUserId();
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
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    
    ReservationUserDetails that = (ReservationUserDetails) o;
    
    return user.equals(that.user);
  }
  
  @Override
  public int hashCode() {
    return user.hashCode();
  }
}
