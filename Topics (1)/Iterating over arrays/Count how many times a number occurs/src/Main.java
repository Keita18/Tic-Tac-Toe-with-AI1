import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
/*        // put your code here
        Scanner scanner = new Scanner(System.in);
        var first = scanner.nextInt();
        List<Integer> numbers = new ArrayList<>();
        while (first > 0) {
            numbers.add(scanner.nextInt());
            first--;
        }
        var last = scanner.nextInt();
        var res = (int) numbers.stream().filter(it -> it == last).count();

        System.out.println(res);*/

        var a = method(29815);
        System.out.println(a);
        Person person = new Person("Test Dummy", -4);

    }

    static long method(long n) {
        if (n == 0) {
            return 0;
        }
        return n % 10 + method(n / 10);
    }
}
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        System.out.println(this);
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }
}
