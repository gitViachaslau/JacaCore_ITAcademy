package by.academy.task_6.container;

public class Сylinder extends Container{

    private int weightContainer = 0;

    public Сylinder(boolean type, int height, int density) {
        super(type, height, density);
        countWeight();
    }

    @Override
    void countWeight() {
        weightContainer = (int) (Math.PI*(Math.pow(super.getSizeDioganal()/2, 2)) * super.getHeightContainer()
                * super.getDensityOfWater());
    }

    @Override
    public int getWeightContainer() {
        return weightContainer;
    }

    @Override
    public String toString() {
        return ", Контейнер типа ЦИЛИНДР, " +
                "Вес контейнера: " + weightContainer + super.toString();
    }
}
