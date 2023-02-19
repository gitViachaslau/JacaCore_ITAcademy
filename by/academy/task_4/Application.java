/*
Домашнее задание №4
 */
package by.academy.task_4;

import java.util.Arrays;

public class Application {
    // Размерность массива
    static private final int ARRAY_SIZE = 10;
    // Количество символов для элемента массива
    static private final int SYMBOL_PCS = 7;
    // Позиция в массиве для вещественного элемента
    static final int POS_NUMBER = 3;

    public static void main(String[] args) {
        // Исходный массив
        String[][] array;
        // Массив с диоганалями
        String[] arrayDioganals;
        // Генерируем массив исходный рандомом
        array = createArray(ARRAY_SIZE, POS_NUMBER);
        System.out.println("Сгенерирован массив:");
        // Выводим на печать исходный массив
        print(array);
        // Создаём масссив содержащий диоганали
        arrayDioganals = createArrayDioganals(array);
        System.out.println("\nСгенерирован массив на основе диоганалей:");
        // Выводим на печать массив с диоганалями
        print(arrayDioganals);
        // Проверяем что их элементов является строкой а что числом и производим действия согласно заданию
        checkStringOrDouble(arrayDioganals);


    }

    // Проверка элементов на тип: текст или число. Действия согласно заданию
    static private void checkStringOrDouble(String[] array) {
        int[] arrayNumber;
        int counter = 0;
        int index = 0;

        System.out.println("\nВыводим всё что есть строки (выводим со 2-го по 4-й элемент):");
        for (int i = 0; i < array.length; i++) {
            if (array[i].indexOf(".") == -1) {
                StringBuilder str = new StringBuilder(array[i].substring(2, 5));
                System.out.print(str + ", ");
                counter++;
            }
        }

        arrayNumber = new int[array.length - counter];
        System.out.println("\n\nВыводим всё что есть число :");
        for (int i = 0; i < array.length; i++) {
            if (array[i].indexOf(".") != -1) {
                double d = Double.parseDouble(array[i]);
                if (d > 1.7) {
                    arrayNumber[index] = (int) Math.ceil(d);
                } else {
                    arrayNumber[index] = (int) Math.floor(d);
                }
                index++;
            }
        }


        for (int i = 0; i < arrayNumber.length; i++) {
            if (i != arrayNumber.length - 1) {
                System.out.print(arrayNumber[i] + "_");
            } else {
                System.out.print(arrayNumber[i]);
            }
        }
    }


    // Проверка и вывод в консоль равенства диоганалей
    static private void printCheckEqualsDioganals(String[] arrOne, String[] arrTwo) {
        boolean result = Arrays.equals(arrOne, arrTwo);
        if (result) {
            System.out.println("\nДиоганали равны!");
        } else {
            System.out.println("\nДиоганали внезапно не равны =(");
        }
    }

    // Создание массива с диоганалями
    static private String[] createArrayDioganals(String[][] array) {
        String[] arrayOne = new String[array.length];
        String[] arrayTwo = new String[array.length];
        String[] resultArray = new String[arrayOne.length + arrayTwo.length];

        for (int i = 0, j = 0, count = 0; i < array.length; i++, j++) {
            arrayOne[count] = array[i][j];
            count++;
        }
        ;
        for (int i = 0, j = array[i].length - 1, count = 0; i < array.length; i++, j--) {
            arrayTwo[count] = array[i][j];
            count++;
        }

        printCheckEqualsDioganals(arrayOne, arrayTwo);

        for (int i = 0; i < arrayOne.length; i++) {
            resultArray[i] = arrayOne[i];
        }
        for (int i = 0; i < arrayTwo.length; i++) {
            resultArray[i + arrayOne.length] = arrayTwo[i];
        }
        return resultArray;
    }

    // Вывод на печать двумерного массива
    private static void print(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Вывод на печать одномерного массива
    private static void print(String[] array) {
        for (int j = 0; j < array.length; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.println();
    }

    // Создание сходного массива
    private static String[][] createArray(int size, int positionNumber) {
        int counter = 1;
        String[][] array = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (counter != 3) {
                    array[i][j] = getRandomText(SYMBOL_PCS);
                    counter++;
                } else {
                    array[i][j] = getRandomNumber(SYMBOL_PCS);
                    counter = 1;
                }
            }
        }
        return array;
    }

    // Метод для генерирования рандомных букв
    private static String getRandomText(int count) {
        String strSmall = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String result = "";
        for (int i = 1; i <= count; i++) {
            result += strSmall.charAt((int) (Math.random() * strSmall.length()));
        }
        return result;
    }

// Метод для генерирования рандомных вещественных чисел
    private static String getRandomNumber(int count) {
        int pcs = count - 2;
        String result = "";
        result += (int) (Math.random() * 2) + ".";
        for (int i = 0; i < pcs; i++) {
            result += (int) (Math.random() * 9);
        }
        return result;
    }
}
