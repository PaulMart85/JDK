/* Задача 4:
Написать два класса работник и бездельник, реализующие этот интерфейс.
Написать обобщенные классы Workplace и Club, содержащие массив из Person.
В этих классах необходимо вызывать у всего массива людей соответствующие методы.
 */
interface Person {
    void doWork();
    void haveRest();
}

class Worker implements Person {
    @Override
    public void doWork() {
        System.out.println("Работник любит работать.");
    }

    @Override
    public void haveRest() {
        System.out.println("Работник не умеет бездельничать.");
    }
}

class Idler implements Person {
    @Override
    public void doWork() {
        System.out.println("Бездельник не любит работать.");
    }

    @Override
    public void haveRest() {
        System.out.println("Бездельник умеет отдыхать.");
    }
}

class Workplace<E extends Person> {
    E[] people;

    public Workplace(E[] people) {
        this.people = people;
    }

    public void performWork() {
        for (E person : people) {
            person.doWork();
        }
    }
}

class Club<E extends Person> {
    E[] people;

    public Club(E[] people) {
        this.people = people;
    }

    public void haveFun() {
        for (E person : people) {
            person.haveRest();
        }
    }
}

public class WorkerIdler {
    public static void main(String[] args) {
        Person[] employees = {new Worker(), new Idler()};

        Workplace<Person> employeesWorkplace = new Workplace<>(employees);
        Club<Person> employeesClub = new Club<>(employees);

        System.out.println("Работники на работе:");
        employeesWorkplace.performWork();

        System.out.println("\nРаботники в клубе:");
        employeesClub.haveFun();

    }
}
