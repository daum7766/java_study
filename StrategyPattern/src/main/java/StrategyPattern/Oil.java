package StrategyPattern;

public class Oil implements MoveInterface {

    @Override
    public void move() {
        System.out.println("오일을 사용하여 이동합니다.");
    }
    
}
