package com.nort721.xpunisher;

import com.nort721.xpunisher.data.LoggedUser;
import com.nort721.xpunisher.menus.LoginGUI;
import com.nort721.xpunisher.utils.console.Console;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class XPunisher {

    public static final String SOFTWARE_NAME = "XPunisher";
    public static final String VERSION = "Build 1 BETA";

    public static Console console;

    public static final ArrayList<JLabel> LABELS = new ArrayList<>();
    public static final ArrayList<JButton> BUTTONS = new ArrayList<>();

    public static LoggedUser loggedUser;

    public static void main(String[] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new WindowsLookAndFeel());
            } catch (Exception e) {
                System.out.println("Radiance Graphite failed to initialize");
            }
        });

        LoginGUI loginGUI = new LoginGUI();
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\XPunisher.png");
        loginGUI.setIconImage(icon);
    }

    public static void executeDebugConsole() {
        if (console != null) return;
        console = new Console();
        console.setTitle(SOFTWARE_NAME + " " + VERSION + " - Debug Console");
        console.printClean("\n");
        console.printClean("Runtime logging - " + SOFTWARE_NAME + " " + VERSION + "\n");
        console.printClean("...\n");
    }
}
