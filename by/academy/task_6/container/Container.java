package by.academy.task_6.container;

public abstract class Container {
    static final int DENSITY_OF_WATER_LOW = 1000;
    static final int DENSITY_OF_WATER_HIGH = 2000;
    static final int SMALL_SIZE_DIOGANAL = 10;
    static final int BIG_SIZE_DIOGANAL = 20;
    static final int MIN_HEIGHT = 10;
    static final int MAX_HEIGHT = 100;

    private boolean bigContainer = false;
    private int densityOfWater = 0;
    private int heightContainer = 10;
    private int sizeDioganal = 0;

    abstract void countWeight();
    abstract public int getWeightContainer();

    public Container(boolean type, int height, int density) {
        this.bigContainer = type;
        this.sizeDioganal = bigContainer ? BIG_SIZE_DIOGANAL : SMALL_SIZE_DIOGANAL;

        if (height < MIN_HEIGHT){
            this.heightContainer = MIN_HEIGHT;
        }
        else if (height > MAX_HEIGHT){
            this.heightContainer = MAX_HEIGHT;
        }
        else{
            this.heightContainer = height;
        }

        switch (density){
            case 1:
                this.densityOfWater = DENSITY_OF_WATER_LOW;
                break;
            case 2:
                this.densityOfWater = DENSITY_OF_WATER_HIGH;
                break;
            default:
                this.densityOfWater = DENSITY_OF_WATER_HIGH;
        }
    }

    public boolean isBigContainer() {
        return bigContainer;
    }

    public int getDensityOfWater() {
        return densityOfWater;
    }

    public int getHeightContainer() {
        return heightContainer;
    }

    public int getSizeDioganal() {
        return sizeDioganal;
    }

    @Override
    public String toString() {
        return  (bigContainer ? " Большой контейнер" : " Малый контейнер") +
                ", Плотность воды: " + densityOfWater + ", " +
                "Высота : " + heightContainer + ", Размер диоганали: " + sizeDioganal;
    }
}
