package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.XPunisher;
import com.nort721.xpunisher.data.enums.Language;
import com.nort721.xpunisher.utils.TranslationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPunishmentMenu extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBoxDuration;
    private JTextField textFieldDuration;
    private JLabel durationLabel;
    private JButton cancelButton;
    private JLabel usernameLabel;
    private JLabel steamIdLabel;
    private JLabel reasonLabel;
    private JLabel dateLabel;
    private JLabel punishmentLabel;

    public AddPunishmentMenu() {
        super("XPunisher - Add Punishment");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(370, 440));
        setResizable(false);

        XPunisher.LABELS.add(usernameLabel);
        XPunisher.LABELS.add(steamIdLabel);
        XPunisher.LABELS.add(reasonLabel);
        XPunisher.LABELS.add(dateLabel);
        XPunisher.LABELS.add(punishmentLabel);
        XPunisher.LABELS.add(durationLabel);
        XPunisher.BUTTONS.add(addButton);
        XPunisher.BUTTONS.add(cancelButton);

        String[] items = {"warning", "ban"};

        if (TranslationUtil.currentLanguage == Language.HEBREW) {
            for (JLabel label : XPunisher.LABELS)
                label.setText(TranslationUtil.convertToHebrew(label.getText()));
            for (JButton button : XPunisher.BUTTONS)
                button.setText(TranslationUtil.convertToHebrew(button.getText() + ""));
            for (int i = 0; i < items.length; i++)
                items[i] = TranslationUtil.convertToHebrew(items[i]);
        }

        durationLabel.setVisible(false);
        textFieldDuration.setVisible(false);

        for (String str : items)
            comboBoxDuration.addItem(str);

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
