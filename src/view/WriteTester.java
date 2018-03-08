package view;

import file_managers.FileWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriteTester {
    public static void main(String[] args) {
        String testString = "to spiral [ :len ] [ if less? :len 200 [ fd :len rt 89 spiral + :len 3] ]";
        List<String> tester = new ArrayList<>(Arrays.asList(testString.split(" ")));
        FileWriter cw = new FileWriter();
        try {
            cw.writeToFile("TESTME.txt", "Variable\n", tester);
        }
        catch (Exception e) {
            System.out.println("Bad input");
        }
    }
}
