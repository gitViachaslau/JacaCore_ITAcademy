package by.academy.task_10.repository;

import java.util.List;

public interface Operations<T> {

    List<T> readAll();

    boolean create(T t);

    boolean delete(List<T> t);
}

