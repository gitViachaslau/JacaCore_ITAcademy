package by.academy.task_7;

public class Main {
    public static void main(String[] args) {
        Robot[] robots = new Robot[9];

        robots[0] = new RobotTypeOne("Первый", HeadFactory.LARGE.get(), BodyFactory.SMALL.get());
        robots[1] = new RobotTypeOne("Второй", HeadFactory.MIDDLE.get(), BodyFactory.MIDDLE.get());
        robots[2] = new RobotTypeOne("Трейтий", HeadFactory.SMALL.get(), BodyFactory.LARGE.get());
        robots[3] = new RobotTypeTwo("Четвёртый", HeadFactory.LARGE.get(), BodyFactory.SMALL.get());
        robots[4] = new RobotTypeTwo("Пятый", HeadFactory.MIDDLE.get(), BodyFactory.MIDDLE.get());
        robots[5] = new RobotTypeTwo("Шестой", HeadFactory.SMALL.get(), BodyFactory.LARGE.get());
        robots[6] = new RobotTypeThree("Седьмой", HeadFactory.LARGE.get(), BodyFactory.SMALL.get());
        robots[7] = new RobotTypeThree("Восьмой", HeadFactory.MIDDLE.get(), BodyFactory.MIDDLE.get());
        robots[8] = new RobotTypeThree("Девятый", HeadFactory.SMALL.get(), BodyFactory.LARGE.get());

        for(Robot r: robots){
            System.out.println(r.toString() + "\n");
        }
    }
}
