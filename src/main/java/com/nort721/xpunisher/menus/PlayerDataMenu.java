package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.data.PunishedData;

import javax.swing.*;
import java.awt.*;

public class PlayerDataMenu extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JLabel usernameNameLabel;

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
}
