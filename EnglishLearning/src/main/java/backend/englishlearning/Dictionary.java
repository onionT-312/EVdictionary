package backend.englishlearning;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words; // The list of words in the dictionary

    /**
     * Constructs a new Dictionary object with an empty list of words.
     */
    public Dictionary() {
        words = new ArrayList<>();
    }

    /**
     * Adds a word to the dictionary.
     * @param word The word to be added.
     */
    public void addWord(Word word) {
        words.add(word);
    }

    /**
     * Gets the list of words in the dictionary.
     * @return The list of words.
     */
    public ArrayList<Word> getWords() {
        return words;
    }

    /**
     * Gets the number of words in the dictionary.
     * @return The number of words.
     */
    public int getSize() {
        return words.size();
    }
}
