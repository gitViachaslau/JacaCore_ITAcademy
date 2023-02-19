package by.academy.task_6;

import by.academy.task_6.port.Port;
import by.academy.task_6.ship.Ship;

public class Main {
    public static void main(String[] args) {
        // Создаём порт
        Port port = new Port();
        // Создаём корабли
        port.addShip("Мудяжный", 2);
        port.addShip("Отчаянный", 1);
        port.addShip("Элегантный", 1);
        port.addShip("Сверепый", 2);
        port.addShip("Терпимый", 1);

        System.out.println("Добавление палубы произошло " + (port.getShips()[0].addDeck() ? "успешно" : "неудачно"));
        System.out.println(port.toString());

        port.getShips()[0].removeDeck();
        System.out.println("Удаление палубы произведено ");

// Добавим контейнеры на палубы кораблей
        port.getShips()[0].getDeck()[0].addContainerToDeck("Cone", false, 100, 1000);

        port.getShips()[1].getDeck()[0].addContainerToDeck("Square", true, 10, 2000);

        port.getShips()[2].getDeck()[0].addContainerToDeck("Cylinder", true, 100, 2000);

        port.getShips()[3].getDeck()[0].addContainerToDeck("Square", true, 100, 1000);

        port.getShips()[3].getDeck()[1].addContainerToDeck("Cylinder", false, 10, 1000);

        port.getShips()[4].getDeck()[0].addContainerToDeck("Cone", true, 10, 2000);

        System.out.println(port.toString());
        System.out.println("Общая масса груза в порту: " + port.getFullWeight());

        // Удалим груз на палубе 2 корабля 4
        port.getShips()[3].getDeck()[1].removeContainerToDeck();

        System.out.println(port.toString());
        System.out.println("Общая масса груза в порту: " + port.getFullWeight());

        // Удалим корабль
        port.removeShip("Сверепый");

        System.out.println(port.toString());
        System.out.println("Общая масса груза в порту: " + port.getFullWeight());

        // Опять добавим корабль
        port.addShip("Утюжный", 2);

        // Опять добавляем контейнеры на палубу
        port.getShips()[3].getDeck()[0].addContainerToDeck("Cylinder", true, 10, 1000);
        port.getShips()[3].getDeck()[0].addContainerToDeck("Cylinder", true, 10, 1000);


        System.out.println(port.toString());
        System.out.println("Общая масса груза в порту: " + port.getFullWeight());
    }
}
