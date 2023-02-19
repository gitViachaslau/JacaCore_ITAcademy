/*
task 2
Viachaslau Chzyhyk
 */

package by.academy.task_2.app;

import by.academy.task_2.data.Rainbow;

public class Main {
    public static void main(String[] args) {
        int a = getRandomNumber();
        int b = getRandomNumber();
        int c = getRandomNumber();

        Rainbow rainbow = new Rainbow();

        System.out.println("Игра СГЕНЕРИМ РАДУГУ Версия 1.0\n");

        System.out.println("Код цвета: " + a + " --> " + rainbow.getColor(a));
        System.out.println("Код цвета: " + b + ", " + c + " --> " + rainbow.getColor(b, c));

    }

    // Генератор рандомных чисел для теста
    private static int getRandomNumber() {
        return (int) (Math.random() * 10) + 1;
    }
}
