package personal.jasonevans.cowboywiki.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import personal.jasonevans.cowboywiki.entity.User;
import personal.jasonevans.cowboywiki.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class triggers when a user is successfully authenticated.
 * Their account will be fetched and placed into the HTTP Session.
 * They will then be forwarded to the home page.
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        System.out.println("Successfully authenticated");

        String username = authentication.getName();

        System.out.println("Current user: " + username);

        //fetch user from DB
        User theUser = userService.findByUserName(username);

        //place user in session
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("user", theUser);
        System.out.println("Roles: " + theUser.getRoles());

        //forward to home page of "/"
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/");
    }
}
