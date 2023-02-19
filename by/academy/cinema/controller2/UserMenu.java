/**
 * Бесполезный такой класс просто что бы писать какой пользователь активен
 */

package by.academy.cinema.controller2;

import by.academy.cinema.model.Context;

import java.util.List;

public class UserMenu extends Menu{


    public UserMenu(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
    }

    protected void menuAdditionalText() {
        System.out.println("ПОЛЬЗОВАТЕЛЬ: " + getContext().getUser().getLogin());
    }
}
