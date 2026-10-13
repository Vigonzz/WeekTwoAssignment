/*
 * Violet Gonzalez, October 12, 2026
 * Week Two Assignment - Specifically the CookBook style
 * Professor Cristina Videira Lopes
 */

/*
 * The task of this program is to follow the Term Frequency program using the CookBook style.
 * this is part 2 of a three part assignment.
 */


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
            int store = stopPositions.get(n);
            words.remove(store);
        }
    }

    // Frequencies - matches the words with the counts
    static void frequencies() {

        for (int i = 0; i < words.size(); i++) {
            String currentWord = words.get(i);
            int position = freqWords.indexOf(currentWord);

            if (position != - 1) {
                freqCounts.set(position, freqCounts.get(position) + 1);
            } else {
                freqWords.add(currentWord);
                freqCounts.add(1);
            }
        }
    }

    // The positions in our program
    static void sort() {
        ArrayList<Integer> order = new ArrayList<>();

        for (int i = 0; i < freqWords.size(); i++) {
            order.add(i);
        }
        order.sort((a,b) -> Integer.compare(freqCounts.get(b), freqCounts.get(a)));

        // Rebuilding the list in a sorted order

        List<String> sortedWords = new ArrayList<>();
        List<Integer> sortedCounts = new ArrayList<>();

        for (int i = 0; i < order.size(); i++) {
            int ticket = order.get(i);
            sortedWords.add(freqWords.get(ticket));
            sortedCounts.add(freqCounts.get(ticket));
        }

        freqWords = sortedWords;
        freqCounts = sortedCounts;

    }


    public static void main (String[] args) throws IOException {

        // Error handling:
        if (args.length == 0) {
            System.err.println("Needed: java WeekTwoCookBook.java ../pride-and-prejudice.txt");
            System.err.println("Unable to proceed");

            return;
        }
        readFile(args[0]);
        filterAndCleanChar();
        scan();
        removeStopWords();
        frequencies();
        sort();

        for (int check = 0; check < 25; check++) {
            System.out.println(freqWords.get(check) + "  " + "-" + "  " + freqCounts.get(check));
        }

    }
}
