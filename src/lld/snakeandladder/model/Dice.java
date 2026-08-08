package lld.snakeandladder.model;

import java.util.Random;

public class Dice {

    private int sides; //1, 2, 3, 4, 5, 6

    private final Random random = new Random();

    public Dice(int sides) {
        this.sides = sides;
    }

    public int roll() {
        return random.nextInt(sides) + 1;
    }
}
