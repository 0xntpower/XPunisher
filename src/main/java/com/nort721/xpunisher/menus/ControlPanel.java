package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.XPunisher;
import com.nort721.xpunisher.data.LoggedUser;
import com.nort721.xpunisher.data.PunishedData;
import com.nort721.xpunisher.data.enums.AccessLevel;
import com.nort721.xpunisher.data.enums.SearchType;
import com.nort721.xpunisher.utils.MongoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JFrame {

    private JPanel panel1;
    private JTextField textField1;
    private JButton searchButton;
    private JComboBox comboBox1;
    private JButton addPunishmentButton;
    private JLabel accessLevelLabel;

    public ControlPanel() {
        super("XPunisher - ControlPanel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(420, 270));
        setResizable(false);

        accessLevelLabel.setText("Access-Level: " + XPunisher.loggedUser.getAccessLevel().getName());

        int accessLevelID = XPunisher.loggedUser.getAccessLevel().getAccessId();

        String[] items = {"By name", "By SteamID", "By Punish Reason"};

        if (accessLevelID >= AccessLevel.ADMIN.getAccessId()) {
            // remove here all items that admins shouldn't access
        }

        for (String str : items)
            comboBox1.addItem(str);

        add(panel1);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PunishedData punishedData = MongoUtil.getPunishedPlayerDataByUsername(textField1.getText(), SearchType.getByName(String.valueOf(comboBox1.getSelectedItem())));

                if (punishedData == null) {
                    JOptionPane.showMessageDialog(null, "Player not found", "Operation failed", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

            }
        });

        addPunishmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
