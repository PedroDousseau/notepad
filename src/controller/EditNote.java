package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class EditNote {

    public static String getFileContent(String fileName) {
        String fileContent = "";
        String storeAllString = "";
        String notePath = "notes/" + Login.getLoggedUser().getEmail() + "/" + fileName;

        try {
            FileReader read = new FileReader(notePath);
            Scanner scan = new Scanner(read);
            while (scan.hasNextLine()) {
                String temp = scan.nextLine() + "\n";
                storeAllString = storeAllString + temp;
            }

            fileContent = storeAllString;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return fileContent;
    }

    public static boolean saveFile(String fileName, String content) {
        String notePath = "notes/" + Login.getLoggedUser().getEmail() + "/" + fileName;
        File file = null;
        FileWriter out = null;

        try {
            file = new File(notePath);
            out = new FileWriter(file);

            out.write(content);
            out.close();
            
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}