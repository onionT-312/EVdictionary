package backend.englishlearning;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a word in the dictionary.
 */
public class Word {
    private final StringProperty wordTarget = new SimpleStringProperty(); // The target word
    private final StringProperty wordExplain = new SimpleStringProperty(); // The explanation of the word

    /**
     * Constructs a new Word object with the specified target word and explanation.
     *
     * @param wordTarget  The target word.
     * @param wordExplain The explanation of the word.
     */
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget.set(wordTarget);
        this.wordExplain.set(wordExplain);
    }

    /**
     * Gets the target word.
     *
     * @return The target word.
     */
    public String getWordTarget() {
        return wordTarget.get();
    }

    /**
     * Gets the property representing the target word.
     *
     * @return The property representing the target word.
     */
    public StringProperty wordTargetProperty() {
        return wordTarget;
    }

    /**
     * Sets the target word.
     *
     * @param wordTarget The target word to set.
     */
    public void setWordTarget(String wordTarget) {
        this.wordTarget.set(wordTarget);
    }

    /**
     * Gets the explanation of the word.
     *
     * @return The explanation of the word.
     */
    public String getWordExplain() {
        return wordExplain.get();
    }

    /**
     * Gets the property representing the explanation of the word.
     *
     * @return The property representing the explanation of the word.
     */
    public StringProperty wordExplainProperty() {
        return wordExplain;
    }

    /**
     * Sets the explanation of the word.
     *
     * @param wordExplain The explanation of the word to set.
     */
    public void setWordExplain(String wordExplain) {
        this.wordExplain.set(wordExplain);
    }
}
