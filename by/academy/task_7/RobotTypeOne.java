package by.academy.task_7;

public class RobotTypeOne extends Robot implements Action{

    private final String typeMove = "Робот на гусеничном ходу";
    private final String typeFight = "Робот атакует ломом";

    public RobotTypeOne(String name, Head head, Body body) {
        super(name, head, body);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + typeMove + ".\n" + typeFight + "\n***";
    }

    @Override
    public String fight() {
        return "Бросает лом в противника.";
    }

    @Override
    public String move() {
        return "Едет гремя гусеницами.";
    }
}
