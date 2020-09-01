package StrategyPattern;

public class Moving {
    private MoveInterface moveInterface;

    public void move() {
        moveInterface.move();
    }

    public void setMoveInterface(MoveInterface moveInterface) {
        this.moveInterface = moveInterface;
    }
    
}
