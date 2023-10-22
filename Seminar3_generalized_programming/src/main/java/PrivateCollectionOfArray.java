/* Задача 2:
Описать собственную коллекцию - список на основе массива. Коллекция должна иметь
возможность хранить любые типы данных, иметь методы добавления и удаления элементов.
 */

import java.util.ArrayList;

public class PrivateCollectionOfArray<T> extends ArrayList<T> {

    ArrayList<T> collection;

    public PrivateCollectionOfArray() {
        collection = new ArrayList<>();
    }

    public void displayCollection() {
        for (T item : this) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        PrivateCollectionOfArray<Object> myCollection = new PrivateCollectionOfArray<>();

        myCollection.add(42);
        myCollection.add("Hello, world");
        myCollection.add(Math.acos(0.6));
        myCollection.displayCollection();

        myCollection.remove(1);
        myCollection.displayCollection();
    }
}
