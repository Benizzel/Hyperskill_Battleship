class ArrayOperations {
    public static void printCorners(int[][] twoDimArray) {
        int[] array1 = twoDimArray[0];
        int[] array2 = twoDimArray[twoDimArray.length - 1];

        System.out.println(array1[0] + " " +  array1[array1.length - 1]);
        System.out.println(array2[0] + " " + array2[array2.length - 1]);

    }
}