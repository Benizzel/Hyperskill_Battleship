package battleship;

public class Ship {
    private final ShipType shipType;
    private final Gamefield gamefield;
    private final Coordinate coordinateA;
    private final Coordinate coordinateB;

    //TODO Add a Ship Type
    public Ship(Gamefield gamefield, ShipType shipType, Coordinate coordinateA, Coordinate coordinateB) {
        this.gamefield = gamefield;
        this.shipType = shipType;
        this.coordinateA = coordinateA;
        this.coordinateB = coordinateB;
    }

    public int getLength() {
        return shipType.getLength();
     }


    private static int convertCharToInt(char inputChar) {
        if (!Character.isLetter(inputChar)) {
            throw new IllegalArgumentException("The provided character is not a letter: " + inputChar);
        }

        //Return ASCII Value of Character minus 64 (A in ASCII == 65)
        return Character.toUpperCase(inputChar) - ('A' - 1);
    }

    public Coordinate getCoordinateA() {
        return coordinateA;
    }

    public Coordinate getCoordinateB() {
        return coordinateB;
    }

    public int getRowA() {
        return coordinateA.getRow();
    }

    public int getRowB() {
        return coordinateB.getRow();
    }

    public int getColumnA() {
        return coordinateA.getColumn();
    }

    public int getColumnB() {
        return coordinateB.getColumn();
    }

    public Gamefield getGamefield() {
        return gamefield;
    }

    public ShipType getShipType() {
        return shipType;
    }
}
