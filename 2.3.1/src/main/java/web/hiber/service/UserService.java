package web.hiber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.hiber.dao.UserDao;
import web.hiber.model.User;

import java.util.List;

@Transactional
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void add(User user) {
        userDao.add(user);
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public void deleteUser(String name) {
        userDao.deleteUser(name);
    }

    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
