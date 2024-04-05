package battleship;

public class Player {
    private final Gamefield gamefield;
    private final String name;

    public Player(String name) {
        this.gamefield = new Gamefield();
        this.name = name;
    }

    public Gamefield getGamefield() {
        return gamefield;
    }

    public String getName() {
        return name;
    }
}
