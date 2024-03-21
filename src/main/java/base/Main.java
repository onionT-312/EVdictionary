package base;

public class Main {
    public static void main(String[] args) {
        // Tạo một đối tượng DictionaryManagement
        DictionaryManagement management = new DictionaryManagement();

////         Thực hiện nhập từ điển từ tệp dictionaries.txt
//        management.insertFromFile("src/resources/dictionaries.txt");

        DictionaryCommandline commandline = new DictionaryCommandline();
        System.out.println("------------------------------------");
        commandline.dictionaryAdvanced();
    }
}