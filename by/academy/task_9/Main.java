package by.academy.task_9;

import by.academy.task_9.exceptions.IncorrectArraySizeException;
import by.academy.task_9.exceptions.IncorrectValueException;
import by.academy.task_9.exceptions.WordValueException;

public class Main {
    public static void main(String[] args) {
        int counter = 0;
        String word = "Fuck";
        int[] arr = new int[]{1, 2, 3, 0};

        if (checkValue(arr)) {
            System.out.println("Значения прошли проверку положительно");
        } else {
            System.out.println("Значения не прошли проверку");
        }

        try {
            counter = print(word);
            System.out.println("Вы ввели слово: " + word);
        } catch (WordValueException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Работа программы окончена");
        }

    }

    static boolean checkValue(int[] value) {
        boolean result = true;
        final int MAX_VALUE = 5;
        try {
            if (value.length >= MAX_VALUE) {
                throw new IncorrectArraySizeException("Размерность " + value.length + " недопустима");
            }
            for (int i : value) {
                try {
                    if (i == 0) {
                        throw new IncorrectValueException("Нулевые значения недопустимы");
                    }
                } catch (IncorrectValueException e) {
                    result = false;
                    e.printStackTrace();
                }
            }
        } catch (IncorrectArraySizeException e) {
            result = false;
            e.printStackTrace();
        } finally {
            return result;
        }
    }


    static int print(String str) throws WordValueException {
        if (str.equals("Fuck")) {
            throw new WordValueException("Такие слова недопустимы!");
        }
        return str.length();
    }
}


