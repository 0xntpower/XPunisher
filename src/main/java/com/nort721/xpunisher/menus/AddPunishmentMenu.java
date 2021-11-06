package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.XPunisher;
import com.nort721.xpunisher.data.PlayerData;
import com.nort721.xpunisher.data.Punishment;
import com.nort721.xpunisher.data.enums.AccessLevel;
import com.nort721.xpunisher.data.enums.Language;
import com.nort721.xpunisher.data.enums.PunishType;
import com.nort721.xpunisher.utils.MongoUtil;
import com.nort721.xpunisher.utils.TranslationUtil;
import com.nort721.xpunisher.utils.console.Console;
import com.nort721.xpunisher.utils.console.LogType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPunishmentMenu extends JFrame {
    private JPanel panel1;
    private JTextField steamIdTextField;
    private JTextField playerNameTextField;
    private JButton addButton;
    private JComboBox comboBoxPunishment;
    private JTextField textFieldDuration;
    private JLabel durationLabel;
    private JButton cancelButton;
    private JLabel playerNameLabel;
    private JLabel steamIdLabel;
    private JLabel reasonLabel;
    private JLabel dateLabel;
    private JLabel punishmentLabel;
    private JComboBox reasonComboBox;
    private JTextField dateTextField;

    public AddPunishmentMenu() {
        super("XPunisher - Add Punishment");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(370, 440));
        setResizable(false);

        XPunisher.LABELS.add(playerNameLabel);
        XPunisher.LABELS.add(steamIdLabel);
        XPunisher.LABELS.add(dateLabel);
        XPunisher.LABELS.add(punishmentLabel);
        XPunisher.LABELS.add(durationLabel);
        XPunisher.BUTTONS.add(addButton);
        XPunisher.BUTTONS.add(cancelButton);

        String[] punishmentItems = {"warning", "ban", "kick"};
        String[] reasonItems = {"RDM", "VDM", "NLR", "Revenge kill", "Team kill", "FailRP", "FearRP", "PowerGaming",
                "None RP driving", "MetaGaming", "AutoEat", "CombatLog", "CopBaiting", "Farming", "Break character", "Job abuse", "Disrespect staff/player"};

        if (TranslationUtil.currentLanguage == Language.HEBREW) {
            Console.log("translating menu language to hebrew . . .", LogType.INFO);
            for (JLabel label : XPunisher.LABELS)
                label.setText(TranslationUtil.convertToHebrew(label.getText()));
            for (JButton button : XPunisher.BUTTONS)
                button.setText(TranslationUtil.convertToHebrew(button.getText() + ""));
            for (int i = 0; i < punishmentItems.length; i++)
                punishmentItems[i] = TranslationUtil.convertToHebrew(punishmentItems[i]);
//            for (int i = 0; i < reasonItems.length; i++)
//                reasonItems[i] = TranslationUtil.convertToHebrew(reasonItems[i]);
        }

        durationLabel.setVisible(false);
        textFieldDuration.setVisible(false);

        for (String str : punishmentItems)
            comboBoxPunishment.addItem(str);

        for (String str : reasonItems)
            reasonComboBox.addItem(str);

        add(panel1);
        pack();
        setLocationRelativeTo(null);
        //dateFormattedTextField1.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter()));
        setVisible(true);

        comboBoxPunishment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean toggle;

                toggle = String.valueOf(comboBoxPunishment.getSelectedItem()).equals("ban")
                        || String.valueOf(comboBoxPunishment.getSelectedItem()).equals(TranslationUtil.convertToHebrew("ban"));

                durationLabel.setVisible(toggle);
                textFieldDuration.setVisible(toggle);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerData playerData = new PlayerData(playerNameTextField.getText(), steamIdTextField.getText());

                PunishType type;

                if (textFieldDuration.isVisible()) {
                    if (textFieldDuration.getText().equalsIgnoreCase("forever"))
                        type = PunishType.PERMABAN;
                }

                Punishment punishment = new Punishment(PunishType
                        .getPunishTypeFromString(comboBoxPunishment.getSelectedItem() + ""),
                        dateTextField.getText(), reasonComboBox.getSelectedItem() + "");

                if (XPunisher.loggedUser.getAccessLevel().getAccessId() < AccessLevel.MANAGER.getAccessId()) {
                    punishment.setPending(true);
                }

                MongoUtil.savePunishment(playerData, punishment);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
