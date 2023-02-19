/**
 * Класс для отображению меню МЕНЕДЖЕРА
 */

package by.academy.cinema.controller;

import by.academy.cinema.model.User;
import by.academy.cinema.service.EventService;
import by.academy.cinema.service.MovieService;
import by.academy.cinema.service.TicketService;
import by.academy.cinema.service.UserService;
import by.academy.cinema.util.CheckType;

import java.util.Scanner;

import static by.academy.cinema.util.CheckSyntax.checkSyntax;
import static by.academy.cinema.controller.MainMenuController.EXIT;

public class ManagerMenuController extends CommonManagementController{

    private ControllerFactory controllerFactory;


    public ManagerMenuController(User user,
                                 UserService userService,
                                 EventService eventService,
                                 MovieService movieService,
                                 TicketService ticketService) {

        super(user, userService, eventService, movieService, ticketService);
        controllerFactory = new ControllerFactory(userService, eventService, movieService, ticketService);
    }

    @Override
    public void start() {
        while (true) {
            System.out.println("\n=-=-=-= МЕНЮ МЕНЕДЖЕРА =-=-=-=");
            System.out.println("1 - Показать все фильмы");
            System.out.println("2 - Показать все мероприятия");
            System.out.println("3 - Показать все пользователей");
            System.out.println("4 - Зайти в кабинет определённого пользователя");
            System.out.println("5 - Редактировать мероприятие");
            System.out.println("6 - Редактировать фильм");
            System.out.println("\n0 - Возврат в предыдущее меню с завершением авторизации");
            System.out.print("-->");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            boolean result = false;

            switch (input) {
                case "1":
                    System.out.println("\n=== Список фильмов ===");
                    result = printMovies();
                    //ShowMovies
                    System.out.println(result ? "" : "Фильмов нет\n");
                    break;
                case "2":
                    System.out.println("\n=== Список мероприятий ===");
                    result = printEvents();
                    System.out.println(result ? "" : "Мероприятий нет\n");
                    break;
                case "3":
                    System.out.println("\n=== Список пользователей ===");
                    result = printAllUsers();
                    //ShowUsers
                    System.out.println(result ? "" : "Пользователей нет\n");
                    break;
                case "4":
                    System.out.println("\n=== Работа в меню пользователя ===");
                    //TODO
                    result = uploadMenuUser();
                    System.out.println(result ? "" : "Такого пользователя нет\n");
                    break;
                case "5":
                    System.out.println("");
                    result = editMovie();
                    System.out.println(result ? "" : "Редактирование фильма не удалось\n");
                    break;
                case "6":
                    System.out.println("\n=== Редактирование фильма ===");
                    result = editMovie();
                    System.out.println(result ? "Фильм отредактирован" : "Редактирование фильма не удалось\n");
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Вы ввели недопустимое значение!!! Попробуйте ещё раз!\n");
                    break;
            }

        }
    }



    private boolean uploadMenuUser(){

        final int ID_SYMBOL_MIN = 1;
        final int ID_SYMBOL_MAX = 8;

        while (true) {
            System.out.println("=== 0 - Возврат  предыдущее меню ===");
            System.out.println("Введите ID-номер пользователя для входа в его меню");
            System.out.print("--> ");
            Scanner input = new Scanner(System.in);
            String id = input.nextLine();

            if (EXIT.equals(id)) {
                return false;
            }

            if (checkSyntax(id, ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                int idInt = Integer.valueOf(id);

                User read = getUserService().read(idInt);

                if (read != null) {

                    System.out.println("==== Вы находитесь в меню пользователя " + read.getLogin() + " ====" +
                            "\nЗдесь Вы можете купить и сдать для него билеты\n");

                    controllerFactory.getController(read).start();

                    return true;
                }
                else {
                    System.out.println("Пользователя с таким ID не существует.\n");
                }
            }
            else {
                System.out.println("Вы ввели некорретный номер.\n");
            }
        }
    }

}
