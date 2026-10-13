import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WeekTwoCookBook {
    // Shared data (static fields)
    static List<Character> data = new ArrayList<>();
    static List<String> words = new ArrayList<>();
    static List<String> freqWords = new ArrayList<>();
    static List<Integer> freqCounts = new ArrayList<>();

    // Read the file
    static void readFile(String pathToFile) throws IOException {
        String fileText = Files.readString(Path.of(pathToFile));

        // Fill our arrayList data with characters
        for (int i = 0; i < fileText.length(); i++) {
            data.add(fileText.charAt(i));
        }
    }

    // Filter through the characters and get them prepared for splitting
    static void filterAndCleanChar() {
        for (int i = 0; i < data.size(); i++) {
            if (!Character.isLetterOrDigit(data.get(i))) {
                data.set(i, ' ');
            } else {
                data.set(i, Character.toLowerCase(data.get(i)));
            }
        }
    }



    public static void main (String[] args) throws IOException {
        readFile(args[0]);
        filterAndCleanChar();
    }
}
