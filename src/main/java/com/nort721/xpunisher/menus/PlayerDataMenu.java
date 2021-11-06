package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.data.PlayerData;

import javax.swing.*;
import java.awt.*;

public class PlayerDataMenu extends JFrame {
    private JPanel panel1;
    private JButton searchButton;
    private JLabel usernameNameLabel;
    private JList punishmentsList;
    private JScrollBar scrollBar1;
    private JTextField textField1;
    private JComboBox searchTypeCB;

    public PlayerDataMenu(PlayerData playerData) {
        super("XPunisher - " + playerData.getPlayerName() + "'s data");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(420, 470));
        setResizable(false);

        add(panel1);
        pack();
        setLocationRelativeTo(null);

        usernameNameLabel.setText("username: " + playerData.getPlayerName());

        String[] searchItems = { "By date", "By punish reason" };

        for (String item : searchItems)
            searchTypeCB.addItem(item);

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
