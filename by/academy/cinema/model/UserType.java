/**
 * Типы пользователей
 *
 * USER - простой пользователь;
 * MANAGER - менеджер;
 * ADMIN - администратор;
 */

package by.academy.cinema.model;

public enum UserType {

    USER ("USER") , MANAGER ("MANAGER"), ADMIN ("ADMIN");

    private String level;

    UserType(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}
