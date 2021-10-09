package com.nort721.xpunisher;

import com.nort721.xpunisher.data.LoggedUser;
import com.nort721.xpunisher.menus.LoginGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class XPunisher {

    public static final String SOFTWARE_NAME = "XPunisher";
    public static final String VERSION = "Build 1 BETA";

    public static final ArrayList<JLabel> LABELS = new ArrayList<>();
    public static final ArrayList<JButton> BUTTONS = new ArrayList<>();

    public static LoggedUser loggedUser;

    public static void main(String[] args) {
        LoginGUI loginGUI = new LoginGUI();
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\XPunisher.png");
        loginGUI.setIconImage(icon);
    }
}
