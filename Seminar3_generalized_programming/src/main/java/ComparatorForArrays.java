/* Задача 7:
Напишите обобщенный метод compareArrays, который принимает два массива и возвращает true,
если одинаковые, и false в противном случае. Массивы могут быть любого типа данных, но
должны иметь одинаковую длину и содержать элементы одного типа.
 */
public class ComparatorForArrays {
    static <T> boolean compareArrays(T[] array1, T[] array2) {
        if (array1 == null || array2 == null) {
            return false;
        }

        if (array1.length != array2.length) {
            return false;
        }

        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].equals(array2[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Integer[] intArray1 = {1, 2, 3, 4, 5};
        Integer[] intArray2 = {1, 2, 3, 4, 5};

        String[] strArray1 = {"cat", "dog", "mouse"};
        String[] strArray2 = {"cat", "dog", "mouse"};

        System.out.println("intArray1 и intArray2 равны: " + compareArrays(intArray1, intArray2));

        System.out.println("strArray1 и strArray2 равны: " + compareArrays(strArray1, strArray2));
    }
}
