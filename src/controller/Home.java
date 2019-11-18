package controller;

import java.io.File;

import javax.swing.DefaultListModel;

import controller.Login;

public class Home {

    private static File userNotesPath = new File("notes/" + Login.getLoggedUser().getEmail() + "/");

    public static DefaultListModel getUserNotes() {
        DefaultListModel notesList = new DefaultListModel<String>();

        for (final File fileEntry : userNotesPath.listFiles()) {
            notesList.add(notesList.getSize(), fileEntry.getName());
        }

        return notesList;
    }

}