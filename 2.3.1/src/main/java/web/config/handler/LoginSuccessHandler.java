package web.config.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web.hiber.model.Role;
import web.hiber.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        User user = (User) authentication.getPrincipal();
        if (user.getAuthorities().contains(new Role("admin"))) {
            response.sendRedirect("/admin");
        } else if (user.getAuthorities().contains(new Role("user"))) {
            response.sendRedirect("/user");
        }

    }
}
