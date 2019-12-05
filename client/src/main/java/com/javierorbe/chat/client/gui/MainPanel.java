package com.javierorbe.chat.client.gui;

import static java.awt.GridBagConstraints.REMAINDER;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class MainPanel extends JPanel {

    private final GridBagBuilder gbb = new GridBagBuilder();

    private final JTextArea chatArea = new JTextArea(10, 6);

    MainPanel(Consumer<String> msgCallback) {
        super(new GridBagLayout());

        chatArea.setEditable(false);
        JScrollPane chatAreaScroll = new JScrollPane(chatArea);
        JTextField msgField = new JTextField(30);
        JButton sendBtn = new JButton("Send");

        gbb.setFillAndAnchor(GridBagBuilder.Fill.HORIZONTAL, GridBagBuilder.Anchor.WEST)
                .setInsets(4, 4, 4, 4);

        gbb.setGridWidthAndWeightX(REMAINDER, 1);
        put(chatAreaScroll);
        gbb.setGridWidthAndWeightX(1, 1);
        put(msgField);
        gbb.setGridWidthAndWeightX(REMAINDER, 0);
        put(sendBtn);

        sendBtn.addActionListener(e -> msgCallback.accept(msgField.getText()));
    }

    private void put(Component component) {
        add(component, gbb.getConstraints());
    }

    void addChatLine(String text) {
        chatArea.append(text + "\n");
    }
}
