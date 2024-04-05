package battleship;

public class Gamefield {
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    public char[][] board;
    public Gamefield() {
        board = createBoard();
    }

    private char[][] createBoard() {
        char[][] gameplay = new char[ROWS][COLUMNS];

        for (int i = 0; i < gameplay.length; i++) {
            for (int j = 0; j < gameplay[i].length; j++) {
                gameplay[i][j] = '~';
            }
        }
        return gameplay;
    }

    public void printUncoveredBoard() {
        for (int i = 0; i <= board[0].length; i++) {
            System.out.print(i == 0 ? " " : " " + i);
        }
        System.out.println();

        char rowTag = 'A';

        for (char[] chars : board) {
            System.out.print(rowTag++);
            for (char aChar : chars) {
                System.out.print(" " + aChar);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printFogOfWarCoveredBoard() {
        for (int i = 0; i <= board[0].length; i++) {
            System.out.print(i == 0 ? " " : " " + i);
        }
        System.out.println();

        char rowTag = 'A';

        for (char[] chars : board) {
            System.out.print(rowTag++);
            for (char aChar : chars) {
                if (aChar == 'O') {
                    System.out.print(" " + '~');
                } else {
                    System.out.print(" " + aChar);
                }
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    protected boolean isCoordinateWithinBoardRange(int row, int column) {
        boolean isRowValid = row >= 1 && row <= ROWS;
        boolean isColumnValid = column >= 1 && column <= COLUMNS;

        /*
        I do not have to check the length because I check the column number. If it's 111, it's out of range.
        Same for row. In set Coordinates the checks are done
         */
        return isRowValid && isColumnValid;
    }

    protected void placeShip(Ship ship) throws RuntimeException {
        //Entering the coordinates is also valid from bottom to top (e.g. E1 A1) and from right to left (e.g. A10 A5). This is why I sort.
        int rowA = ship.getRowA();
        int rowB = ship.getRowB();
        int columnA = ship.getColumnA();
        int columnB = ship.getColumnB();
        int length = ship.getLength();

        int startingRow = Math.min(rowA, rowB);
        int endingRow = Math.max(rowA, rowB);
        int startingColumn = Math.min(columnA, columnB);
        int endingColumn = Math.max(columnA, columnB);

        //Check neighborhood before placing the ship, else a ship blocks itself
        for (int i = startingRow; i <= endingRow; i++) {
            for (int j = startingColumn; j <= endingColumn; j++) {
                if (hasOccupiedNeighborhood(i - 1, j - 1)) {
                    throw new IllegalArgumentException("Your Ship is too close to another ship");
                }
            }
        }

        //Check Alignment and Length and if ok, place ship
        if (!isAlignmentCorrect(rowA, rowB, columnA, columnB)) {
            throw new IllegalArgumentException("Alignment must be horizontal or vertical.");
        } else if (!isLengthCorrect(rowA, rowB, columnA, columnB, length)) {
            throw new IllegalArgumentException("Wrong length.");
        } else {
            for (int i = startingRow; i <= endingRow; i++) {
                for (int j = startingColumn; j <= endingColumn; j++) {
                    if (board[i - 1][j - 1] == 'O') {
                        throw new IllegalArgumentException("Your Coordinate touches with an already placed ship");
                    } else {
                        board[i - 1][j - 1] = 'O';
                    }
                }
            }
        }
    }

    protected boolean shotHitsAShip(Coordinate coordinate) {
        int row = coordinate.getRow();
        int column = coordinate.getColumn();
        //Already hit ('X') or already missed ('M') is for the sake of simplicity another hit / missed
        String hitIndicator = "[OX]";
        String missIndicator = "[M~]";

        if (String.valueOf(board[row - 1][column - 1]).matches(hitIndicator)) {
            return true;
        } else if (String.valueOf(board[row - 1][column - 1]).matches(missIndicator)) {
            return false;
        } else {
            throw new IllegalArgumentException("Something wrong. Could not indicate if shot is a hit or a miss!");
        }
    }

    protected void markTheShotOnBoard(Coordinate coordinate) {
        int row = coordinate.getRow();
        int column = coordinate.getColumn();

        if (board[row - 1][column - 1] == 'O') {
            board[row - 1][column - 1] = 'X';
        } else if (board[row - 1][column - 1] == '~') {
            board[row - 1][column - 1] = 'M';
        }
    }

    protected boolean hasOccupiedNeighborhood(int rowIndex, int columnIndex) {
        //true, if neighbor is occupied ('O')
        boolean lowerNeighborOccupied = rowIndex + 1 < ROWS && board[rowIndex + 1][columnIndex] == 'O';
        boolean upperNeighborOccupied = rowIndex - 1 >= 0 && board[rowIndex - 1][columnIndex] == 'O';
        boolean rightNeighborOccupied = columnIndex + 1 < COLUMNS && board[rowIndex][columnIndex + 1] == 'O';
        boolean leftNeighborOccupied = columnIndex - 1 >= 0 && board[rowIndex][columnIndex - 1] == '0';

        return lowerNeighborOccupied || upperNeighborOccupied || rightNeighborOccupied || leftNeighborOccupied;
    }

    protected boolean allShipsSunk() {
        boolean allShipSunk = true;
        for (char[] chars : board) {
            for (char aChar : chars) {
                if (aChar == 'O') {
                    allShipSunk = false;
                }
            }
        }
        return allShipSunk;
    }

    private boolean isAlignmentCorrect(int rowA, int rowB, int columnA, int columnB) {
        /*
        Ship is horizontally when row is identical
        Ship is vertically when column is identical
         */
        return (rowA == rowB || columnA == columnB);
    }

    private boolean isLengthCorrect(int rowA, int rowB, int columnA, int columnB, int length) {
        int columnDifference = Math.abs(columnA - columnB);
        int rowDifference = Math.abs(rowA - rowB);

        /*
        Vertically: Ship has correct length when the absolute value of the column difference is equal to length - 1
        Horizontally: Ship has correct length when the absolute value of the row (char has an ASCII or whatever number) difference is equal to length - 1
         */
        return (columnDifference == length - 1 || rowDifference == length - 1);
    }
}
