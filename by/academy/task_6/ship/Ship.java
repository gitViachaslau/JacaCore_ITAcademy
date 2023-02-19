package by.academy.task_6.ship;


import by.academy.task_6.deck.Deck;

import java.util.Arrays;

public class Ship {
    String name = new String("");
    Deck[] deck;

    public Ship(String name, int pcsDeck) {
        this.name = name;
        if (pcsDeck <= 1){
            deck = new Deck[1];
            deck[0] = new Deck();
        }
        else {
            deck = new Deck[2];
            deck[0] = new Deck();
            deck[1] = new Deck();
        }
    }

    public boolean addDeck (){
        if (deck.length == 2){
            return false;
        }
        else {
            Deck[] temp = new Deck[2];
            temp[0] = deck[0];
            deck = temp;
        }
        return true;
    }

    public void removeDeck(){
        Deck[] temp = new Deck[1];
        temp[0] = deck[0];
        deck = temp;
    }

    public int getWeightShip(){
        int result = 0;
        for (int i = 0; i < deck.length; i++){
            if (deck[i] != null){
                result += deck[i].getWeightDeck();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return " Корабль " + "\"" + name + "\"" + ", Количество палуб: " + deck.length + ", Вес груза: "
                + getWeightShip() + " " + Arrays.toString(deck) + "\n";
    }

    public String getName() {
        return name;
    }

    public Deck[] getDeck() {
        return deck;
    }



}
