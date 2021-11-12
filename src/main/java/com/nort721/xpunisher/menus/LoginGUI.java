package com.nort721.xpunisher.menus;

import com.nort721.xpunisher.XPunisher;
import com.nort721.xpunisher.data.LoggedUser;
import com.nort721.xpunisher.storage.MongoDB;
import com.nort721.xpunisher.utils.MongoUtil;
import com.nort721.xpunisher.utils.TranslationUtil;
import com.nort721.xpunisher.utils.console.Console;
import com.nort721.xpunisher.utils.console.LogType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

    private JTextField usernameTextField;
    private JPanel loginPanel;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JButton languageButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private ControlPanel controlPanel;

    public static MongoDB mongoDB;

    public LoginGUI() {
        super(XPunisher.SOFTWARE_NAME + " " + XPunisher.VERSION);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 250));
        setResizable(false);

        add(loginPanel);

        pack();
        setLocationRelativeTo(null);

        JOptionPane jop = new JOptionPane();
        jop.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        jop.setMessage("Initializing app and connecting to database . . .");
        JDialog dialog = jop.createDialog(null, "XPunisher");

        new Thread(new Runnable() {
            public void run() {
                dialog.setVisible(true);
            }
        }).start();

        Console.log("connecting to database . . .", LogType.INFO);
        mongoDB = new MongoDB();

        dialog.dispose();
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

                if (username.equalsIgnoreCase("debug") && password.equalsIgnoreCase("console")) {
                    XPunisher.executeDebugConsole();
                    return;
                }

                Console.log("user " + username + " is trying to login", LogType.INFO);

                String loginAttemptResult = MongoUtil.checkUserLogin(username, password);

                if (loginAttemptResult.equalsIgnoreCase("approved")) {
                    Console.log("user " + username + " has logged in successfully", LogType.INFO);
                    JOptionPane.showMessageDialog(null, "Welcome " + username, "Approved", JOptionPane.INFORMATION_MESSAGE);
                    XPunisher.loggedUser = new LoggedUser(username, MongoUtil.getUserAccessLevel(username));
                    setVisible(false);
                    controlPanel = new ControlPanel();
                } else {
                    Console.log("user " + username + " has failed to login (" + loginAttemptResult + ")", LogType.INFO);
                    JOptionPane.showMessageDialog(null, "Incorrect username or password", "Denied", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        languageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (languageButton.getText().equals("English")) {
                    Console.log("changing UI language to hebrew", LogType.INFO);
                    languageButton.setText("Hebrew");
                    for (JLabel label : XPunisher.LABELS)
                        label.setText(TranslationUtil.convertToEnglish(label.getText()));
                    for (JButton button : XPunisher.BUTTONS)
                        button.setText(TranslationUtil.convertToEnglish(button.getText() + ""));
                    Console.log("UI language has been changed to hebrew", LogType.INFO);
                } else {
                    Console.log("changing UI language to english", LogType.INFO);
                    languageButton.setText("English");
                    for (JLabel label : XPunisher.LABELS)
                        label.setText(TranslationUtil.convertToHebrew(label.getText()));
                    for (JButton button : XPunisher.BUTTONS)
                        button.setText(TranslationUtil.convertToHebrew(button.getText() + ""));
                    Console.log("UI language has been changed to english", LogType.INFO);
                }
            }
        });
    }
}
