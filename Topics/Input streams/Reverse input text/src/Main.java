import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static final int MAX_LENGTH = 50;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] chars = new char[MAX_LENGTH];
        int count = reader.read(chars);
        for (int i = count - 1; i >= 0; i--) {
            System.out.print(chars[i]);
        }

        reader.close();
    }
}