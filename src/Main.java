import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        String inputFilePath = "";
        String outputFilePath = "";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an input file path: ");
        inputFilePath = scanner.next();
        System.out.print("Enter an output file path: ");
        outputFilePath = scanner.next();
        scanner.close();

        FrequencyDict dict = new FrequencyDict();
        try {
            dict.calcFromFile(inputFilePath);
        } catch (Exception e) {
            System.out.println("wrong");
        }

        try {
            dict.saveToFile(outputFilePath);
        } catch (Exception e) {
            System.out.println("wrong");
        }

    }
}
