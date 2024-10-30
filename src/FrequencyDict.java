import java.io.*;
import java.util.HashMap;

public class FrequencyDict {
    // idx in dict is ascii code of a symbol
    private final int[] dict;

    FrequencyDict() {
        dict = new int[91];
    }

    public void calcFromFile(String filePath) throws FileNotFoundException, IOException {
        FileReader fis = new FileReader(filePath);
        int charCode = 0;
        while ((charCode = fis.read()) != -1) {
            if (65 <= charCode && charCode <= 90) { // A is 65 in ASCII, Z - 90
                dict[charCode]++;
            } else if (97 <= charCode && charCode <= 122) { // a is 97, z - 122
                dict[charCode - 32]++; // lowercase english char code - 32 = uppercase english char code
            }
        }
        fis.close();
    }

    public void saveToFile(String filePath) throws IOException {
        FileWriter fos = new FileWriter(filePath);
        for (int i = 65; i < dict.length; i++) {
            fos.write(String.format("%c - %d\n", i, dict[i]));
        }
        fos.close();
    }

    public void print() {
        for (int i = 0; i < dict.length; i++) {
            System.out.println(String.format("%c = %d", i, dict[i]));
        }
    }
}
