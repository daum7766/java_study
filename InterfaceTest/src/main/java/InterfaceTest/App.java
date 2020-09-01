package InterfaceTest;

public final class App {

    public static void main(String[] args) {
        TestClass test = new TestClass();
        test.forward(3);
        test.backward(2);
        test.leftMove(1);
        test.rightMove(4);
    }
}
