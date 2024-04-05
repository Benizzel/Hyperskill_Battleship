public class Main {

    public static void main(String[] args) {
        int counter = 0;

        for (Secret secret : Secret.values()) {
            if (secret.toString().startsWith("STAR")) {
                counter++;
            }
        }

        System.out.println(counter);
    }
}


//enum Secret {
//    STAR, CRASH, START, // ...
//}
