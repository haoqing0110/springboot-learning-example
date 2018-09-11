package spring.boot.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.boot.core.domain.User;
import spring.boot.core.domain.UserRepository;
import spring.boot.core.service.UserService;

import java.util.List;

/**
 * User Service
 *
 * Created by bysocket on 24/07/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User insertByUser(User user) {
        LOGGER.info("New added user: " + user.toString());
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        LOGGER.info("Update user: " + user.toString());
        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);

        LOGGER.info("Delete user: " + user.toString());
        return user;
    }

    @Override
    public User findById(Long id) {
        LOGGER.info("Get User ID: " + id);
        return userRepository.findById(id).get();
    }
}
