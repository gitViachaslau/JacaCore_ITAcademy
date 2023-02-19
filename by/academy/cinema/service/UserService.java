

package by.academy.cinema.service;

import by.academy.cinema.model.User;

import java.util.List;

public interface UserService {

    boolean createUser(User user);

    List<User> readAll ();

    boolean checkName(String name);

    User authorization(String name, String password);

    List<User> readAllNotRule ();

    User read(int idUser);



}
