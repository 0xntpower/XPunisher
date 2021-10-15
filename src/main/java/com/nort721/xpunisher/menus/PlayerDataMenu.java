package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.data.PunishedData;
import com.nort721.xpunisher.data.Punishment;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerDataMenu extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JLabel usernameNameLabel;
    private JList punishmentsList;
    private JScrollBar scrollBar1;

    public PlayerDataMenu(PunishedData punishedData) {
        super("XPunisher - " + punishedData.getUsername() + "'s data");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(420, 470));
        setResizable(false);

        usernameNameLabel.setText("username: " + punishedData.getUsername());

        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }

    private void createUIComponents() {
        DefaultListModel<String> punishments = new DefaultListModel<>();
        punishmentsList = new JList(punishments);
        for (int i = 0; i < 100; i++) {
            punishments.add(i, i + "");
        }
        scrollBar1 = new JScrollBar(Adjustable.VERTICAL);
        scrollBar1.setValue(punishmentsList.getHeight());
    }
}
