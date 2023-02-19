/**
 * Класс для создания фильма
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Movie;
import by.academy.cinema.util.CheckType;

import static by.academy.cinema.util.CheckSyntax.checkSyntax;

public class CreateNewMovie extends AbstractMenuItem {

    public CreateNewMovie(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {

        System.out.println("\n=== Создание нового фильма ===");

        final int TITLE_SYMBOL_MIN = 1;
        final int TITLE_SYMBOL_MAX = 30;
        final int TIME_SYMBOL_MIN = 1;
        final int TIME_SYMBOL_MAX = 3;

        while (true) {
            System.out.println("=== 0 - Возврат  предыдущее меню ===");

            String title;
            String time;

            System.out.println("Введите название фильма ");
            System.out.print("--> ");

            title = getUserInput();

            if (EXIT_COMMAND.equals(title)) {
                return;
            }

            if (checkSyntax(title, TITLE_SYMBOL_MIN, TITLE_SYMBOL_MAX, CheckType.MOVIE_TITLE)) {

                System.out.println("Введите продолжительность фильма");
                System.out.print("--> ");

                time = getUserInput();

                if (checkSyntax(time, TIME_SYMBOL_MIN, TIME_SYMBOL_MAX, CheckType.MOVIE_TIME)) {

                    boolean result = getContext().getMovieService().create(new Movie(title, Integer.valueOf(time)));
                    System.out.println(result ? "Фильм создан" : "Создание фильма не удалось\n");
                    return;
                } else {
                    System.out.println("Вы ввели некорректное значение.\n");
                }
            } else {
                System.out.println("Вы ввели некорректное значение.\n");
            }
        }
    }
}
