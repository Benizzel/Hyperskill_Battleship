// You can experiment here, it won’t be checked

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task {
  public static void main(String[] args) throws IOException {
    File file = new File("/Users/benjaminwyss/Desktop/file.txt");

      /*
      * new FileWriter overwrites the file
      */
    try (FileWriter writer = new FileWriter(file, false)) {
      writer.write("Hello Schebubu\n");
      writer.write("I Love you\n");
      writer.write("Schnecke Eule\n");
      writer.close();
    } catch (IOException e) {
      System.out.printf("An exception occured: %s", e.getMessage());
    }

    /*
    * print file content in console
     */
    try {
      System.out.println(readFileAsString(file.getAbsolutePath()));
    } catch (IOException e) {
      System.out.println("Cannot read file: " + e.getMessage());
    }
  }

  public static String readFileAsString(String fileName) throws IOException {
    return new String(Files.readAllBytes(Paths.get(fileName)));
  }

}
