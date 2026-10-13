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
import java.util.List;


public class WeekTwoMonolith {
    public static void main (String[] args) throws IOException {

        // Verify if a file was given lets, provide feedback for wrong input and then exits the program
        if (args.length == 0) {
            System.err.println("Needed: java WeekTwoMonolith.java ../pride-and-prejudice.txt");
            System.err.println("Unable to proceed");

            return;
        }

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

        // Copies words from splitStop into stopWords
        for (int i = 0; i < splitStop.length; i++) {
            stopWords.add(splitStop[i]);
        }

        // Add now a-z into the stopWords
        for (char letter = 'a'; letter <= 'z'; letter++) {
            stopWords.add(String.valueOf(letter));
        }

        // Now we actually need to read the book - pride and prejudice
        List<String> containBookLines = Files.readAllLines(Path.of(args[0]));

        // Loop through the lines of the book
        for (int i = 0; i < containBookLines.size(); i++) {
            String line = containBookLines.get(i) + "\n";
            int startPosition = -1;

            //  This loop will help walk the character
            for (int j = 0; j < line.length(); j++) {
                char characterWalk = line.charAt(j);

                // outline the logic
                if (startPosition == -1) {
                    if (Character.isLetterOrDigit(characterWalk)) {
                        startPosition = j;
                    }
                } else {
                    if (! Character.isLetterOrDigit(characterWalk)) {
                        String bookWord = line.substring(startPosition, j).toLowerCase();
                        if (! stopWords.contains(bookWord)) {
                            boolean found = false;
                            int position = -1;

                            for (int k = 0; k < words.size(); k++) {
                                boolean positionMatch = words.get(k).equals(bookWord);
                                if (positionMatch) {
                                    counts.set(k, counts.get(k) + 1);
                                    found = true;
                                    position = k;
                                    break;
                                }
                            }

                            // if I didn't find the word
                            if (! found) {
                                words.add(bookWord);
                                counts.add(1);
                            } else {
                                // A loop to resort
                                for (int n = position - 1; n >= 0; n--) {
                                    if (counts.get(position) > counts.get(n)) {
                                        // words swap
                                        String temp = words.get(n);
                                        words.set(n, words.get(position));
                                        words.set(position, temp);
                                         // count swap
                                        Integer tempCount = counts.get(n);
                                        counts.set(n, counts.get(position));
                                        counts.set(position, tempCount);

                                        position = n;

                                    }
                                }
                            }

                        }

                        startPosition = -1;
                    }
                }
            }
        }

        // The final output
        for (int check = 0; check < 25; check++) {
            System.out.println(words.get(check) + "  " + "-" + "  " + counts.get(check));
        }

    }

}
