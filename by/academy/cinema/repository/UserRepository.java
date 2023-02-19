package by.academy.cinema.repository;

import by.academy.cinema.model.User;

public interface UserRepository extends CommonRepository<User> {

    User gerUserOnName(String nameUser);
}
