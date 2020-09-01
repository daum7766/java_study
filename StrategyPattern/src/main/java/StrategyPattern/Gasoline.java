package StrategyPattern;

public class Gasoline implements MoveInterface {

    @Override
    public void move() {
        System.out.println("가솔린을 사용하여 이동합니다.");
    }
    
}
