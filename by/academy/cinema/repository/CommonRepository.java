/**
 * Данный интерфей описывает основнеы CRUD операции для базы данных
 */

package by.academy.cinema.repository;

import java.util.List;

public interface CommonRepository<T> {

    boolean create(T t);

    T read(int id);

    boolean update(T oldObj, T newObj);

    boolean delete(int id);

    List<T> readAll();

}
