/*
task 2
Viachaslau Chzyhyk
 */

package by.academy.task_2.data;

public class Rainbow {

    private static String COLOR_RED = "Red";
    private static String COLOR_ORANGE = "Orange";
    private static String COLOR_YELLOW = "Yellow";
    private static String COLOR_GREEN = "Green";
    private static String COLOR_BLUE = "Blue";
    private static String COLOR_INDIGO = "Indigo";
    private static String COLOR_VIOLET = "Violet";
    private static String COLOR_NOT_FOUND = "Color not found";
    private static int MAX_INDEX_COLOR = 7;
    private static int MIN_INDEX_COLOR = 1;

    public String getColor(int a) {
        String result = "";

        switch (a) {
            case 1:
                result += COLOR_RED;
                break;
            case 2:
                result += COLOR_ORANGE;
                break;
            case 3:
                result += COLOR_YELLOW;
                break;
            case 4:
                result += COLOR_GREEN;
                break;
            case 5:
                result += COLOR_BLUE;
                break;
            case 6:
                result += COLOR_INDIGO;
                break;
            case 7:
                result += COLOR_VIOLET;
                break;
            default:
                result += COLOR_NOT_FOUND;
        }
        return result;
    }

    // Перегрузка метода
    public String getColor(int a, int b) {
        String result = "";

        // Проверка на существование комбинации цветов
        if ((a > MAX_INDEX_COLOR) || (b > MAX_INDEX_COLOR) || (a < MIN_INDEX_COLOR) || (b < MIN_INDEX_COLOR)) {
            return COLOR_NOT_FOUND;
        } else

        // Если коды цветов одинаковы, то воспринимаем как один код
        if (a == b) {
            result += getColor(a);
            return result;
        }

        // Для обработки каждого цвета вызываем стандартный наш метод
        result += getColor(a) + "-" + getColor(b);
        return result;
    }


}
