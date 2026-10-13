/*
 * Violet Gonzalez, October 12, 2026
 * Week Two Assignment - Specifically the Pipeline style
 * Professor Cristina Videira Lopes
 */

/*
 * The task of this program is to follow the Term Frequency program using the Pipeline style.
 * this lastly, part 3 of a three part assignment.
 */

/*
 * Quick notation - I will be using LinkedHashmap to try and match pythons
 * dicts insertion order. LinkedHashmap keeps that true to her code, as opposed to
 * a just hashmap which holds no order
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeekTwoPipeline {
    // Read file
    static String readFile(String pathToFile) throws IOException {
        String fileText = Files.readString(Path.of(pathToFile));
        return fileText;
    }

    // Filter and clean
    static String filterCharAndClean(String strData) {
        return strData.replaceAll("[\\W_]+", " ").toLowerCase();
    }

    // Scanning for words
    static List<String> scan(String scanData) {
        // We need to split on the whiespaces
        String[] splitting = scanData.split("\\s+");
        List<String> wordList = new ArrayList<>();

        // Copies splitting into our wordList
        for (int i = 0; i < splitting.length; i++) {
            if (! splitting[i].isEmpty()) {
                wordList.add(splitting[i]);
            }
        }
        return wordList;
    }

    // Remove Stop words
    static List<String> removeStopWords(List<String> wordList) throws IOException {
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

        ArrayList<String> filteredWords = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            if (! stopWords.contains(wordList.get(i))) {
                filteredWords.add(wordList.get(i));
            }
        }
        return filteredWords;
    }

    // frequencies which will match the words and the counts together
    static Map<String, Integer> frequencies (List<String> wordList) {
        /*
         * Quick notation - I will be using LinkedHashmap to try and match pythons
         * dicts insertion order. LinkedHashmap keeps that true to her code, as opposed to
         * a just hashmap which holds no order
         */
    }

    public static void main(String[] args) throws IOException {
        // Error handling:
        if (args.length == 0) {
            System.err.println("Needed: java WeekTwoPipeline.java ../pride-and-prejudice.txt");
            System.err.println("Unable to proceed");

            return;
        }
    }
}


