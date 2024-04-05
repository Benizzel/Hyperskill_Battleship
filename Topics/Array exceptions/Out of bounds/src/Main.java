import java.util.*;

class FixingStringIndexOutOfBoundsException {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String string = scanner.nextLine();
        int index = scanner.nextInt();

        /*
        - String could be null? Probably not, because String could be empty, but has a reference
        - index could be negative
        - index could be bigger then array
         */
        if (string != null && index >= 0 && index <= string.length() - 1) {
            System.out.println(string.charAt(index));
        } else {
            System.out.println("Out of bounds!");
        }
    }
}