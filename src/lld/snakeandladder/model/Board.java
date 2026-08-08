package lld.snakeandladder.model;

import java.util.Map;

public class Board {

    private int size;
    private Map<Integer, Snake> snakes;
    private Map<Integer, Ladder> ladders;

    public Board(int size, Map<Integer, Snake> snakes, Map<Integer, Ladder> ladders) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public int getFinalPosition(int position)
    {
        if (snakes.containsKey(position)) {
            return snakes.get(position).getTail();
        }

        if (ladders.containsKey(position)) {
            return ladders.get(position).getTop();
        }

        return position;
    }

    public int getSize() {
        return size;
    }

    public Map<Integer, Snake> getSnakes() {
        return snakes;
    }

    public Map<Integer, Ladder> getLadders() {
        return ladders;
    }
}
