public class Main {
    static final int CAT_CYCLE_PCS = 10;
    static final int CAT_ARRAY_PCS = 5;
    static final int CAT_MAX_GENERATE_SYMBOL_NAME = 5;

    public static void main(String[] args) {
        int counter = 1;

        // первый цикл while
        while (counter <= CAT_CYCLE_PCS) {
            Cat cat = new Cat();
            System.out.println("Порождён дефалтовый кот № " + counter + ", информация о коте: " + cat);
            counter++;
        }

        System.out.println();

        // второй цикл for
        for (counter = 1; counter <= CAT_CYCLE_PCS; counter++) {
            Cat cat = new Cat();
            cat.setAge(getRandomAge(Cat.MIN_AGE_CAT, Cat.MAX_AGE_CAT));
            cat.setName(getRandomName(CAT_MAX_GENERATE_SYMBOL_NAME));
            System.out.println("Порождён с использованием сеттера кот № " + counter + ", информация о коте: " +
                    "Имя: " + cat.getName() + ", Возраст: " + cat.getAge());
        }

        System.out.println();

        // трейтий цикл с do while
        counter = 1;
        do {
            Cat cat = new Cat(getRandomName(CAT_MAX_GENERATE_SYMBOL_NAME), getRandomAge(Cat.MIN_AGE_CAT, Cat.MAX_AGE_CAT));
            System.out.println("Порождён конструкторский кот № " + counter + ", информация о коте: " + cat);
            counter++;
        } while (counter <= CAT_CYCLE_PCS);

        System.out.println();

        // четвёртый цикл с массивом
        // Так как foreach может только перебирать массив, то задавать значения будем через стандартный for
        Cat[] catArray = new Cat[CAT_ARRAY_PCS];
        for (counter = 0; counter < catArray.length; counter++) {
            catArray[counter] = new Cat(getRandomName(CAT_MAX_GENERATE_SYMBOL_NAME), getRandomAge(Cat.MIN_AGE_CAT, Cat.MAX_AGE_CAT));
        }
        counter = 1;
        for (Cat cat : catArray) {
            System.out.println("Порождён массивный кот № " + counter + ", информация о коте: " + cat);
            counter++;
        }
    }

    // Генератор рандомных чисел для теста
    private static int getRandomAge(int minValue, int maxValue) {
        return (int) ((Math.random() * maxValue) + minValue);
    }

    // Генератор рандомных имён заданной длины
    private static String getRandomName(int count) {
        String strSmall = "abcdefghijklmnopqrstuvwxyz";
        String strBig = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String result = "";
        result += strBig.charAt((int) (Math.random() * strBig.length()));
        for (int i = 1; i <= count - 1; i++) {
            result += strSmall.charAt((int) (Math.random() * strSmall.length()));
        }
        return result;
    }


}
