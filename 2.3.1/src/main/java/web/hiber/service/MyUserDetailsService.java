package web.hiber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.hiber.model.Role;
import web.hiber.model.User;

import javax.persistence.NoResultException;
import java.util.Collection;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.getUserByName(s);
        } catch (NoResultException e) {
            throw new UsernameNotFoundException(s);
        }
        return new User(user.getUsername(),
                user.getPassword(),
                (Collection<Role>) user.getAuthorities());
    }


}

