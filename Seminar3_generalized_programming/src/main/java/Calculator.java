/* Задача 6:
Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
sum(), multiply(), divide(), subtract().
Параметры этих методов - два числа разного типа, над которыми должна быть произведена операция.
 */
public class Calculator {

    static <T extends Number> double sum(T x, T y) {
        return x.doubleValue() + y.doubleValue();
    }

    static <T extends Number> double multiply(T x, T y) {
        return x.doubleValue() * y.doubleValue();
    }

    static <T extends Number> double divide(T x, T y) {
        if (y.doubleValue() == 0) {
            throw new ArithmeticException("Деление на ноль!");
        }
        return x.doubleValue() / y.doubleValue();
    }

    static <T extends Number> double subtract(T x, T y) {
        return x.doubleValue() - y.doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(Calculator.sum(5, 0.36));
        System.out.println(Calculator.multiply(8, 10.3f));
        System.out.println(Calculator.divide(23.6f, 12L));
        System.out.println(Calculator.subtract(12.2, 18));
    }

}


