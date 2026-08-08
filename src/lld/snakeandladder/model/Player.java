package lld.snakeandladder.model;

public class Player {

    private int playerId;
    private String name;
    private int position;

    public Player(int playerId, String name, int position) {
        this.playerId = playerId;
        this.name = name;
        this.position = position;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
