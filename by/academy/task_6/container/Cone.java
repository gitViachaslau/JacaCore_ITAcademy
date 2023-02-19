package by.academy.task_6.container;

public class Cone extends Container {

    private int weightContainer = 0;

    public Cone(boolean type, int height, int density) {
        super(type, height, density);
        countWeight();
    }

    @Override
    void countWeight() {
        weightContainer = (int) (Math.PI * (Math.pow(super.getSizeDioganal() / 2, 2)) * super.getHeightContainer() *
                super.getDensityOfWater() / 3);
    }

    @Override
    public int getWeightContainer() {
        return weightContainer;
    }

    @Override
    public String toString() {
        return ", Контейнер типа КОНУС, " +
                "Вес контейнера: " + weightContainer + super.toString();
    }
}
