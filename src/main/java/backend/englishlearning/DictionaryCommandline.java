package backend.englishlearning;

/**
 * Manages the command-line interface of the dictionary.
 */
public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement; // Manages dictionary operations

    /**
     * Constructs a new DictionaryCommandline object with a DictionaryManagement instance.
     */
    public DictionaryCommandline() {
        dictionaryManagement = new DictionaryManagement();
    }

    /**
     * Displays all words in the dictionary along with their English and Vietnamese meanings.
     *
     * @param dictionary The dictionary to display.
     */
    public void showAllWords(Dictionary dictionary) {
        System.out.println("No  | English              | Vietnamese");
        int index = 1;
        for (Word word : dictionary.getWords()) {
            System.out.printf("%-3d | %-20s | %-20s\n", index++, word.getWordTarget(), word.getWordExplain());
        }
    }

    /**
     * Performs basic dictionary operations: inserts words from the command line input and displays all words.
     *
     * @param dictionary The dictionary to operate on.
     */
    public void dictionaryBasic(Dictionary dictionary) {
        dictionaryManagement.insertFromCommandLine(dictionary);
        showAllWords(dictionary);
    }
}
