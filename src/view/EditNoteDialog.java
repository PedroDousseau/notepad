package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.EditNote;

public class EditNoteDialog extends JDialog {

    private JButton btnSave;
    private JButton btnCancel;
    private JTextArea textArea;

    public EditNoteDialog(Frame parent, String fileName) {
        super(parent, fileName.replace(".txt", ""), true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        textArea = new JTextArea();
        textArea.setText(EditNote.getFileContent(fileName));

        panel.add(textArea, cs);
        cs.fill = GridBagConstraints.HORIZONTAL;

        btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (EditNote.saveFile(fileName, textArea.getText())) {
                    JOptionPane.showMessageDialog(EditNoteDialog.this, "Your note has been saved.", "Alert", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(EditNoteDialog.this, "Oops! Something went wrong", "Alert", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeDialog homeDialog = new HomeDialog(parent);
                homeDialog.setVisible(true);
            }
        });

        JPanel bp = new JPanel();
        bp.add(btnSave);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

}