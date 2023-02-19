package by.academy.task_7;

public class RobotTypeTwo extends Robot implements Action{

    private final String typeMove = "Робот на колёсном ходу";
    private final String typeFight = "Робот атакует щебнем";

    public RobotTypeTwo(String name, Head head, Body body) {
        super(name, head, body);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + typeMove + ".\n" + typeFight + "\n***";
    }

    @Override
    public String fight() {
        return "Бросает щебень в противника.";
    }

    @Override
    public String move() {
        return "Едет шурша колёсами.";
    }
}
