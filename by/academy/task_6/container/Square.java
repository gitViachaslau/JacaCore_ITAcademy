package by.academy.task_6.container;

public class Square extends Container {

    private int weightContainer = 0;

    public Square(boolean type, int height, int density) {
        super(type, height, density);
        countWeight();
    }

    @Override
    void countWeight() {
        weightContainer = (int) (Math.sqrt(Math.pow(super.getSizeDioganal(), 2) / 2) * 3 * super.getDensityOfWater());
    }

    @Override
    public int getWeightContainer() {
        return weightContainer;
    }

    @Override
    public String toString() {
        return ", Контейнер типа КУБ, " +
                "Вес контейнера: " + weightContainer  + super.toString();
    }
}
