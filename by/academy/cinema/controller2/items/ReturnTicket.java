/**
 * Класс для возврат билета
 */

package by.academy.cinema.controller2.items;

import by.academy.cinema.controller2.AbstractMenuItem;
import by.academy.cinema.model.Context;
import by.academy.cinema.model.Ticket;
import by.academy.cinema.util.CheckType;


import static by.academy.cinema.util.CheckSyntax.checkSyntax;

public class ReturnTicket extends AbstractMenuItem {
    private static String EXIT_COMMAND = "0";

    public ReturnTicket(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    @Override
    public void start() {
        final int ID_SYMBOL_MIN = 1;
        final int ID_SYMBOL_MAX = 8;

        System.out.println("\n=== Сдача билета на мероприятие пользователя " + getContext().getUser().getLogin() + " ===");

        while (true) {
            System.out.println("=== " + EXIT_COMMAND + " - Возврат  предыдущее меню ===");
            System.out.println("Введите ID-номер билета для возврата");
            System.out.print("--> ");
            String id = getUserInput();

            if (EXIT_COMMAND.equals(id)) {
                return;
            }

            if (checkSyntax(id, ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                int idInt = Integer.valueOf(id);

                if (getContext().getTicketService().checkIdTicket(idInt)) {

                    Ticket ticket = getContext().getTicketService().read(idInt);

                    if (ticket.getIdUser().equals(String.valueOf(getContext().getUser().getId()))) {

                        boolean result = getContext().getTicketService().returnTicket(idInt);
                        System.out.println(result ? "Билет удачно сдан" : "Сдать билет не удалось не удалась\n");
                        return;
                    }
                    else{
                        System.out.println("У Вас нет билета с таким ID\n");
                    }
                }

                else{
                    System.out.println("Билета с таким ID не существует.\n");
                }
            }
            else{
                System.out.println("Вы ввели некорретный номер.\n");
            }
        }
    }
}
