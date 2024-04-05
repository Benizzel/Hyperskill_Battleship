class FixingExceptions {
    public static void calculateSquare(int[] array, int index) {
        try {
            int square = (int) Math.pow(array[index], 2);
            System.out.println(square);
        } catch (Exception e) {
            System.out.println("Exception!");
        }
    }
}