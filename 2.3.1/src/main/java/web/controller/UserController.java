package web.controller;

import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.hiber.model.Role;
import web.hiber.model.User;
import web.hiber.service.UserService;

import javax.persistence.NoResultException;
import java.security.Principal;


@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String printUsers() {
        return "admin";
    }

    @RequestMapping(value = "admin", method = RequestMethod.POST)
    public String addUser(@RequestParam String name, @RequestParam String password,
                          @RequestParam String lastName, @RequestParam String email,
                          @RequestParam Long age, @RequestParam String role) {
        try {
            userService.getUserByName(name);
        } catch (NoResultException e) {
            User user = new User(name, password, lastName, email, age, new Role(role));
            userService.add(user);
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "admin/delete", method = RequestMethod.POST)
    public String deleteUser(@RequestParam @NotNull String name) {
        userService.deleteUser(name);
        return "redirect:/admin";
    }

    @RequestMapping(value = "admin/change", method = RequestMethod.POST)
    public String changeUser(@RequestParam String name, @RequestParam String lastName,
                             @RequestParam String email, @RequestParam Long age,
                             @RequestParam String password, @RequestParam String role) {
        try {
            User user = userService.getUserByName(name);
            if (!lastName.isEmpty()) {
                user.setLastName(lastName);
            }
            if (!email.isEmpty()) {
                user.setEmail(email);
            }
            if (age != null) {
                user.setAge(age);
            }
            if (!password.isEmpty()) {
                user.setPassword(password);
            }
            if (!role.isEmpty()) {
                user.addRole(new Role(role));
            }
            userService.deleteUser(name);
            userService.add(user);
        } catch (NoResultException e) {
            return "redirect:/admin";
        }
        return "redirect:/admin";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String userPage(Principal principal, Model model) {
        User user = userService.getUserByName(principal.getName());
        model.addAttribute("user", user.toString());
        return "user";
    }

}