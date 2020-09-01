package overloadingAndOverriding;

public class ChildClass extends TestClass {
    
    @Override
    public void sum(int a, int b) {
        System.out.println("a + b 인척하는 a - b =" + (a - b));
    }
}