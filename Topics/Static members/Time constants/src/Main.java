class TimeConstantsDemo {

    public static void main(String[] args) {
        System.out.println(TimeConstants.SECONDS_IN_HOUR * 24);
        System.out.println(TimeConstants.SECONDS_IN_HOUR * 24 * 7);
        // print SECONDS_IN_DAY
        // print SECONDS_IN_WEEK
    }
}

final class TimeConstants {
    public static final int SECOND = 1;
    public static final int SECONDS_IN_MINUTE = 60 * SECOND;
    public static final int SECONDS_IN_HOUR = 60 * SECONDS_IN_MINUTE;
    // write your code here

    private TimeConstants() { }
}