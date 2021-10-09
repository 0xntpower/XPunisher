package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.XPunisher;
import com.nort721.xpunisher.data.enums.AccessLevel;
import com.nort721.xpunisher.data.enums.Language;
import com.nort721.xpunisher.utils.TranslationUtil;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class AddPunishmentMenu extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JComboBox comboBoxDuration;
    private JTextField textFieldDuration;
    private JLabel durationLabel;
    private JButton cancelButton;
    private JLabel usernameLabel;
    private JLabel steamIdLabel;
    private JLabel reasonLabel;
    private JLabel dateLabel;
    private JLabel punishmentLabel;
    private JComboBox reasonComboBox;
    private JFormattedTextField dateFormattedTextField1;

    public AddPunishmentMenu() {
        super("XPunisher - Add Punishment");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(370, 440));
        setResizable(false);

        XPunisher.LABELS.add(usernameLabel);
        XPunisher.LABELS.add(steamIdLabel);
        XPunisher.LABELS.add(dateLabel);
        XPunisher.LABELS.add(punishmentLabel);
        XPunisher.LABELS.add(durationLabel);
        XPunisher.BUTTONS.add(addButton);
        XPunisher.BUTTONS.add(cancelButton);

        String[] punishmentItems = {"warning", "ban", "kick"};
        String[] reasonItems = {"RDM", "VDM", "NLR", "Revenge kill", "Team kill", "FailRP", "FearRP", "PowerGaming",
                "None RP driving", "MetaGaming", "AutoEat", "CombatLog", "CopBaiting", "Farming", "Break character", "Job abuse", "Disrespect staff/player"};

        dateFormattedTextField1.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter()));

        if (TranslationUtil.currentLanguage == Language.HEBREW) {
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
            comboBoxDuration.addItem(str);

        for (String str : reasonItems)
            reasonComboBox.addItem(str);

        add(panel1);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        comboBoxDuration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean toggle;

                toggle = String.valueOf(comboBoxDuration.getSelectedItem()).equals("ban")
                        || String.valueOf(comboBoxDuration.getSelectedItem()).equals(TranslationUtil.convertToHebrew("ban"));

                durationLabel.setVisible(toggle);
                textFieldDuration.setVisible(toggle);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (XPunisher.loggedUser.getAccessLevel().getAccessId() < AccessLevel.MANAGER.getAccessId()) {
                    // make punishment pending
                }
                // check if the player has a document, if he has then add punishment to document, if he doesn't create a new one
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
