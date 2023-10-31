/* Задача 2:
● Создайте коллекцию мужских и женских имен с помощью интерфейса List -
добавьте повторяющиеся значения
● Получите уникальный список Set на основании List
● Определите наименьший элемент (алфавитный порядок)
● Определите наибольший элемент (по количеству букв в слове, но в обратном порядке)
● Удалите все элементы, содержащие букву ‘A’
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class NamesSet {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>(List.of("Саша", "Микеланджело", "Серхо", "Аксюта", "Марина", "Валентина"));

        names.add("Саша");
        names.add("Ксюша");

        for (String name : names) System.out.print(name + " ");
        System.out.println();

        HashSet<String> namesSet = new HashSet<>(names);
        namesSet.forEach(elem -> System.out.print(elem + " "));
        System.out.println();

        TreeSet<String> sortedNames = new TreeSet<>(namesSet);
        String smallestName = sortedNames.first();
        System.out.println("Наименьший элемент: " + smallestName);

        TreeSet<String> reverseLengthSortedNames = new TreeSet<>(
                (name1, name2) -> Integer.compare(name2.length(), name1.length())
        );
        reverseLengthSortedNames.addAll(sortedNames);
        System.out.println("Наибольший элемент по количеству букв в обратном порядке: " + reverseLengthSortedNames.first());

        namesSet.removeIf(elem -> elem.contains("А"));
        namesSet.forEach(System.out::println);
    }
}
