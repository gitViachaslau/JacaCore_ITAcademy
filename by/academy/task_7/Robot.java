package by.academy.task_7;

public abstract class Robot {
    private String name;
    private Head head;
    private Body body;


    public Robot(String name, Head head, Body body) {
        this.name = name;
        this.head = head;
        this.body = body;
    }

    @Override
    public String toString() {
        return "***\nИмя: " + name + ". Тело: " + body.getName() + ". Голова: " + head.getName();
    }
}
