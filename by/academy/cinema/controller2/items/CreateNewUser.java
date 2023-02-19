/**
 * Класс для создания пользователя
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.User;
import by.academy.cinema.model.UserType;
import by.academy.cinema.util.CheckType;


import static by.academy.cinema.util.CheckSyntax.checkSyntax;

public class CreateNewUser extends AbstractMenuItem {
    public CreateNewUser(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {
        final int NAME_SYMBOL_MIN = 4;
        final int NAME_SYMBOL_MAX = 10;
        final int PASSWORD_SYMBOL_MIN = 8;
        final int PASSWORD_SYMBOL_MAX = 12;

        while (true) {
            System.out.println("=-=-=-= Регистрация пользователя =-=-=-=");
            System.out.println("=== 0 - Возврат  предыдущее меню ===");
            System.out.println("Введите имя пользователя\nДопустимо имя не менее 4 и не более 10 символов " +
                    "(буквы и цифры) без пробелов");
            System.out.print("--> ");

            String name = getUserInput();

            if (EXIT_COMMAND.equals(name)) {
                return;
            }

            if (checkSyntax(name, NAME_SYMBOL_MIN, NAME_SYMBOL_MAX, CheckType.LOGIN)) {
                if (checkName(name)) {
                    while (true) {
                        System.out.println("Введите пароль пользователя\nДопустим пароль от 8 до 12" +
                                "символов без пробелов (цифры и буквы)");
                        System.out.print("--> ");
                        String password = getUserInput();

                        if (EXIT_COMMAND.equals(password)) {
                            return;
                        }

                        if (checkSyntax(password, PASSWORD_SYMBOL_MIN, PASSWORD_SYMBOL_MAX, CheckType.PASSWORD)) {
                            boolean result = getContext().getUserService().createUser(new User(name, password, UserType.USER));

                            System.out.println(result ? "Вы зарегистрированы. Войдите в сервис используя логин и пароль.\n" :
                                    "Регистрация завершилась неудачно\n");
                            return;
                        } else {
                            System.out.println("Ошибка! Вы ввели недопустимый пароль\n");
                        }
                    }
                } else {
                    System.out.println("Ошибка! Такое имя уже существует. Попробуйте другое.\n");
                }
            } else {
                System.out.println("Ошибка! Вы ввели недопустимое имя.\n");
            }
        }
    }

    private boolean checkName(String name) {
        return getContext().getUserService().checkName(name);
    }
}
