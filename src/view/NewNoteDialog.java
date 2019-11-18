package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.NewNote;

public class NewNoteDialog extends JDialog {

    private JTextField tfNoteTitle;
    private JLabel lbNoteTitle;
    private JButton btnAddNote;
    private JButton btnCancel;

    public NewNoteDialog(Frame parent) {
        super(parent, "New note", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbNoteTitle = new JLabel("Name it: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbNoteTitle, cs);

        tfNoteTitle = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfNoteTitle, cs);

        tfNoteTitle.getDocument().addDocumentListener(new DocumentListener() {

            public void changedUpdate(DocumentEvent e) {
                changed();
            }

            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            public void changed() {
                if (tfNoteTitle.getText().equals("")) {
                    btnAddNote.setEnabled(false);
                } else {
                    btnAddNote.setEnabled(true);
                }

            }
        });

        btnAddNote = new JButton("Add note");
        btnAddNote.setEnabled(false);
        btnAddNote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String msg = NewNote.createNewNote(getNoteTitle());

                JOptionPane.showMessageDialog(NewNoteDialog.this, msg, "Alert", JOptionPane.INFORMATION_MESSAGE);
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
        bp.add(btnAddNote);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public String getNoteTitle() {
        return tfNoteTitle.getText().trim();
    }

}