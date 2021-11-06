package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.XPunisher;
import com.nort721.xpunisher.data.PlayerData;
import com.nort721.xpunisher.data.enums.Language;
import com.nort721.xpunisher.data.enums.SearchType;
import com.nort721.xpunisher.utils.MongoUtil;
import com.nort721.xpunisher.utils.TranslationUtil;
import com.nort721.xpunisher.utils.console.Console;
import com.nort721.xpunisher.utils.console.LogType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JFrame {

    private JPanel panel1;
    private JTextField textField1;
    private JButton searchButton;
    private JComboBox searchTypeCB;
    private JButton addPunishmentButton;
    private JLabel accessLevelLabel;
    private JLabel searchPlayerLabel;

    public ControlPanel() {
        super("XPunisher - ControlPanel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(420, 270));
        setResizable(false);

        XPunisher.LABELS.add(accessLevelLabel);
        XPunisher.LABELS.add(searchPlayerLabel);
        XPunisher.BUTTONS.add(searchButton);
        XPunisher.BUTTONS.add(addPunishmentButton);

        String[] items = {"By name", "By SteamID"};

        if (TranslationUtil.currentLanguage == Language.HEBREW) {
            Console.log("translating menu language to hebrew . . .", LogType.INFO);
            for (JLabel label : XPunisher.LABELS)
                label.setText(TranslationUtil.convertToHebrew(label.getText()));
            for (JButton button : XPunisher.BUTTONS)
                button.setText(TranslationUtil.convertToHebrew(button.getText() + ""));
            for (int i = 0; i < items.length; i++)
                items[i] = TranslationUtil.convertToHebrew(items[i]);
            accessLevelLabel.setText(TranslationUtil.convertToHebrew(accessLevelLabel.getText()));
        }

        accessLevelLabel.setText(accessLevelLabel.getText() + " " + XPunisher.loggedUser.getAccessLevel().getName());

        for (String str : items)
            searchTypeCB.addItem(str);

        add(panel1);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Console.log("searching player data", LogType.INFO);

                PlayerData playerData = MongoUtil.getPlayerByName(textField1.getText(), SearchType.getByName(String.valueOf(searchTypeCB.getSelectedItem())));

                if (playerData == null) {
                    Console.log("could not find player data", LogType.INFO);
                    JOptionPane.showMessageDialog(null, "Player not found", "Operation failed", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                Console.log("opening player data . . .", LogType.INFO);
                PlayerDataMenu playerDataMenu = new PlayerDataMenu(playerData);
            }
        });

        addPunishmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Console.log("opening add punishment menu . . .", LogType.INFO);
                AddPunishmentMenu addPunishmentMenu = new AddPunishmentMenu();
            }
        });
    }
}
