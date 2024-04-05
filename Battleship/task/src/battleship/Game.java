package battleship;

import java.util.Scanner;

public class Game {

    public final Player player1;
    public final Player player2;
    public Game() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    public void arrangeShips(Player player) {
        Scanner scanner = new Scanner(System.in);
        Gamefield gamefield = player.getGamefield();
        ShipType shipType = null;

        System.out.println(player.getName() + ", place your ships on the game field");
        gamefield.printUncoveredBoard();

        //TODO for Loop for all ShipType Enums
        for (ShipType value : ShipType.values()) {
            do {
                switch (value) {
                    case AIRCRAFT_CARRIER:
                        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ShipType.AIRCRAFT_CARRIER.getName(), ShipType.AIRCRAFT_CARRIER.getLength());
                        shipType = ShipType.AIRCRAFT_CARRIER;
                        break;
                    case BATTLESHIP:
                        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ShipType.BATTLESHIP.getName(), ShipType.BATTLESHIP.getLength());
                        shipType = ShipType.BATTLESHIP;
                        break;
                    case SUBMARINE:
                        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ShipType.SUBMARINE.getName(), ShipType.SUBMARINE.getLength());
                        shipType = ShipType.SUBMARINE;
                        break;
                    case CRUISER:
                        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ShipType.CRUISER.getName(), ShipType.CRUISER.getLength());
                        shipType = ShipType.CRUISER;
                        break;
                    case DESTROYER:
                        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ShipType.DESTROYER.getName(), ShipType.DESTROYER.getLength());
                        shipType = ShipType.DESTROYER;
                        break;
                    default:
                        System.out.println("Error! Not a valid Ship Type.");
                }
                try {
                    Coordinate coordinateA = new Coordinate(scanner.next(), gamefield);
                    Coordinate coordinateB = new Coordinate(scanner.next(), gamefield);
                    Ship ship = new Ship(gamefield, shipType, coordinateA, coordinateB);
                    gamefield.placeShip(ship);
                    gamefield.printUncoveredBoard();
                    //with the break, program leaves do/while and goes to the for-loop, which means goes to next ShipType
                    break;
                } catch (RuntimeException e) {
                    System.out.println("Error! " + e + "\n");
                }
            } while (true);
        }
        passTheMove();
    }

    public void playGame() {
        Player activePlayer = player1;
        Player inactivePlayer = player2;
        Scanner scanner = new Scanner(System.in);
        Gamefield gamefield = inactivePlayer.getGamefield();

        while (true) {

            printBoardsForActivePlayer(activePlayer, inactivePlayer);
            System.out.println(activePlayer.getName() + ", it's your turn:");

            try {
                Coordinate shot = new Coordinate(scanner.next(), gamefield);

                if (gamefield.shotHitsAShip(shot) && !gamefield.hasOccupiedNeighborhood(shot.getRow() - 1, shot.getColumn() - 1)) {
                    gamefield.markTheShotOnBoard(shot);
                    System.out.println("You sank a ship!\n");
                } else if (gamefield.shotHitsAShip(shot)) {
                    gamefield.markTheShotOnBoard(shot);
                    System.out.println("You hit a ship!\n");
                } else {
                    gamefield.markTheShotOnBoard(shot);
                    System.out.println("You missed!\n");
                }

            } catch (RuntimeException e) {
                System.out.println("Error ! " + e + "\n");
            }

            if (player1.getGamefield().allShipsSunk() || player2.getGamefield().allShipsSunk()) {
                break;
            } else {
                passTheMove();
                activePlayer = switchPlayer(activePlayer);
                inactivePlayer = activePlayer == player1 ? player2 : player1;
                gamefield = inactivePlayer.getGamefield();
            }
        }

        System.out.println("You sank the last ship. You won. Congratulations!");
    }

    private void passTheMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    private Player switchPlayer(Player activPlayer) {
        Player newActivePlayer;
        newActivePlayer = activPlayer == player1 ? player2 : player1;
        return newActivePlayer;
    }

    private void printBoardsForActivePlayer(Player activePlayer, Player inactivePlayer) {
        inactivePlayer.getGamefield().printFogOfWarCoveredBoard();
        activePlayer.getGamefield().printUncoveredBoard();
    }
}

