/**
 * Самый такой главный класс
 */

package by.academy.cinema;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.controller2.AuthorizationMenu;
import by.academy.cinema.controller2.Menu;
import by.academy.cinema.controller2.MenuItem;
import by.academy.cinema.controller2.UserMenuInMenu;
import by.academy.cinema.controller2.UserMenu;
import by.academy.cinema.controller2.items.*;
import by.academy.cinema.model.*;
import by.academy.cinema.repository.*;
import by.academy.cinema.repository.mysql.EventRepositoryImpl;
import by.academy.cinema.repository.mysql.MovieRepositoryImpl;
import by.academy.cinema.repository.mysql.TicketRepositoryImpl;
import by.academy.cinema.repository.mysql.UserRepositoryImpl;
import by.academy.cinema.service.EventService;
import by.academy.cinema.service.MovieService;
import by.academy.cinema.service.TicketService;
import by.academy.cinema.service.UserService;
import by.academy.cinema.service.impl.EventServiceImpl;
import by.academy.cinema.service.impl.MovieServiceImpl;
import by.academy.cinema.service.impl.TicketServiceImpl;
import by.academy.cinema.service.impl.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        // Создаю репозитории для работы с БД

        UserRepository userRepository = new UserRepositoryImpl();
        MovieRepository movieRepository = new MovieRepositoryImpl();
        EventRepository eventRepository = new EventRepositoryImpl();
        TicketRepository ticketRepository = new TicketRepositoryImpl();

        // Создаю сервисы для работы с данными от/в репозитории

        UserService userService = new UserServiceImpl(userRepository);
        TicketService ticketService = new TicketServiceImpl(ticketRepository);
        MovieService movieService = new MovieServiceImpl(movieRepository);
        EventService eventService = new EventServiceImpl(eventRepository);

        // Забрасываю все сервисы в отдельный класс для более удобного использования и вызова необхдимых методов

        Context context = new Context().setEventService(eventService)
                .setMovieService(movieService)
                .setTicketService(ticketService)
                .setUserService(userService);

        // Создаю менюшку для обыкновенного пользователя

        MenuItem userMenu = new UserMenu("МЕНЮ ПОЛЬЗОВАТЕЛЯ", null, context)
                .addItem(new ShowUserEvents("Список доступных билетов пользователя", "1", context))
                .addItem(new ShowUserTickets("Список купленных билетов пользователя", "2", context))
                .addItem(new BuyTicket("Покупка билета на мероприятие для пользователя", "3", context))
                .addItem(new ReturnTicket("Сдача билета на мероприятие пользователя", "4", context));

        // Создаю менюшку для менеджера

        MenuItem managerMenu = new UserMenu("МЕНЮ МЕНЕДЖЕРА", null, context)
                .addItem(new ShowMovies("Список фильмов", "1", context))
                .addItem(new ShowEvents("Список мероприятий", "2", context))
                .addItem(new ShowUsers("Список пользователей", "3", context))
                .addItem(new UserMenuInMenu("Работа в меню пользователя", "4", context)
                        .addItem(new ShowUserEvents("Список доступных билетов пользователя", "1", context))
                        .addItem(new ShowUserTickets("Список купленных билетов пользователя", "2", context))
                        .addItem(new BuyTicket("Покупка билета на мероприятие для пользователя", "3", context))
                        .addItem(new ReturnTicket("Сдача билета на мероприятие пользователя", "4", context)))
                .addItem(new EditMovie("Редактирование фильма", "5", context))
                .addItem(new EditEvent("Редактирование мероприятия", "6", context));

        // Создаю менюшку для администратора

        MenuItem adminMenu = new UserMenu("МЕНЮ НАИГЛАВНЕЙШЕГО АДМИНИСТРАТОРА", null, context)
                .addItem(new ShowMovies("Список фильмов", "1", context))
                .addItem(new ShowEvents("Список мероприятий", "2", context))
                .addItem(new ShowUsers("Список пользователей", "3", context))
                .addItem(new CreateNewMovie("Создать новый фильм", "4", context))
                .addItem(new CreateNewEvent("Создать новое мероприятие", "5", context))
                .addItem(new CreateNewUser("Зарегистрировать нового пользователя", "7", context))
                .addItem(new EditMovie("Редактирование фильма", "8", context))
                .addItem(new EditEvent("Редактирование мероприятия", "9", context));

        // Создаю класс для проведения авторизации с вложенными менюшками созданных выше пользователей

        AbstractMenuItem authorizationMenu = new AuthorizationMenu("Авторизация пользователя", "1", context)
                .addChooseMenu(UserType.USER, userMenu)
                .addChooseMenu(UserType.MANAGER, managerMenu)
                .addChooseMenu(UserType.ADMIN, adminMenu);


        // Создаю первичную менюшку

        MenuItem mainMenu = new Menu("Кинотеатр \"Наследие Михалкова\"", null, context)
                .addItem(authorizationMenu)
                .addItem(new CreateNewUser("Регистрация пользователя", "2", context));

        // Запускаю первичную менюшку

        mainMenu.start();


//        User manager = new User("manager", "11111111", UserType.MANAGER);
//        User admin = new User("administrator", "11111111", UserType.ADMIN);
//
//        userRepository.create(manager);
//        userRepository.create(admin);

//        System.out.println(ticketRepository.getCountFreeTicketsForEvent(2));


        /*Controller controller = new MainMenuController(userService, ticketService, movieService, eventService);

        controller.start();*/

//        Movie movie1 = new Movie("Титаник", 120);
//        Movie movie2 = new Movie("Крепкий орешек", 100);
////
//        movieRepository.create(movie1);
//        movieRepository.create(movie2);
//
//        Event event1 = new Event(2, 1, LocalDateTime.of(2022,06,20,13,30),10.20);
//        Event event2 = new Event(2, 1, LocalDateTime.of(2022,06,20,11,30),11.20);
//        Event event3 = new Event(2, 2, LocalDateTime.of(2022,06,21,13,30),22.20);
//        Event event4 = new Event(2, 2, LocalDateTime.of(2022,06,22,13,30),12.20);
//
//        eventRepository.create(event1);
//        eventRepository.create(event2);
//        eventRepository.create(event3);
//        eventRepository.create(event4);
//
//        Ticket ticket1 = new Ticket(1, 1, false);
//        Ticket ticket2 = new Ticket(1, 2, false);
//
//        Ticket ticket3 = new Ticket(2, 1, false);
//        Ticket ticket4 = new Ticket(2, 2, false);
//
//        Ticket ticket5 = new Ticket(3, 1, false);
//        Ticket ticket6 = new Ticket(3, 2, false);
//
//        Ticket ticket7 = new Ticket(4, 1, false);
//        Ticket ticket8 = new Ticket(4, 2, false);
//
//        ticketRepository.create(ticket1);
//        ticketRepository.create(ticket2);
//        ticketRepository.create(ticket3);
//        ticketRepository.create(ticket4);
//        ticketRepository.create(ticket5);
//        ticketRepository.create(ticket6);
//        ticketRepository.create(ticket7);
//        ticketRepository.create(ticket8);

    }
}



