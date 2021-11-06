package com.nort721.xpunisher.utils.console;

import com.nort721.xpunisher.XPunisher;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

public class Console extends JFrame {

    private final JTextArea textArea;
    private static final String VERSION = "B1";
    private static final String PREFIX = " output -> ";

    public Console() {
        textArea = new JTextArea(24, 80);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.LIGHT_GRAY);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(String.valueOf((char) b));
            }
        }));
        add(textArea);
        init();
    }

    private void init() {
        pack();
        setVisible(true);
    }

    public void printClean(String output) {
        textArea.append(" " + output);
    }

    public void print(String output) {
        printClean(PREFIX + output);
    }

    public void logMsg(String msg, LogType logType) {
        // ToDo change message color according to type, change prefix and more...
        LocalTime localTime = LocalTime.now();
        String time = localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond() + " -> ";
        printClean(time + logType.name() + " -> " + msg);
    }

    public static void log(String msg, LogType logType) {
        if (XPunisher.console != null)
            XPunisher.console.logMsg(msg + "\n", logType);
    }
}
