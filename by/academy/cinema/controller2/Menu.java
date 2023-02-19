/**
 * Класс описывает менюшку
 *
 * menuItems - вложенные менюшки (возможные действия)
 */

package by.academy.cinema.controller2;

import by.academy.cinema.model.Context;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AbstractMenuItem {

    // Список наших возможностей в меню
    private List<AbstractMenuItem> menuItems;


    public Menu(String title, String executeCommand, Context context) {
        super(title, executeCommand, context);
        menuItems = new ArrayList<>();
    }


    public Menu addItem(AbstractMenuItem item) {
        menuItems.add(item);
        return this;
    }


    protected void menuAdditionalText() {}

    protected boolean preStart() {
        return true;
    }
    protected void postStart() {}

    private void drawMenu() {
        System.out.println("\n=-=-=-= " + getTitle() + " =-=-=-=");
        menuAdditionalText();
        menuItems.forEach(i -> System.out.println(i.getExecuteCommand() + " - " + i.getTitle()));
        System.out.println("\n"+ EXIT_COMMAND +" - Возврат в предыдущее меню");
        System.out.print("-->");
    }

    @Override
    public void start() {
        if(!preStart()) {
            return;
        }
        while (true) {
            drawMenu();
            String input = getUserInput();
            if (input.equals(EXIT_COMMAND)) {
                postStart();
                return;
            }
            AbstractMenuItem item = menuItems.stream().filter(i -> i.getExecuteCommand().equals(input)).findFirst().orElse(null);
            if (item != null) {
                item.start();
                System.out.println("");
            } else {
                System.out.println("Вы ввели недопустимое значение!!! Попробуйте ещё раз!\n");
            }
        }
    }
}
