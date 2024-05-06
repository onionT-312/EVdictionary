package evapp.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Dictionary {
    private ArrayList<Word> words;

    public Dictionary() {
        words = new ArrayList<>();
    }

    public void addWord(Word word) {
        words.add(word);
    }


    public Word lookup(String target) {
        for (Word word : words) {
            if (word.getWord_target().equalsIgnoreCase(target)) {
                return word;
            }
        }
        return null;
    }

    public ArrayList<Word> getWordsSorted() {
        Collections.sort(words, Comparator.comparing(Word::getWord_target));
        return words;
    }

    public int getSize() {
        return words.size();
    }

}
