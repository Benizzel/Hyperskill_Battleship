package battleship;

public class Coordinate {
    private final String coordinate;
    private final Gamefield gamefield;
    private final int row;
    private final int column;

    public Coordinate(String coordinate, Gamefield gamefield) throws IllegalArgumentException {
        if (coordinate.length() > 3) {
            throw new IllegalArgumentException("At least one of the Coordinates has more than 3 digits.");
        } else if (coordinate.length() < 2) {
            throw new IllegalArgumentException("At least one of the Coordinates has less than 2 digits.");
        }

        this.gamefield = gamefield;
        row = convertCharToInt(coordinate.charAt(0));
        column = Integer.parseInt(coordinate.substring(1));

        if (gamefield.isCoordinateWithinBoardRange(row, column)) {
            this.coordinate = coordinate;
        } else {
            throw new IllegalArgumentException("At least one of the Coordinate is not within Board range");
        }
    }

    protected String getCoordinate() {
        return coordinate;
    }

    protected int getRow() {
        return row;
    }

    protected int getColumn() {
        return column;
    }

    private static int convertCharToInt(char inputChar) {
        if (!Character.isLetter(inputChar)) {
            throw new IllegalArgumentException("The provided character is not a letter: " + inputChar);
        }

        //Return ASCII Value of Character minus 64 (A in ASCII == 65)
        return Character.toUpperCase(inputChar) - ('A' - 1);
    }

    public Gamefield getGamefield() {
        return gamefield;
    }
}
