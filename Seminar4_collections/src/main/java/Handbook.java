import java.util.Map;
import java.util.TreeMap;

/* Задача 3:
● Создайте телефонный справочник с помощью Map - телефон это ключ, а имя
значение
● Найдите человека с самым маленьким номером телефона
● Найдите номер телефона человека, чье имя самое большое в алфавитном порядке

 */
public class Handbook {
    public static void main(String[] args) {
        TreeMap<String, String> phoneDirectory = new TreeMap<>();

        phoneDirectory.put("1234567890", "Иван Иванов");
        phoneDirectory.put("9876543210", "Анна Петрова");
        phoneDirectory.put("5555555555", "Петр Сидоров");

        Map.Entry<String, String> first = phoneDirectory.firstEntry();
        System.out.println("Человек с самым маленьким номером телефона: " + first.getValue());

        // Находим человека с самым большим именем в алфавитном порядке
        Map.Entry<String, String> last = ((TreeMap<String, String>) phoneDirectory).lastEntry();
        System.out.println("Номер телефона: " + last.getKey());
    }


}
