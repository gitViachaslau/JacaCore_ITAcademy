/**
 * Класс для редактирования мероприятия
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Event;
import by.academy.cinema.util.CheckType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static by.academy.cinema.util.CheckSyntax.checkSyntax;

public class EditEvent extends AbstractMenuItem {



    public EditEvent(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {
        System.out.println("\n=== Редактирование мероприятия ===");

        final int ID_SYMBOL_MIN = 1;
        final int ID_SYMBOL_MAX = 8;

        while (true) {
            System.out.println("=== 0 - Возврат  предыдущее меню ===");
            System.out.println("Введите ID-номер мероприятия для редактирования");
            System.out.print("--> ");

            String id = getUserInput();

            if (EXIT_COMMAND.equals(id)) {
                return;
            }


            if (checkSyntax(id, ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                int idInt = Integer.valueOf(id);

                if (getContext().getEventService().checkIdEvent(idInt)) {

                    Event eventOld = getContext().getEventService().readEvent(idInt);

                    String newIdMovie;
                    LocalDateTime newDateTime;
                    String newPrice;

                    System.out.println("Введите ID-номер нового фильма (или старый =) )" +
                            "\nСтарый ID-номер фильма: " + eventOld.getIdMovie());
                    System.out.print("--> ");

                    newIdMovie = getUserInput();

                    if (EXIT_COMMAND.equals(newIdMovie)) {
                        return;
                    }

                    if (checkSyntax(String.valueOf(newIdMovie), ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                        if (getContext().getMovieService().checkIdMovie(Integer.parseInt(newIdMovie))) {

                            System.out.println("Введите новую стоиомость фильма (или старую =) )" +
                                    "\nСтарая стоимость фильма: " + eventOld.getPrice());
                            System.out.print("--> ");

                            newPrice = getUserInput();

                            if (EXIT_COMMAND.equals(newPrice)) {
                                return;
                            }

                            if (checkSyntax(String.valueOf(newPrice), CheckType.PRICE)) {

                                String DATE_TIME_PATTERN = "dd.MM.yyyy HH-mm";
                                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

                                String oldDateTime = eventOld.getDateTimeStart().format(dateTimeFormatter);

                                System.out.println("Введите новую дату и время начала мероприятия в формате  ДД.MM.ГГГГ ЧЧ-ММ " +
                                        "(или старую =) )" +
                                        "\nСтарая дата и время начала мероприятия: " + oldDateTime);
                                System.out.print("--> ");

                                String inputDateTime = getUserInput();

                                if (EXIT_COMMAND.equals(inputDateTime)) {
                                    return;
                                }

                                try{
                                    newDateTime = LocalDateTime.parse(inputDateTime, dateTimeFormatter);
                                } catch (Exception e) {
                                    newDateTime = null;
                                }

                                if (newDateTime != null) {

                                    boolean result = getContext().getEventService().update(eventOld, new Event(eventOld.getPcsTicket(),
                                            Integer.parseInt(newIdMovie), newDateTime, Double.parseDouble(newPrice)));

                                    System.out.println(result ? "Мероприятие отредактировано" : "Редактирование мероприятия не удалось\n");
                                    return;
                                }
                                else{
                                    System.out.println("Вы ввели дату и время несоответсвующее формату!");
                                }
                            }
                            else{
                                System.out.println("Вы ввели некорректную стоимость!");
                            }
                        }
                        else{
                            System.out.println("Вы ввели ID не существующего фильма!");
                        }
                    }
                    else{
                        System.out.println("Вы ввели некорректный ID фильма");
                    }
                }
                else{
                    System.out.println("Вы ввели ID не сущетсуующего мероприятия либо дата показа уже прошла");
                }
            }
            else {
                System.out.println("Вы ввели некорректный ID мероприятия!");
            }
        }
    }
}
