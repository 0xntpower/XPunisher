package com.nort721.xpunisher.menus;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.nort721.xpunisher.XPunisher;
import com.nort721.xpunisher.data.LoggedUser;
import com.nort721.xpunisher.data.enums.Language;
import com.nort721.xpunisher.storage.MongoDB;
import com.nort721.xpunisher.utils.MongoUtil;
import com.nort721.xpunisher.utils.TranslationUtil;
import com.nort721.xpunisher.utils.console.Console;
import com.nort721.xpunisher.utils.console.LogType;
import com.nort721.xpunisher.utils.jnic;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

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

                checkLoginCredentials(username, password);
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

    @jnic
    private void checkLoginCredentials(String username, String password) {
        String loginAttemptResult = MongoUtil.checkUserLogin(username, password);

        if (loginAttemptResult.equalsIgnoreCase("approved")) {
            Console.log("user " + username + " has logged in successfully", LogType.INFO);
            String msg = TranslationUtil.currentLanguage == Language.HEBREW ? TranslationUtil.convertToHebrew("Welcome") : "Welcome";
            JOptionPane.showMessageDialog(null, msg + " " + username, "Approved", JOptionPane.INFORMATION_MESSAGE);
            XPunisher.loggedUser = new LoggedUser(username, MongoUtil.getUserAccessLevel(username));
            setVisible(false);
            controlPanel = new ControlPanel();
        } else {
            Console.log("user " + username + " has failed to login (" + loginAttemptResult + ")", LogType.INFO);
            String msg = TranslationUtil.currentLanguage == Language.HEBREW ? TranslationUtil.convertToHebrew("Incorrect username or password") : "Incorrect username or password";
            JOptionPane.showMessageDialog(null, msg, "Denied", JOptionPane.INFORMATION_MESSAGE);
        }
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
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayoutManager(20, 3, new Insets(0, 0, 0, 0), -1, -1));
        usernameTextField = new JTextField();
        loginPanel.add(usernameTextField, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordTextField = new JPasswordField();
        loginPanel.add(passwordTextField, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        loginPanel.add(panel1, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        loginButton = new JButton();
        loginButton.setText("login");
        panel1.add(loginButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        loginPanel.add(spacer3, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        loginPanel.add(spacer4, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        loginPanel.add(spacer5, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        loginPanel.add(spacer6, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        loginPanel.add(spacer7, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        passwordLabel = new JLabel();
        passwordLabel.setText("password:");
        loginPanel.add(passwordLabel, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usernameLabel = new JLabel();
        usernameLabel.setText("username:");
        loginPanel.add(usernameLabel, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        loginPanel.add(spacer8, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        loginPanel.add(spacer9, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Courier", Font.BOLD, 20, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("XPunisher");
        loginPanel.add(label1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        loginPanel.add(spacer10, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer11 = new Spacer();
        loginPanel.add(spacer11, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer12 = new Spacer();
        loginPanel.add(spacer12, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer13 = new Spacer();
        loginPanel.add(spacer13, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer14 = new Spacer();
        loginPanel.add(spacer14, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        loginPanel.add(panel2, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        languageButton = new JButton();
        languageButton.setText("Hebrew");
        panel2.add(languageButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer15 = new Spacer();
        panel2.add(spacer15, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer16 = new Spacer();
        panel2.add(spacer16, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer17 = new Spacer();
        loginPanel.add(spacer17, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer18 = new Spacer();
        loginPanel.add(spacer18, new GridConstraints(18, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer19 = new Spacer();
        loginPanel.add(spacer19, new GridConstraints(19, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return loginPanel;
    }
}
