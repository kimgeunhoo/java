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
        setTitle("�޴� �߰�");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        JLabel nameLabel = new JLabel("�޴� �̸�:");
        menuNameField = new JTextField();
        JLabel priceLabel = new JLabel("����:");
        menuPriceField = new JTextField();
        JButton addButton = new JButton("�߰�");

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

            JOptionPane.showMessageDialog(this, "�޴��� �߰��Ǿ����ϴ�.");

        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "�޴� �߰��� �����߽��ϴ�.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddMenuFrame addMenuFrame = new AddMenuFrame();
            addMenuFrame.setVisible(true);
        });
    }
}