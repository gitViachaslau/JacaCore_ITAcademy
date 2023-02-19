package by.academy.task_6.deck;

import by.academy.task_6.container.Cone;
import by.academy.task_6.container.Container;
import by.academy.task_6.container.Square;
import by.academy.task_6.container.Сylinder;

import java.util.Arrays;

public class Deck {
    static private final int MAX_VOLUME_DECK = 4;
    private int freeSpace = MAX_VOLUME_DECK;
    private Container[] allContainer = new Container[MAX_VOLUME_DECK];
    private int weightDeck = 0;

    public Deck() {
    }

    public int getWeightDeck() {
        return weightDeck;
    }

    public int getFreeSpace() {
        return freeSpace;
    }

    public boolean addContainerToDeck(String nameType, boolean type, int height, int density){
        if (((type == false) && (this.freeSpace >= 1)) || ((type == true) && (this.freeSpace >= 2))){
            int indexFree = 0;
            for(int i = 0; i < allContainer.length; i++){
                if(allContainer[i] == null){
                    indexFree = i;
                }
            }

            switch (nameType){
                case "Cone":
                    allContainer[indexFree] = new Cone(type, height, density);
                    this.weightDeck += allContainer[indexFree].getWeightContainer();
                    break;
                case "Square":
                    allContainer[indexFree] = new Square(type, height, density);
                    this.weightDeck += allContainer[indexFree].getWeightContainer();
                    break;
                case "Cylinder":
                    allContainer[indexFree] = new Сylinder(type, height, density);
                    this.weightDeck += allContainer[indexFree].getWeightContainer();
                    break;
                default:
                    break;
            }
            if (type){
                freeSpace = freeSpace - 2;
            }
            else {
                freeSpace = freeSpace - 1;
            }
            return true;
        }
        return false;
    }


    public void removeContainerToDeck(){
        for(int i = allContainer.length - 1; i >= 0; i--){
            if(allContainer[i] != null){
                freeSpace = allContainer[i].isBigContainer() ? (freeSpace - 2) : (freeSpace - 1);
                weightDeck -= allContainer[i].getWeightContainer();
                allContainer[i] = null;
            }
        }
    }

    @Override
    public String toString() {
        return "Данные о палубе: " +
                "Свободное место: " + freeSpace +
                ", Данные о контейнере на палубе: " + printInfoCont(allContainer) +
                ", Вес на палубе: " + weightDeck ;
    }

    private String printInfoCont (Container[] containerArray){
        String result = "";
        for (int i = 0; i < containerArray.length; i++){
            if(containerArray[i] != null){
                result += containerArray[i].toString();
            }
        }
        return result;
    }
}
