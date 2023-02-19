/**
 * Класс содержит методы для проверки корректности вводимых данных
 */

package by.academy.cinema.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckSyntax {

    public static boolean checkSyntax(String text, int min, int max, CheckType checkType) {
        final int SYMBOLS_MIN = min;
        final int SYMBOLS_MAX = max;
        final String key = getCheckKey(checkType);
        final String patternKey = key + "{" + SYMBOLS_MIN + "," + SYMBOLS_MAX + "}";
        Pattern pattern = Pattern.compile(patternKey);
        Matcher matcher = pattern.matcher(text);
        boolean match = matcher.matches();
        return match;
    }

    public static boolean checkSyntax(String text, CheckType checkType) {
        final String key = getCheckKey(checkType);
        final String patternKey = key;
        Pattern pattern = Pattern.compile(patternKey);
        Matcher matcher = pattern.matcher(text);
        boolean match = matcher.matches();
        return match;
    }

    private static String getCheckKey(CheckType checkType) {
        switch (checkType.toString()) {
            case "LOGIN":
                return "[a-zA-Zа-яА-Я0-9]";
            case "PASSWORD":
                return "[a-zA-Zа-яА-Я0-9]";
            case "ID":
                return "[0-9]";
            case "SEAT":
                return "[0-9]";
            case "MOVIE_TITLE":
                return "[a-zA-Zа-яА-Я0-9 ,:]";
            case "MOVIE_TIME":
                return "[0-9]";
            case "PRICE":
                return "[0-9]{1,4}.[0-9]{1,2}";
            default:
                return "";
        }
    }
}
