/* Задача 3:
Написать итератор по массиву элементов любого типа.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {

    T[] array;
    int currentIndex;

    public ArrayIterator(T[] array) {
        this.array = array;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[currentIndex++];
    }


    public static void main(String[] args) {
        PrivateCollectionOfArray<Object> myCollection = new PrivateCollectionOfArray<>();

        myCollection.add(42);
        myCollection.add("Hello, world");
        myCollection.add(Math.acos(0.6));

        ArrayIterator<Object> array = new ArrayIterator<>(myCollection.toArray());
        while (array.hasNext()) {
            System.out.println(array.next());
        }
    }
}
