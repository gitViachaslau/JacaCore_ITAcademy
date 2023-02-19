package by.academy.task_6.port;

import by.academy.task_6.ship.Ship;

import java.util.Arrays;

public class Port {
    static final int SIZE_PORT = 10;
    private Ship[] ships = new Ship[SIZE_PORT];

    public Port() {
    }

    public boolean addShip(String name, int pcsDeck){
            for(int i = 0; i < ships.length; i++){
                if (ships[i] == null){
                    ships[i] = new Ship(name, pcsDeck);
                    return true;
                }
            }
        return false;
    }

    public boolean removeShip(String name){
        for (int i = 0; i < ships.length; i++){
            if (ships[i].getName().equals(name)){
                ships[i] = null;
                return true;
            }
        }
        return false;
    }

    private int getFreeSlots(){
        int result = 0;
        for(int i = 0; i < ships.length; i++) {
            if (ships[i] == null){
                result++;
            }
        }
        return result;
    }

    public long getFullWeight(){
        long result = 0;
        for(int i = 0; i < ships.length; i++){
            if (ships[i] != null){
                result += ships[i].getWeightShip();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Количество свободных мест в порту: " + getFreeSlots() +
                ". \nКорабли: \n" + printInfoShips(ships);
    }

    private String printInfoShips (Ship[] shipArray){
        String result = "";
        for (int i = 0; i < shipArray.length; i++){
            if(shipArray[i] != null){
                result += shipArray[i].toString();
            }
            else{
                result += "Место свободно\n";
            }
        }
        return result;
    }

    public Ship[] getShips() {
        return ships;
    }
}
