package evapp.base;

public class Main {
    public static void main(String[] args) {
        // Tạo một đối tượng DictionaryManagement
        DictionaryManagement management = new DictionaryManagement();

        DictionaryCommandline commandline = new DictionaryCommandline();
        System.out.println("------------------------------------");
        commandline.dictionaryAdvanced();
    }
}