/* Задача 1.
Создать коллекцию мужских и женских имен с помощью интерфейса List.
Отсортировать коллекцию в алфавитном порядке.
Отсортировать коллекцию по количеству букв в слове.
Развернуть коллекцию.
 */

import java.util.*;

public class NamesList {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("Саша", "Ваня", "Серхо", "Ксюша", "Марина", "Валентина"));

        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) System.out.print(iterator.next() + " ");
        System.out.println();

        Collections.sort(names);

        for (String name : names) System.out.print(name + " ");
        System.out.println();

        names.sort(Comparator.comparing(String::length));

        names.stream().forEach(elem -> System.out.print(elem + " "));
        System.out.println();

        Collections.reverse(names);

        names.forEach(System.out::println);

    }
}
