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

    // Building the String - turning the chars. into words
    static void scan() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            builder.append(data.get(i));
        }
        String dataString = builder.toString();

        // We need to split on the whiespaces
        String[] splitting = dataString.split("\\s+");

        // Copies splitting into words
        for (int i = 0; i < splitting.length; i++) {
            if (! splitting[i].isEmpty()) {
                words.add(splitting[i]);
            }
        }
    }

    // Remove the stop words
    static void removeStopWords() throws IOException {

        // Read the files
        Path stopPath = Path.of("../stop_words.txt");
        String stop = Files.readString(stopPath);

        // Splitting the commas between the stop words into the stop words array
        String[] splitStop = stop.split(",");

        // ArrayList for stop words
        ArrayList<String> stopWords = new ArrayList<>();

        // Copies words intp stopWords
        for (int i = 0; i < splitStop.length; i++) {
            stopWords.add(splitStop[i]);
        }
        // Add a-z
        for (char letter = 'a'; letter <= 'z'; letter++) {
            stopWords.add(String.valueOf(letter));
        }

        // Finding the positions
        ArrayList<Integer> stopPositions = new ArrayList<>();

        for (int i = 0; i < words.size(); i++){
            if (stopWords.contains(words.get(i))) {
                stopPositions.add(i);
            }
        }

        // A loop to resort backwards
        for (int n = stopPositions.size() - 1; n >= 0; n--) {
            int store = n;
            words.remove(store);
        }


            System.out.println(stopWords.size());

    }


    public static void main (String[] args) throws IOException {
        readFile(args[0]);
        filterAndCleanChar();
        scan();
        removeStopWords();

        for (int check = 0; check < 5; check++) {
            System.out.println(words.get(check));
        }

    }
}
