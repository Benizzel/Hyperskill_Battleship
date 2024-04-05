package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.arrangeShips(game.player1);
        game.arrangeShips(game.player2);
        game.playGame();
    }
}

