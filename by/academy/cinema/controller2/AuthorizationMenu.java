/**
 * Всё что нужно для авторизации
 *
 * chooseMenus - мапа храним ключ-значение типа тип пользователя - менюшка для этого типа стартовая
 */

package by.academy.cinema.controller2;

import by.academy.cinema.model.Context;
import by.academy.cinema.model.User;
import by.academy.cinema.model.UserType;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AuthorizationMenu extends AbstractMenuItem {

    private Map<UserType, MenuItem> chooseMenus;

    public AuthorizationMenu(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
        chooseMenus = new HashMap<>();
    }

    public AuthorizationMenu addChooseMenu(UserType userType, MenuItem menuItem) {
        chooseMenus.put(userType, menuItem);
        return this;
    }

    @Override
    public void start() {
        User user = null;
        while (true){
            System.out.println("=-=-=-= Авторизация пользователя =-=-=-=");
            System.out.println("=== 0 - возврат в предыдущее меню ===");
            System.out.println("Введите имя пользователя");
            System.out.print("--> ");

            String name = getUserInput();
            if (EXIT_COMMAND.equals(name)) {
                return;
            }

            System.out.println("Введите пароль пользователя");
            System.out.print("--> ");

            String password = getUserInput();
            if (EXIT_COMMAND.equals(password)) {
                return;
            }

            user = getContext().getUserService().authorization(name, password);
            getContext().setUser(user);
            break;
        }

        if (user != null) {
            MenuItem menu = chooseMenus.get(user.getUserType());
            if (menu != null) {
                menu.start();
            }
        }

        System.out.println("Логин или пароль не верен.");
        System.out.println();

    }
}
