package by.academy.task_7;

public class RobotTypeThree extends Robot implements Action{

    private final String typeMove = "Робот летает";
    private final String typeFight = "Робот атакует фекалиями";

    public RobotTypeThree(String name, Head head, Body body) {
        super(name, head, body);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + typeMove + ".\n" + typeFight + "\n***";
    }

    @Override
    public String fight() {
        return "Производит сброс фекалий на неприятеля.";
    }

    @Override
    public String move() {
        return "Летит со звуком вжжжжж..";
    }
}
