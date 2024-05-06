package evapp.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import evapp.base.*;

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
            String target = "";
            String explain = "";
            while (scanner.hasNextLine()) {
                target = scanner.nextLine(); // Dòng đầu tiên là từ tiếng Anh
                if (scanner.hasNextLine()) {
                    explain = scanner.nextLine(); // Dòng thứ hai là nghĩa
                    this.addWord(new Word(target, explain));
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dictionaryLookup() {
        String filePath = "src/main/resources/data/dictionaries.txt";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the English word to lookup: ");
        String target = scanner.nextLine();

        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            boolean found = false;
            while (fileScanner.hasNextLine()) {

                // Lấy từ và nghĩa từ phần tách
                String word = fileScanner.nextLine();
                String meaning = fileScanner.nextLine();

                // Kiểm tra xem từ có khớp không
                if (word.equalsIgnoreCase(target)) {
                    System.out.println("Vietnamese explanation: " + meaning);
                    found = true;
                    break;
                }
            }

            // Nếu không tìm thấy từ
            if (!found) {
                System.out.println("Word not found.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found: " + filePath);
        }
    }

       private static final String FILE_PATH = "src/main/resources/data/dictionaries.txt";

        // Phương thức thêm từ mới vào file
        public void addWord(String word, String definition) {
            try {
                FileWriter writer = new FileWriter(FILE_PATH, true);
                writer.write("\n" + word + "\n" + definition);
                writer.close();
                System.out.println("Word added successfully!");
            } catch (IOException e) {
                System.out.println("An error occurred while adding the word: " + e.getMessage());
            }
        }

        // Phương thức xoá từ khỏi file
        // Phương thức xoá từ khỏi file
        public static void removeWord(String target) {
            try {
                List<String> lines = new ArrayList<>();
                Scanner scanner = new Scanner(new File(FILE_PATH));

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (!line.equalsIgnoreCase(target)) {
                        lines.add(line);
                    } else {
                        // Bỏ qua dòng chứa từ cần xoá và dòng kế tiếp (nghĩa của từ)
                        scanner.nextLine();
                    }
                }
                scanner.close();

                FileWriter writer = new FileWriter(FILE_PATH);
                for (String line : lines) {
                    writer.write(line + "\n");
                }
                writer.close();
                System.out.println("Word removed successfully!");
            } catch (IOException e) {
                System.out.println("An error occurred while removing the word: " + e.getMessage());
            }
        }





    public void updateWord(String target, Word newWord) {
        Word oldWord = lookup(target);
        if (oldWord != null) {
            oldWord.setWord_explain(newWord.getWord_explain());
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
