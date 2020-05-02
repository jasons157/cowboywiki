package personal.jasonevans.cowboywiki.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import personal.jasonevans.cowboywiki.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    /**
     * Sets the authentication provider as our own, with
     * our own user service and pw encoder.
     * @param auth authentication to set up for user
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Sets up permissions/denials for certain web pages based on roles
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //TODO create login page controller
        //TODO Create Login page
        //TODO Create action authenticateUser in login form
        //TODO create access denied page

        http.authorizeRequests()
                .antMatchers("/").hasRole("VISITOR")//Everyone gets visitor by default (UserServiceImpl)
                .and()
                .formLogin()
                    .loginPage("/showLoginPage")
                    .loginProcessingUrl("/authenticateUser")
                    .successHandler(successHandler)
                    .permitAll()//Everyone can go to the login page (or else it would defeat the point of all this)
                .and()
                .logout().permitAll()//Let everyone log out
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");


    }

    /**
     * Bean definitions
     */

    /**
     * Set authentication details and password encoder (uses BCrypt).
     * Setting userservicedetails allows us to use the loadUserByUsername
     * which includes roles.
     * Essentially loads the roles of a user.
     * @return Authentication provider auth
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    //get bcrypt encoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
