package InterfaceTest;

public class TestClass implements InterfaceClass {

    @Override
    public void forward(int distance) {
        System.out.println(String.format("앞으로 %d 만큼 이동합니다.", distance));
    }

    @Override
    public void backward(int distance) {
        System.out.println(String.format("뒤으로 %d 만큼 이동합니다.", distance));
    }

    @Override
    public void leftMove(int distance) {
        System.out.println(String.format("왼쪽으로 %d 만큼 이동합니다.", distance));
    }

    @Override
    public void rightMove(int distance) {
        System.out.println(String.format("오른쪽으로 %d 만큼 이동합니다.", distance));
    }
    
}
