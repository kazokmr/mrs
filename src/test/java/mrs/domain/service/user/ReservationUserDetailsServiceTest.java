package mrs.domain.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import mrs.domain.model.RoleName;
import mrs.domain.model.User;
import mrs.domain.repository.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ReservationUserDetailsServiceTest {
  
  @InjectMocks
  ReservationUserDetailsService service;
  
  @Mock
  UserRepository userRepository;
  
  User userA;
  User userB;
  
  @Before
  public void setUp() throws Exception {
    userA = new User();
    userA.setUserId("aaaa");
    userA.setPassword("");
    userA.setLastName("taro");
    userA.setFirstName("yamada");
    userA.setRoleName(RoleName.USER);
    
    userB = new User();
    userB.setUserId("aaaa");
    userB.setPassword("");
    userB.setLastName("taro");
    userB.setFirstName("yamada");
    userB.setRoleName(RoleName.USER);
  }
  
  @Test
  public void usernameからUserAが取れること() {
    doReturn(userA).when(userRepository).findOne("aaaa");
    
    UserDetails userDetails = service.loadUserByUsername("aaaa");
    assertThat(userDetails).isEqualTo(new ReservationUserDetails(userA));
  }
  
  @Test
  public void usernameからUserBが取れること() {
    doReturn(userB).when(userRepository).findOne("bbbb");
    
    UserDetails userDetails = service.loadUserByUsername("bbbb");
    assertThat(userDetails).isEqualTo(new ReservationUserDetails(userB));
  }
  
  @Test(expected = UsernameNotFoundException.class)
  public void usernameが取れなければException() throws Exception {
    doReturn(null).when(userRepository).findOne("abcd");
    
    service.loadUserByUsername("abcd");
  }
}