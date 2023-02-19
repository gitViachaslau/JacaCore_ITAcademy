/*
Задание 1
 */

package by.academy.homework_1;

public class Main {

    // Объявляем несколько статических переменных

    static final boolean MY_REPLY = true;
    static byte byteEasyTypeStatic;

    // Объявлем примитивные типы

    boolean boolEasyType;
    byte byteEasyType = 14;
    short shorEasyType;
    int intEasyType;
    long longEasyType;
    float floatEasyType = 44.22f;
    double doubleEasyType;
    char charEasyType;

    // Объявляем классы-обёртки

    Boolean boolObjectType;
    Byte byteObjectType;
    Short shortObjectType;
    Integer intObjectType = 777;
    Long longObjectType;
    Float floatObjectType;
    Double doubleObjectType;
    Character charObjectType;
    String stringObjectType;

    public static void main(String[] args) {
        Main object1 = new Main();
        Variables object2 = new Variables();

        // Объявлем примитивные типы

        boolean boolEasyTypeInMethod = false;
        byte byteEasyTypeInMethod = 124;
        short shorEasyTypeInMethod = 5555;
        int intEasyTypeInMethod = 338856;
        long longEasyTypeInMethod = 45532513545125L;
        // float floatEasyTypeInMethod = 77.77;  Нужно явно указать что это число float иначе компилятор будет думать что double
        float floatEasyTypeInMethod = 77.77f;
        double doubleEasyTypeInMethod = 88.88565;
        char charEasyTypeInMethod = 'U';

        // Объявляем классы-обёртки

        Boolean boolObjectTypeInMethod = false;
        Byte byteObjectTypeInMethod = (byte) 33;
        Short shortObjectTypeInMethod = (short) 555;
        Integer intObjectTypeInMethod = 638745;
        Long longObjectTypeInMethod = 15454564215L;
        Float floatObjectTypeInMethod = 66.25f;
        Double doubleObjectTypeInMethod = 458.201;
        Character charObjectTypeInMethod = 'G';
        String stringObjectTypeInMethod = new String("Бла бла бла");

        // инициализируем какини-нибудь значениями поля объёкта object2

        object2.setBoolObjectType(true);
        object2.setBoolEasyType(false);
        object2.setByteObjectType((byte)22);
        object2.setByteObjectType((byte)33);
        object2.setCharObjectType('N');
        object2.setDoubleObjectType(33.555);
        object2.setDoubleEasyType(85.255);
        object2.setFloatObjectType(89.10f);
        object2.setFloatEasyType(999.33f);
        object2.setIntObjectType(1024);
        object2.setIntEasyType(5553);
        object2.setShortObjectType((short) 551);
        object2.setShorEasyType((short) 321);
        object2.setLongEasyType(3265688972L);
        object2.setLongObjectType(4545424534L);
        object2.setCharObjectType('X');
        object2.setStringObjectType("Уруру");

        System.out.println("\nВыводим установленные по умолчанию компилятором знаечния в классе Main:\n" +
                "MY_REPLY - " + MY_REPLY + "\n" + "byteEasyTypeStatic - " + byteEasyTypeStatic + "\n"+
                "boolEasyType - " + object1.boolEasyType + "\n" + "byteEasyType - " + object1.byteEasyType + "\n" +
                "shorEasyType - " + object1.shorEasyType + "\n" + "intEasyType - " + object1.intEasyType + "\n" +
                "longEasyType - " + object1.longEasyType + "\n" + "floatEasyType - " + object1.floatEasyType + "\n" +
                "doubleEasyType - " + object1.doubleEasyType + "\n" + "charEasyType - " + object1.charEasyType + "\n" +
                "boolObjectType - " + object1.boolObjectType + "\n" +"byteObjectType - " + object1.byteObjectType + "\n" +
                "shortObjectType - " + object1.shortObjectType + "\n" + "intObjectType - " + object1.intObjectType + "\n" +
                "longObjectType - " + object1.longObjectType + "\n" + "floatObjectType - " + object1.floatObjectType + "\n" +
                "doubleObjectType - " + object1.doubleObjectType + "\n" + "charObjectType - " + object1.charObjectType + "\n" +
                "stringObjectType - " + object1.stringObjectType);

        System.out.println("\nВыводим установленные через сеттеры знаечения в классе Variables:\n" +
                "boolEasyType - " + object2.isBoolEasyType() + "\n" + "byteEasyType - " + object2.getByteEasyType() + "\n" +
                "shorEasyType - " + object2.getShorEasyType() + "\n" + "intEasyType - " + object2.getIntEasyType() + "\n" +
                "longEasyType - " + object2.getLongEasyType() + "\n" + "floatEasyType - " + object2.getLongEasyType() + "\n" +
                "doubleEasyType - " + object2.getDoubleEasyType() + "\n" + "byteObjectType - " + object2.getByteObjectType()+ "\n" +
                "shortObjectType - " + object2.getShortObjectType() + "\n" + "intObjectType - " + object2.getIntObjectType() + "\n" +
                "longObjectType - " + object2.getLongObjectType() + "\n" + "floatObjectType - " + object2.getFloatObjectType() + "\n" +
                "doubleObjectType - " + object2.getDoubleObjectType() + "\n" + "charObjectType - " + object2.getCharObjectType() + "\n" +
                "stringObjectType - " + object2.getStringObjectType());


        // Main.MY_REPLY = false; - нельзя изменить константу
        Main.byteEasyTypeStatic = (byte) (byteEasyTypeInMethod - object2.getByteObjectType());
        System.out.println(byteEasyTypeInMethod + " - " + object2.getByteObjectType() + " = " + Main.byteEasyTypeStatic);

        object1.intEasyType = object2.getIntEasyType() + object2.getIntEasyType() + intEasyTypeInMethod + intObjectTypeInMethod;
        System.out.println(object2.getIntEasyType() + " + " + object2.getIntEasyType() + " + " + intEasyTypeInMethod + " + " +
                intObjectTypeInMethod + " = " +object1.intEasyType);

        // object2.setIntEasyType(object1.longEasyType); - несовместимые типы нужно использовать приведение
        object2.setIntEasyType((int)object1.longEasyType);
        object1.charEasyType = (char)byteEasyTypeInMethod;

        String str1 = object1.stringObjectType + " " + stringObjectTypeInMethod + " " + object2.getStringObjectType();
        System.out.println(str1);

        object2.setFloatObjectType((float)(object2.getDoubleObjectType() + object2.getDoubleEasyType()));
        System.out.println(object2.getFloatObjectType());

        object2.setCharObjectType(charObjectTypeInMethod);

        byteEasyTypeInMethod = (byte)shorEasyTypeInMethod;

        object2.setIntEasyType((int)floatEasyTypeInMethod);

        floatObjectTypeInMethod = 11.11f;

        stringObjectTypeInMethod = "22";

        longObjectTypeInMethod = Long.parseLong(stringObjectTypeInMethod);

    }
}
