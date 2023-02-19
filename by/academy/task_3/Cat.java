public class Cat {

    private String name;
    private int age;

    static final int MIN_AGE_CAT = 1;
    static final int MAX_AGE_CAT = 28;

    // конструктор по умолчанию
    Cat() {
        name = "Tiran";
        age = 11;
    }

    // Конструктор с аргументами
    Cat(String name, int age) {
        // Проверяем что возраст кота в допустимом диапазоне, если нет, возраст устанавливаем по умолчанию
        if (age >= MIN_AGE_CAT && age <= MAX_AGE_CAT) {
            this.name = name;
            this.age = age;
        } else {
            this.name = name;
            this.age = 11;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (age != cat.age) return false;
        return name.equals(cat.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        return result;
    }
}
