package overloadingAndOverriding;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        TestClass test = new TestClass();
        System.out.println("오버로딩 테스트");
        test.sum(1, 2);
        test.sum(1.1, 1.2, 1.3);

        ChildClass child = new ChildClass();
        System.out.println("오버라이딩 테스트");
        child.sum(2, 1);
        System.out.println("이거는 오버라이딩을 안해서 원래껄로");
        child.sum(1, 1.1, 1.2);
    }
}
