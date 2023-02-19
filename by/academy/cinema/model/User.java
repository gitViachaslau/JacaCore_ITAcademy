/**
 * Класс описывает пользователя кинотеатра
 *
 * id - номер id;
 * login - имя пользователя;
 * password - пароль пользователя;
 * userType - тип пользователя;
 * listIdTickets - список ID билетов пользователя;
 */

package by.academy.cinema.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;

    private String login;

    private String password;

    private UserType userType;

    public User(int id, String login, String password, UserType userType) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    public User(String login, String password, UserType userType) {
        this.login = login;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

}
