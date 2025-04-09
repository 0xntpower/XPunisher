package com.nort721.xpunisher.menus;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
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

                PunishType type = PunishType.getPunishTypeFromString(comboBoxPunishment.getSelectedItem() + "");

                Punishment punishment;

                if (type == PunishType.BAN && textFieldDuration.getText().length() > 0) {
                    punishment = new Punishment(type, dateTextField.getText(),
                            reasonComboBox.getSelectedItem() + "", textFieldDuration.getText());
                } else {
                    punishment = new Punishment(type, dateTextField.getText(),
                            reasonComboBox.getSelectedItem() + "");
                }

                if (XPunisher.loggedUser.getAccessLevel().getAccessId() < AccessLevel.MANAGER.getAccessId()) {
                    punishment.setPending(true);
                }

                MongoUtil.savePunishment(playerData, punishment);

                String msg = "Punishment added to database successfully";

                if (punishment.isPending())
                    msg += " and is now pending for approval from higher management";

                JOptionPane.showMessageDialog(null, msg, "Status", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(28, 3, new Insets(0, 0, 0, 0), -1, -1));
        steamIdTextField = new JTextField();
        panel1.add(steamIdTextField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        playerNameTextField = new JTextField();
        panel1.add(playerNameTextField, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        steamIdLabel = new JLabel();
        steamIdLabel.setText("steamId:");
        panel1.add(steamIdLabel, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        playerNameLabel = new JLabel();
        playerNameLabel.setText("playerName:");
        panel1.add(playerNameLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel1.add(spacer5, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel1.add(spacer6, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        reasonLabel = new JLabel();
        reasonLabel.setText("reason:");
        panel1.add(reasonLabel, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panel1.add(spacer7, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setText("Add");
        panel1.add(addButton, new GridConstraints(25, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        panel1.add(spacer8, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        panel1.add(spacer9, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        panel1.add(spacer10, new GridConstraints(27, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        dateLabel = new JLabel();
        dateLabel.setText("date:");
        panel1.add(dateLabel, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        panel1.add(spacer11, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        panel1.add(spacer12, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        comboBoxPunishment = new JComboBox();
        panel1.add(comboBoxPunishment, new GridConstraints(19, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        panel1.add(spacer13, new GridConstraints(24, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        punishmentLabel = new JLabel();
        punishmentLabel.setText("punishment:");
        panel1.add(punishmentLabel, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        panel1.add(spacer14, new GridConstraints(18, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        durationLabel = new JLabel();
        durationLabel.setText("duration:");
        durationLabel.setVisible(true);
        panel1.add(durationLabel, new GridConstraints(21, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer15 = new Spacer();
        panel1.add(spacer15, new GridConstraints(20, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textFieldDuration = new JTextField();
        panel1.add(textFieldDuration, new GridConstraints(23, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer16 = new Spacer();
        panel1.add(spacer16, new GridConstraints(22, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel1.add(cancelButton, new GridConstraints(26, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        reasonComboBox = new JComboBox();
        panel1.add(reasonComboBox, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dateTextField = new JTextField();
        panel1.add(dateTextField, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
