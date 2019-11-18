package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import controller.Home;

public class HomeDialog extends JDialog {
    private JButton btnNewNote;
    private DefaultListModel listModel;
    private JList notesList;
    private JScrollPane jscroll;

    public HomeDialog(Frame parent) {
        super(parent, "Home", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        listModel = Home.getUserNotes();
        notesList = new JList(listModel);
        jscroll = new JScrollPane(notesList);

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2) {
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = theList.getModel().getElementAt(index);

                        dispose();
                        EditNoteDialog editNoteDlg = new EditNoteDialog(parent, o.toString());
                        editNoteDlg.setVisible(true);
                    }
                }
            }
        };
        notesList.addMouseListener(mouseListener);

        panel.add(notesList, cs);
        cs.fill = GridBagConstraints.HORIZONTAL;

        JPanel bp = new JPanel();

        btnNewNote = new JButton("New note");
        btnNewNote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                NewNoteDialog newNoteDlg = new NewNoteDialog(parent);
                newNoteDlg.setVisible(true);
            }
        });

        bp.add(btnNewNote);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
}