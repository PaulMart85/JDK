/* Задача 1:
Создать обобщенный класс с тремя параметрами (T, V, K). Класс содержит три переменные (T, V, K),
конструктор, принимающий на вход параметры типа (T, V, K), методы, возвращающие значения трех переменных.
Создать метод, выводящий на консоль имена классов для всех трех переменных класса.
Наложить ограничения на параметры типа:
T должен реализовать интерфейс Comparable (классы оболочки, String),
V должен реализовать интерфейс DataInput и расширять класс InputStream,
K должен расширять класс Number.
*/

import java.io.*;

public class GenericClassWith3Params<T extends Comparable<T>, V extends InputStream & DataInput, K extends Number> {
   T comparableItem;
   V dataInputItem;
   K numberItem;

    GenericClassWith3Params(T comparableItem, V dataInputItem, K numberItem) {
        this.comparableItem = comparableItem;
        this.dataInputItem = dataInputItem;
        this.numberItem = numberItem;
    }

    public T getComparableItem() {
        return comparableItem;
    }

    public V getDataInputItem() {
        return dataInputItem;
    }

    public K getNumberItem() {
        return numberItem;
    }

    void consolePrintClassNames() {
        System.out.println("Class name for T: " + comparableItem.getClass().getSimpleName());
        System.out.println("Class name for V: " + dataInputItem.getClass().getSimpleName());
        System.out.println("Class name for K: " + numberItem.getClass().getSimpleName());
    }

    static class MockDataInputStream extends DataInputStream { // класс заглушка
        public MockDataInputStream(InputStream in) {
            super(in);
        }
    }

    public static void main(String[] args) {
        GenericClassWith3Params<String, DataInputStream, Double> genericClass =
                new GenericClassWith3Params<>(
                        "Hello, world",
                        new DataInputStream(new MockDataInputStream(null)),
                        Math.PI);

        genericClass.consolePrintClassNames();
    }
}
