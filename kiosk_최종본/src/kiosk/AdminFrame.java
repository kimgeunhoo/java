package kiosk;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    private JButton addMenuButton;
    private JButton modifyMenuButton;
    private JButton deleteMenuButton;
    private JButton exitButton;

    public AdminFrame() {
        setTitle("������ â");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        addMenuButton = new JButton("�޴� �߰�");
        modifyMenuButton = new JButton("�޴� ����");
        deleteMenuButton = new JButton("�޴� ����");
        exitButton = new JButton("������");

        add(addMenuButton);
        add(modifyMenuButton);
        add(deleteMenuButton);
        add(exitButton);

        addMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddMenuFrame addMenuFrame = new AddMenuFrame();
                addMenuFrame.setVisible(true);
            }
        });

        modifyMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyMenuFrame modifyMenuFrame = new ModifyMenuFrame();
                modifyMenuFrame.setVisible(true);
            }
        });

        deleteMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteMenuFrame deleteMenuFrame = new DeleteMenuFrame();
                deleteMenuFrame.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // â �ݱ�
            }
        });
    }
}
