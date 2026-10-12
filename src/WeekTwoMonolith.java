/*
* Violet Gonzalez, October 11, 2026
* Week Two Assignment - Specifically the Monolith style
* Professor Cristina Videira Lopes
*/

/*
* The task of this program is to follow the Term Frequency program using the Monolith style.
* this is just part 1 of a three part assignment.
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class WeekTwoMonolith {
    public static void main (String[] args) throws IOException {

        // An empty ArrayList that will be collecting the actual words
        ArrayList<String> words = new ArrayList<>();

        // An empty ArrayList that will be collecting the count of the words
        ArrayList<Integer> counts = new ArrayList<>();

        // An empty ArrayList that will be collecting the stop words
        ArrayList<String> stopWords = new ArrayList<>();

        // Read the Stop file
        Path stopPath = Path.of("../stop_words.txt");

        String stop = Files.readString(stopPath);

        // Splitting the commas between the stop words into the stop words array
        String[] splitStop = stop.split(",");


        // Bract check : Will need to be deleted for final output:
        int lastIndex = splitStop.length - 1;
        String lastStop = splitStop[lastIndex];
        System.out.println(lastStop);
        System.out.println(lastStop.length());

        // Fill the stopWords list

        // Copies words from splitStop into stopWords
        for (int i = 0; i < splitStop.length; i++) {
            stopWords.add(splitStop[i]);
        }

        // Add now a-z into the stopWords
        for (char letter = 'a'; letter <= 'z'; letter++) {
            stopWords.add(String.valueOf(letter));
        }

        System.out.println(splitStop.length);
        System.out.println(stopWords.size());


    }

}
