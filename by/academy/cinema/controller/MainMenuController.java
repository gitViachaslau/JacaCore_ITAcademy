/**
 * Класс для отображению главного меню
 */

package by.academy.cinema.controller;

import by.academy.cinema.model.*;
import by.academy.cinema.service.EventService;
import by.academy.cinema.service.MovieService;
import by.academy.cinema.service.TicketService;
import by.academy.cinema.service.UserService;
import by.academy.cinema.util.CheckType;

import java.util.Scanner;

import static by.academy.cinema.util.CheckSyntax.checkSyntax;

public class MainMenuController implements Controller {

    private ControllerFactory controllerFactory;

    private UserService userService;

    private TicketService ticketService;

    private MovieService movieService;

    private EventService eventService;

    static final String EXIT = "0";

    public MainMenuController(UserService userService,
                              TicketService ticketService,
                              MovieService movieService,
                              EventService eventService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.movieService = movieService;
        this.eventService = eventService;
        controllerFactory = new ControllerFactory(userService, eventService, movieService, ticketService);
    }

    @Override
    public void start() {
        while (true) {
            System.out.println("=-=-=-= Кинотеатр \"Наследие Михалкова\" =-=-=-=");
            System.out.println("1 - Авторизация пользователя");
            System.out.println("2 - Регистрация пользователя");
            System.out.println("0 - Завершение работы");
            System.out.println("-->");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            boolean result = false;

            switch (input) {
                case "1":
                    result = authorization();
                    System.out.println(result ? "" :
                            "Вы не были авторизованы. Проверьте введённый логин, пароль и попробуйте снова\n");
                    break;
                case "2":
                    result = createNewUser();
                    System.out.println(result ? "Вы зарегистрированы. Войдите в сервис используя логин и пароль.\n" :
                            "Регистрация завершилась неудачно\n");
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Вы ввели недопустимое значение!!! Попробуйте ещё раз!\n");
                    break;
            }
        }
    }

    private boolean authorization() {
        while (true){
            System.out.println("=-=-=-= Авторизация пользователя =-=-=-=");
            System.out.println("=== 0 - возврат в предыдущее меню ===");
            System.out.println("Введите имя пользователя");
            System.out.print("--> ");

            Scanner input = new Scanner(System.in);
            String name = input.nextLine();
            if (EXIT.equals(name)) {
                return false;
            }

            System.out.println("Введите пароль пользователя");
            System.out.print("--> ");

            input = new Scanner(System.in);
            String password = input.nextLine();
            if (EXIT.equals(password)) {
                return false;
            }

            User user = userService.authorization(name, password);

            if (user != null){
                System.out.println();
                controllerFactory.getController(user).start();
            }
            else {
                return false;
            }
        }
    }


    private boolean createNewUser() {

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
            Scanner input = new Scanner(System.in);
            String name = input.nextLine();

            if (EXIT.equals(name)) {
                return false;
            }

            if (checkSyntax(name, NAME_SYMBOL_MIN, NAME_SYMBOL_MAX, CheckType.LOGIN)) {
                if (checkName(name)) {
                    while (true) {
                        System.out.println("Введите пароль пользователя\nДопустим пароль от 8 до 12" +
                                "символов без пробелов (цифры и буквы)");
                        System.out.print("--> ");
                        String password = input.nextLine();

                        if (EXIT.equals(password)) {
                            return false;
                        }

                        if (checkSyntax(password, PASSWORD_SYMBOL_MIN, PASSWORD_SYMBOL_MAX, CheckType.PASSWORD)) {
                            boolean user = userService.createUser(new User(name, password, UserType.USER));
                            if (user) {
                                return true;
                            } else {
                                return false;
                            }
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
        return userService.checkName(name);
    }


}
