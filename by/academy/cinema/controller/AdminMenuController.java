/**
 * Класс для отображению меню АДМИНИСТРАТОРА
 */

package by.academy.cinema.controller;

import by.academy.cinema.model.Event;
import by.academy.cinema.model.Movie;
import by.academy.cinema.model.Ticket;
import by.academy.cinema.model.User;
import by.academy.cinema.service.EventService;
import by.academy.cinema.service.MovieService;
import by.academy.cinema.service.TicketService;
import by.academy.cinema.service.UserService;

import java.util.Scanner;

import static by.academy.cinema.controller.MainMenuController.EXIT;

public class AdminMenuController extends CommonManagementController{


    public AdminMenuController(User user,
                               UserService userService,
                               EventService eventService,
                               MovieService movieService,
                               TicketService ticketService) {
        super(user, userService, eventService, movieService, ticketService);
    }

    @Override
    public void start() {
        while (true) {
            System.out.println("\n=-=-=-= МЕНЮ НАИГЛАВНЕЙШЕГО АДМИНИСТРАТОРА =-=-=-=");
            System.out.println("1 - Показать все фильмы");
            System.out.println("2 - Показать все мероприятия");
            System.out.println("3 - Показать все пользователей");
            System.out.println("4 - Редактировать мероприятие");
            System.out.println("5 - Редактировать фильм");
            System.out.println("6 - Редактировать пользователя");
            System.out.println("7 - Удалить мероприятие");
            System.out.println("8 - Удалить фильм (со всеми вытекающими..)");
            System.out.println("9 - Удалить пользователя");
            System.out.println("10 - Создать пользователя");
            System.out.println("11 - Создать фильм");
            System.out.println("12 - Создать мероприятие");
            System.out.println("\n0 - Возврат в предыдущее меню с завершением авторизации");
            System.out.print("-->");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            boolean result = false;

            switch (input) {
                case "1":
                    System.out.println("\n=== Список фильмов ===");
                    result = printMovies();
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
                    System.out.println(result ? "" : "Пользователей нет\n");
                    break;
                case "4":

                    break;
                case "5":

                    break;
                case "6":

                    break;
                case "7":

                    break;
                case "8":

                    break;
                case "9":

                    break;
                case "10":

                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Вы ввели недопустимое значение!!! Попробуйте ещё раз!\n");
                    break;
            }

        }
    }
}
