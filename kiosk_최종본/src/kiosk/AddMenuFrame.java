package kiosk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddMenuFrame extends JFrame {
    private JTextField menuNameField;
    private JTextField menuPriceField;

    public AddMenuFrame() {
        setTitle("메뉴 추가");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        JLabel nameLabel = new JLabel("메뉴 이름:");
        menuNameField = new JTextField();
        JLabel priceLabel = new JLabel("가격:");
        menuPriceField = new JTextField();
        JButton addButton = new JButton("추가");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMenuToDatabase();
            }
        });

        panel.add(nameLabel);
        panel.add(menuNameField);
        panel.add(priceLabel);
        panel.add(menuPriceField);
        panel.add(addButton);

        add(panel);
    }

    private void addMenuToDatabase() {
        String menuName = menuNameField.getText();
        String menuPriceStr = menuPriceField.getText();

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO menu (food_name, price) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, menuName);
                preparedStatement.setInt(2, Integer.parseInt(menuPriceStr));
                preparedStatement.executeUpdate();
            }

            JOptionPane.showMessageDialog(this, "메뉴가 추가되었습니다.");

        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "메뉴 추가에 실패했습니다.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddMenuFrame addMenuFrame = new AddMenuFrame();
            addMenuFrame.setVisible(true);
        });
    }
}