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
        setTitle("관리자 창");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        addMenuButton = new JButton("메뉴 추가");
        modifyMenuButton = new JButton("메뉴 수정");
        deleteMenuButton = new JButton("메뉴 삭제");
        exitButton = new JButton("나가기");

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
                dispose(); // 창 닫기
            }
        });
    }
}
