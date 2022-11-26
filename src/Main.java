import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        int count = 0;
        int charc = 0;

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                System.out.println("'" + selectedFile.getName() + "'" + " has been selected.");

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    System.out.printf("\nLine %4d %-60s ", line, rec);
                    String[] words = rec.split(" ");
                    count = count + words.length;
                    String[] wordArray = rec.split("");
                    charc = charc + wordArray.length;
                }
                System.out.println("\n\nThere are '" + line + "' lines in that document.");
                System.out.println("\nThere are '" + count + "' words in that document.");
                System.out.println("\nThere are '" + charc + "' characters in that document.");
                reader.close();
                System.out.println("\n\nData file read!");

            } else {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
