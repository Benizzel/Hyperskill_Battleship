import java.io.InputStream;

class Main {
    public static void main(String[] args) throws Exception {
        try (InputStream inputStream = System.in) {
            int c = inputStream.read();
            while (c != -1) {
                System.out.print(c);
                c = inputStream.read();
            }
        }
    }
}