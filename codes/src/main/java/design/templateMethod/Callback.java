package design.templateMethod;

public class Callback {
    public static void main(String[] args) {
        F f = new C1();
        f.opt();
    }
}

abstract class F {
    void opt() {
        opt1();
        opt2();
    }

    abstract void opt2();

    abstract void opt1();
}

class C1 extends F {

    @Override
    void opt2() {
        System.out.println("==========");
    }

    @Override
    void opt1() {
        System.out.println("----------");
    }
}
