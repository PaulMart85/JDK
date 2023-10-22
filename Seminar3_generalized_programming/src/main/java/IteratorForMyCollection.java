/* Задание 5:
Внедрить итератор из задания 2 в коллекцию из задания 3 таким образом, чтобы
итератор был внутренним классом и, соответственно, объектом в коллекции.
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorForMyCollection {
    static class PrivateCollectionOfArray<T> extends ArrayList<T> {
        ArrayList<T> collection;

        public PrivateCollectionOfArray() {
            collection = new ArrayList<>();
        }

        static class ArrayIterator<T> implements Iterator<T> { // итератор - внутренний класс
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
        }

        public void displayCollection() {
            for (T item : this) {
                System.out.println(item);
            }
        }
    }

    public static void main(String[] args) {
        PrivateCollectionOfArray<Object> myCollection = new PrivateCollectionOfArray<>();

        myCollection.add(42);
        myCollection.add("Hello, world");
        myCollection.add(Math.acos(0.6));

        PrivateCollectionOfArray.ArrayIterator<Object> array =
                new PrivateCollectionOfArray.ArrayIterator<>(myCollection.toArray());
        while (array.hasNext()) {
            System.out.println(array.next());
        }

    }
}
