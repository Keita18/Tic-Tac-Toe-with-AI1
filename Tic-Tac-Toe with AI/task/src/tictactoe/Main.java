package tictactoe;

public class Main {
    public static void main(String[] args) {
        BaseNumberGenerator generator = new MagicNumberGenerator(10);
        var a  = generator.generate();
        System.out.println(a);
    }
}

class A {
    String s;
    public A(String s) {
        this.s = s;
    }
    void printA() {
        System.out.println();
    }
}

class B extends A{
    String s;
    public B(String s, int a) {
        super(s);
        this.s = s;
    }
    void printA() {
        System.out.println();
    }
}

class BaseNumberGenerator {
// this.base = base = 10
    protected int base;

    public BaseNumberGenerator(int base) {
        this.base = base;
    }

    public int generate() {
        return base + 11;
    }
}

class NumberGenerator extends BaseNumberGenerator {

// this.base = baseNG = 10
    public NumberGenerator(int base) {
        super(base);
    }

    @Override
    public int generate() {

        return super.generate() + getNumber();
    }


    protected int getNumber() {
        return this.base - 7;
    }
}

class MagicNumberGenerator extends NumberGenerator {

// this.base = baseMNG = 10
    public MagicNumberGenerator(int base) {
        super(base);
    }

    @Override
    protected int getNumber() {
        return this.base + 7;
    }
}