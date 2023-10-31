import java.util.ArrayList;
import java.util.List;

/* Домашнее задание:
Создать класс справочник сотрудников, который
содержит внутри коллекцию сотрудников - каждый
сотрудник должен иметь следующие атрибуты:
○ Табельный номер
○ Номер телефона
○ Имя
○ Стаж
● Добавить метод, который ищет сотрудника по стажу
(может быть список)
● Добавить метод, который выводит номер телефона
сотрудника по имени (может быть список)
● Добавить метод, который ищет сотрудника по
табельному номеру
● Добавить метод добавления нового сотрудника в
справочник
 */

class Employee {
    private int employeeId;
    private String phoneNumber;
    private String name;
    private int experience;

    public Employee(int employeeId, String phoneNumber, String name, int experience) {
        this.employeeId = employeeId;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Сотрудник: " + name;
    }
}

public class EmployeeDir {
    private List<Employee> employees;

    public EmployeeDir() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee findEmployeeByExperience(int experience) {
        for (Employee employee : employees) {
            if (employee.getExperience() == experience) {
                return employee;
            }
        }
        return null; // Возвращаем null, если сотрудник с таким стажем не найден
    }

    public String findPhoneNumberByName(String name) {
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                return employee.getPhoneNumber();
            }
        }
        return "Номер не найден"; // Возвращаем сообщение, если сотрудник с указанным именем не найден
    }

    public Employee findEmployeeByEmployeeId(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null; // Возвращаем null, если сотрудник с указанным табельным номером не найден
    }

    public static void main(String[] args) {
        EmployeeDir directory = new EmployeeDir();

        Employee employee1 = new Employee(1, "1234567890", "Иван Иванов", 5);
        Employee employee2 = new Employee(2, "9876543210", "Анна Петрова", 10);

        directory.addEmployee(employee1);
        directory.addEmployee(employee2);

        System.out.println("Сотрудник по стажу: " + directory.findEmployeeByExperience(5).toString());

        System.out.println("Номер телефона по имени: " + directory.findPhoneNumberByName("Анна Петрова"));

        System.out.println("Имя сотрудника по табельному: " + directory.findEmployeeByEmployeeId(1));

    }
}
