package by.academy.task_8_1;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

public interface MyList<T> {
    int size();
    boolean isEmpty();
    boolean contains(Object var1);
    boolean add(T var1);
    boolean remove(Object var1);
    boolean addAll(Collection<? extends T> var1);
    boolean removeAll(Collection<?> col);
    void add(int index, T obj);
    boolean addAll(int index, MyList<? extends T> col);
    T get(int index);
    int indexOf(Object obj);
    int lastIndexOf(Object obj);
    ListIterator<T> listIterator ();
    T remove(int index);
    T set(int index, T obj);
    void sort(Comparator<? super T> comp);

    static <T> List<T> of() {
        return null;
    }

}
