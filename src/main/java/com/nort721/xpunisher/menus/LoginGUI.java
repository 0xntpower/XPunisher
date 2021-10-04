package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.XPunisher;
import com.nort721.xpunisher.data.LoggedUser;
import com.nort721.xpunisher.data.enums.AccessLevel;
import com.nort721.xpunisher.utils.MongoUtil;
import com.nort721.xpunisher.utils.TranslationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

    private JTextField usernameTextField;
    private JPanel panel1;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JButton languageButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JFrame frame;
    private ControlPanel controlPanel;

    public LoginGUI() {
        super(XPunisher.SOFTWARE_NAME + " " + XPunisher.VERSION);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 250));
        setResizable(false);

        // now add the panel
        add(panel1);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        XPunisher.LABELS.add(usernameLabel);
        XPunisher.LABELS.add(passwordLabel);
        XPunisher.BUTTONS.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameTextField.getText();
                String password = passwordTextField.getText();

                // check for incorrect syntax
                if (username.length() < 3 || password.length() < 3) {
                    JOptionPane.showMessageDialog(null, "Incorrect args size", "Incorrect syntax", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (MongoUtil.isUserCorrect(username, password) != AccessLevel.NONE) {
                    JOptionPane.showMessageDialog(null, "Welcome " + username, "Approved", JOptionPane.INFORMATION_MESSAGE);
                    XPunisher.loggedUser = new LoggedUser(username, AccessLevel.ADMIN);
                    setVisible(false);
                    controlPanel = new ControlPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password " + username, "Denied", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        languageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (languageButton.getText().equals("English")) {
                    languageButton.setText("Hebrew");
                    for (JLabel label : XPunisher.LABELS)
                        label.setText(TranslationUtil.convertToEnglish(label.getText()));
                    for (JButton button : XPunisher.BUTTONS)
                        button.setText(TranslationUtil.convertToEnglish(button.getText() + ""));
                } else {
                    languageButton.setText("English");
                    for (JLabel label : XPunisher.LABELS)
                        label.setText(TranslationUtil.convertToHebrew(label.getText()));
                    for (JButton button : XPunisher.BUTTONS)
                        button.setText(TranslationUtil.convertToHebrew(button.getText() + ""));
                }
            }
        });
    }
}
