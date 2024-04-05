import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] twoDimArray = new int[n][m];
        int[][] twoDimClockwise = new int[m][n];

        /*
        scan input into array
         */
        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0; j < twoDimArray[i].length; j++) {
                twoDimArray[i][j] = scanner.nextInt();
            }
        }

        /*
        Transform input clockwise by 90 degree into new array
        Index 00 (i_old/j_old) in twoDimArray has to move to Index 02 (i_new/j_new) of twoDimClockwise
        00 -> 02
        01 -> 12
        ...
        10 -> 01
        11 -> 11
        ...
        20 -> 00
        21 -> 10
        ...
        Rule: i_new = j_old / j_new = n - 1 - i_old
         */
        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0; j < twoDimArray[i].length; j++) {
                twoDimClockwise[j][n - 1 - i] = twoDimArray[i][j];
            }
        }

        /*
        Print new array
        First "for" takes every sub-array within twoDimClockwise as "ints"
        Second "for" prints every in within the in array
         */
        for (int[] ints : twoDimClockwise) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        /*
        Possibility two without for-each

        for (int i = 0; i < twoDimClockwise.length; i++) {
            for (int j = 0; j < twoDimClockwise[i].length; j++) {
                System.out.print(twoDimClockwise[i][j] + " ");
            }
            System.out.println();
        }

        */
    }

}