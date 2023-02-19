package by.academy.cinema.service.impl;

import by.academy.cinema.model.User;
import by.academy.cinema.model.UserType;
import by.academy.cinema.repository.UserRepository;
import by.academy.cinema.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean createUser(User user) {
        boolean result = false;
        try {
            result = userRepository.create(user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    @Override
    public List<User> readAll() {
        return userRepository.readAll();
    }


    @Override
    public boolean checkName(String name) {

        User user = userRepository.gerUserOnName(name);

        if (user == null) {
            return true;
        }
        return false;
    }

    @Override
    public User authorization(String login, String password) {
        User userResult = null;
        List<User> users = readAll();

        if (users == null) {
            return userResult;
        }

        for (User user : users) {
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(password)) {
                    userResult = user;
                    return userResult;
                }
            }
        }

        return userResult;
    }

    @Override
    public List<User> readAllNotRule() {
        List<User> users = readAll();
        List<User> result = null;

        if (users == null) {
            return result;
        }

        result = new ArrayList<>();

        for (User u : users) {
            if (u.getUserType().equals(UserType.USER)) {
                result.add(u);
            }
        }

        return result;
    }

    @Override
    public User read(int idUser) {
        return userRepository.read(idUser);
    }
}
