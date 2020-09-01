package StrategyPattern;

public final class App {
    public static void main(String[] args) {
        Moving train = new Train();
        Moving bus = new Bus();

        train.setMoveInterface(new Oil());
        bus.setMoveInterface(new Gasoline());

        train.move();
        bus.move();

        // 오일로 이동하는 버스가 나온다면
        System.out.println("오일로 이동하는 자동차 개발 후");
        bus.setMoveInterface(new Oil());
        bus.move();
    }
}
