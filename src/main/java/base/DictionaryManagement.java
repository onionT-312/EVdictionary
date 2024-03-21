package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {
    public DictionaryManagement() {
        super();
    }

    public void insertFromCommandLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of words to add: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (int i = 0; i < n; i++) {
            System.out.print("Enter English word " + (i + 1) + ": ");
            String target = scanner.nextLine();
            System.out.print("Enter Vietnamese explanation " + (i + 1) + ": ");
            String explain = scanner.nextLine();
            this.addWord(new Word(target, explain));
        }
    }

    public void insertFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    String target = parts[0];
                    String explain = parts[1];
                    this.addWord(new Word(target, explain));
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void dictionaryLookup() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the English word to lookup: ");
//        String target = scanner.nextLine();
//        Word word = this.lookup(target);
//        if (word != null) {
//            System.out.println("Vietnamese explanation: " + word.getWord_explain());
//        } else {
//            System.out.println("Word not found.");
//        }
//    }

    public void dictionaryLookup() {
        String filePath = "src/main/resources/data/dictionaries.txt";

        // Đọc từ file và tìm kiếm từ
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            Scanner inputScanner = new Scanner(System.in);
            System.out.print("Enter the English word to lookup: ");
            String target = inputScanner.nextLine();

            boolean found = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Tách từ và nghĩa bằng dấu tab
                String[] parts = line.split("\t");
                if (parts.length == 2 && parts[0].equalsIgnoreCase(target)) {
                    System.out.println("Vietnamese explanation: " + parts[1]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Word not found.");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            e.printStackTrace();
        }
    }

    public void addWord(Word word) {
        getWordsSorted().add(word);
    }

    public void updateWord(String target, Word newWord) {
        Word oldWord = lookup(target);
        if (oldWord != null) {
            oldWord.setWord_explain(newWord.getWord_explain());
        }
    }

    public void removeWord(String target) {
        Word wordToRemove = null;
        for (Word word : getWordsSorted()) {
            if (word.getWord_target().equalsIgnoreCase(target)) {
                wordToRemove = word;
                break;
            }
        }
        if (wordToRemove != null) {
            getWordsSorted().remove(wordToRemove);
        }
    }

    public void dictionaryExportToFile(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            Map<String, String> englishWords = new HashMap<>();
            ArrayList<Word> words = getWordsSorted();

            for (Word word : words) {
                String english = word.getWord_target();
                String vietnamese = word.getWord_explain();

                // Kiểm tra xem từ tiếng Anh đã tồn tại trong map chưa
                if (englishWords.containsKey(english)) {
                    // Nếu đã tồn tại, kiểm tra nghĩa tiếng Việt
                    if (!englishWords.get(english).equals(vietnamese)) {
                        // Nếu nghĩa tiếng Việt không trùng, kết hợp lại
                        vietnamese = englishWords.get(english) + ", " + vietnamese;
                    }
                }

                // Lưu từ tiếng Anh và nghĩa tiếng Việt vào map
                englishWords.put(english, vietnamese);
            }

            // Ghi từ điển vào tệp tin
            for (Map.Entry<String, String> entry : englishWords.entrySet()) {
                writer.write(entry.getKey() + "\t" + entry.getValue() + "\n");
            }

            writer.close();
            System.out.println("Dictionary exported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
