package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.data.PunishedData;

import javax.swing.*;
import java.awt.*;

public class PlayerData extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JLabel usernameNameLabel;

    public PlayerData(PunishedData punishedData) {
        super("XPunisher - " + punishedData.getUsername() + "'s data");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(420, 270));
        setResizable(false);

        usernameNameLabel.setText("username: " + punishedData.getUsername());

        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);


    }
}
