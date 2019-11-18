package controller;

import java.io.File;

import controller.Login;

import view.HomeDialog;

public class NewNote {

    private static String notesDirectoryName = "notes";
    private static String userKey = Login.getLoggedUser().getEmail();
    private static String noteExtension = ".txt";

    public static String createNewNote(String title) {
        String userDirectoryPath = notesDirectoryName + "/" + userKey;
        Boolean fileHasBeenCreated = false;
        String msg;

        File directory = new File(userDirectoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(userDirectoryPath + "/" + title + noteExtension);
        try {
            fileHasBeenCreated = file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(fileHasBeenCreated){
            
            msg = "Note created.";
        } else {
            msg = "You already have a note with this name.";
        }
        

        return msg;
    }

}