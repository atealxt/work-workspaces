package test.general;

/**
 * In this example, the constructor for class A passes the new instance of A to B.doSomething.<br>
 * As a result, the instance of A—and all of its fields—escapes the scope of the constructor.
 */
public class EscapeAnalysis {

    public static void main(final String args[]) {
        new A(new B());
    }
}

class A {

    final int finalValue;

    public A(final B b) {
        super();
        b.doSomething(this); // this escapes!
        finalValue = 23;
    }

    int getTheValue() {
        return finalValue;
    }
}

class B {

    void doSomething(final A a) {
        System.out.println(a.getTheValue());
    }
}

// http://en.wikipedia.org/wiki/Escape_analysis
// http://www.javaspecialists.eu/archive/Issue179.html