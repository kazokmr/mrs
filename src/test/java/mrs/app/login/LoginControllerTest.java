package mrs.app.login;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

public class LoginControllerTest {
  
  MockMvc mockMvc;
  
  @Before
  public void setUp() {
    this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginController())
        .addFilters(new CharacterEncodingFilter("UTF-8"))
        .build();
  }
  
  @Test
  public void loginFormの呼び出しテスト() throws Exception {
    mockMvc.perform(get("/loginForm"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("login/loginForm"));
  }
}