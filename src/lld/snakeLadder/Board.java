package lld.snakeladder;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private int size;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;

    public Board(int size) {
        this.size = size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
    }

    public int move(int currentPos, int diceRoll)
    {
        int newPos = currentPos + diceRoll;

        //check out of boundary
        if(newPos > this.size){
            return currentPos;
        }

        //check snake bite
        if(this.snakes.containsKey(newPos)){
            return this.snakes.get(newPos);
        }

        //check ladder ride
        if (this.ladders.containsKey(newPos)){
            return this.ladders.get(newPos);
        }

        return newPos;
    }

    public int getSize() {
        return size;
    }
}
