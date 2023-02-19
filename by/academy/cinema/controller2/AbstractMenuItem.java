/**
 * Абстрактный класс для описания менюшки
 *
 * title - текст названия менюшки
 * executeCommand - цифра по которой менющка подгрузится
 * context - объект с текущим пользователем и сервисами
 */

package by.academy.cinema.controller2;

import by.academy.cinema.model.Context;

import java.util.Scanner;

public abstract class AbstractMenuItem implements MenuItem {
    protected static String EXIT_COMMAND = "0";

    private String title;

    private String executeCommand;

    private Context context;

    public AbstractMenuItem(String title, String executeCommand, Context context) {
        this.title = title;
        this.executeCommand = executeCommand;
        this.context = context;
    }

    public String getUserInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public String getTitle() {
        return title;
    }

    public String getExecuteCommand() {
        return executeCommand;
    }

    public Context getContext() {
        return context;
    }
}
