/**
 * Класс для редактирования фильма
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Movie;
import by.academy.cinema.util.CheckType;


import static by.academy.cinema.util.CheckSyntax.checkSyntax;

public class EditMovie extends AbstractMenuItem {

    public EditMovie(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {

        System.out.println("\n=== Редактирование фильма ===");

        final int TITLE_SYMBOL_MIN = 1;
        final int TITLE_SYMBOL_MAX = 20;
        final int TIME_SYMBOL_MIN = 1;
        final int TIME_SYMBOL_MAX = 3;
        final int ID_SYMBOL_MIN = 1;
        final int ID_SYMBOL_MAX = 8;

        while (true) {
            System.out.println("=== 0 - Возврат  предыдущее меню ===");
            System.out.println("Введите ID-номер фильма для редактирования");
            System.out.print("--> ");
            String id = getUserInput();

            if (EXIT_COMMAND.equals(id)) {
                return;
            }

            if (checkSyntax(id, ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                int idInt = Integer.valueOf(id);

                if (getContext().getMovieService().checkIdMovie(idInt)) {

                    Movie movieOld = getContext().getMovieService().read(idInt);
                    String title;
                    String time;

                    System.out.println("Введите новое название для фильма (или старое =) )" +
                            "\nСтарое имя: " + movieOld.getTitle());
                    System.out.print("--> ");
                    title = getUserInput();

                    if (EXIT_COMMAND.equals(title)) {
                        return;
                    }

                    if (checkSyntax(title, TITLE_SYMBOL_MIN, TITLE_SYMBOL_MAX, CheckType.MOVIE_TITLE)) {

                        System.out.println("Введите новую продолжительность фильма (или старое =) )" +
                                "\nСтарая продолжительсноть: " + movieOld.getDurationMinutes());
                        System.out.print("--> ");

                        time = getUserInput();

                        if (checkSyntax(time, TIME_SYMBOL_MIN, TIME_SYMBOL_MAX, CheckType.MOVIE_TIME)) {
                            boolean result = getContext().getMovieService().update(movieOld, new Movie(title, Integer.valueOf(time)));
                            System.out.println(result ? "Фильм отредактирован" : "Редактирование фильма не удалось\n");
                            return;
                        }
                        else {
                            System.out.println("Вы ввели некорректное значение.\n");
                        }
                    }
                    else {
                        System.out.println("Вы ввели некорректное значение.\n");
                    }

                } else {
                    System.out.println("Фильма с таким ID не существует.\n");
                }
            } else {
                System.out.println("Вы ввели некорретный номер.\n");
            }
        }

    }
}
