package by.academy.task_8_1;

import java.util.Collection;
import java.util.Comparator;
import java.util.ListIterator;

public class MyArrayList <T> implements MyList{
    private T[] array;

    public MyArrayList() {
        final int DEFAULT_SIZE = 5;
        this.array = (T[]) new Object[DEFAULT_SIZE];;
    }

    public MyArrayList(int capacity) {
        this.array = (T[]) new Object[capacity];;
    }

    public MyArrayList(MyList <? extends T> col) {
        this.array = (T[]) new Object[col.size()];
    }


    // Возвращает количество элементов в коллекции
    @Override
    public int size() {
        int counter = 0;
        for (T element: this.array){
           if (element != null){
               counter ++;
           }
        }
        return counter;
    }

   // Проверка на наличие элементов
    @Override
    public boolean isEmpty() {
        if (this.size() == 0){
            return true;
        }
        return false;
    }

    // Проверка наличия элемента
    @Override
    public boolean contains(Object var1) {

        if (!checkType(var1)){
            return false;
        }

        for (T element: array){
            if (element.equals(var1)){
                return true;
            }
        }
        return false;
    }

    // Добавлеие элемента в конец списка
    @Override
    public boolean add(Object var1) {
        final int SIZE_ARRAY = array.length;
        final int PCS_ELEMENTS = this.size();

        if (!checkType(var1)){
            return false;
        }

        if ((PCS_ELEMENTS + 1) >= SIZE_ARRAY){
            for(int i = 0; i < array.length; i++){
                if (array[i] == null){
                    array[i] = (T) var1;
                }
            }
            return true;
        }
        else {
            T[] newArray = (T[]) new Object[array.length * 2];
            int counter = 0;

            for(int i = 0; i < array.length; i++){
                newArray[i] = array[i];
                counter ++;
            }

            for(int i = counter + 1; i < newArray.length; i++){
                if (newArray[i] == null){
                    newArray[i] = (T) var1;
                }
            }
            array = newArray;
            return true;
        }
    }

    // Удаление элемента по элементу
    @Override
    public boolean remove(Object var1) {
        int index = 0;
        boolean result = false;

        if (!checkType(var1)){
            return false;
        }

        for(int i = 0; i < array.length; i++){
            if(array[i].equals(var1)){
                result = true;
                index = i;
            }
        }

        if(result){
            array[index] = null;
            for(int i = index; i < array.length - 1; i++){
                array[i] = array[i+1];
            }
            return true;
        }

        return false;
    }

    // Добавление коллекции элементов в конец
    // Вопрос по тому будет ли подтягивать изменившийся размер массива
    @Override
    public boolean addAll(Collection var1) {

        T[] array = (T[]) var1.toArray();
        for(int i = 0; i < array.length; i++){
            this.add(array[i]);
        }
        return true;
    }

    //
    @Override
    public void add(int index, Object obj) {

    }

    @Override
    public boolean addAll(int index, MyList col) {
        return false;
    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public int indexOf(Object obj) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object obj) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object obj) {
        return null;
    }

    @Override
    public void sort(Comparator comp) {

    }

    @Override
    public boolean removeAll(Collection col) {
        return false;
    }

    // Проверка соответствуют ли типы
    private boolean checkType (Object obj){
        if (obj.getClass() != array.getClass()){
            return false;
        }
        return true;
    }
}
