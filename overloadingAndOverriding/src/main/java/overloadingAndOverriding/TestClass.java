package overloadingAndOverriding;

public class TestClass {
    public void sum(int a, int b) {
        System.out.println("a + b = " + a+b);
    }

    public void sum(double a, double b, double c) {
        System.out.println("a + b + c = " + a+b+c);
    }

}