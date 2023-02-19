/**
 * Хитрый такой класс для подгрузки меню пользователя менеджеру для действий менеджера под пользователем
 *
 * currUser - самая важная и хитрая штука тут не считая preStart() и postStart()
 */

package by.academy.cinema.controller2;

import by.academy.cinema.model.Context;
import by.academy.cinema.model.User;
import by.academy.cinema.util.CheckType;

import static by.academy.cinema.util.CheckSyntax.checkSyntax;

public class UserMenuInMenu extends Menu {

    // Сюда записываем менеджера перед работой в меню другого пользователя что бы потом опять стать менеджером
    private User currUser;

    public UserMenuInMenu(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    public void menuAdditionalText() {
        System.out.println("==== Вы находитесь в меню пользователя " + getContext().getUser().getLogin() + " ====" +
                "\nЗдесь Вы можете купить и сдать для него билеты\n");
    }

    public boolean preStart() {
        final int ID_SYMBOL_MIN = 1;
        final int ID_SYMBOL_MAX = 8;

        while (true) {
            System.out.println("=== 0 - Возврат  предыдущее меню ===");
            System.out.println("Введите ID-номер пользователя для входа в его меню");
            System.out.print("--> ");
            String id = getUserInput();

            if (EXIT_COMMAND.equals(id)) {
                return false;
            }

            if (checkSyntax(id, ID_SYMBOL_MIN, ID_SYMBOL_MAX, CheckType.ID)) {

                int idInt = Integer.valueOf(id);

                User read = getContext().getUserService().read(idInt);

                if (read != null) {

                    currUser = getContext().getUser();
                    getContext().setUser(read);

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
    public void postStart() {
        getContext().setUser(currUser);
    }


}
