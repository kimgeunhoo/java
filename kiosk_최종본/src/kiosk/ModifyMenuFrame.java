package kiosk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifyMenuFrame extends JFrame {
    private JTextField menuNameField;
    private JTextField menuPriceField;

    public ModifyMenuFrame() {
        setTitle("메뉴 수정");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        JLabel nameLabel = new JLabel("메뉴 이름:");
        menuNameField = new JTextField();
        JLabel priceLabel = new JLabel("가격:");
        menuPriceField = new JTextField();
//      JButton searchButton = new JButton("검색");
        JButton modifyButton = new JButton("수정");

//      searchButton.addActionListener(new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {
//              searchMenu();
//          }
//      });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyMenuInDatabase();
            }
        });

        panel.add(nameLabel);
        panel.add(menuNameField);
        panel.add(priceLabel);
        panel.add(menuPriceField);
//      panel.add(searchButton);
        panel.add(modifyButton);

        add(panel);
    }

    private void searchMenu() {
        String menuName = menuNameField.getText();

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM menu WHERE food_name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, menuName);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int price = resultSet.getInt("price");
                    menuPriceField.setText(String.valueOf(price));
                } else {
                    JOptionPane.showMessageDialog(this, "해당 메뉴를 찾을 수 없습니다.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "메뉴 검색에 실패했습니다.");
        }
    }

    private void modifyMenuInDatabase() {
        String menuName = menuNameField.getText();
        String menuPriceStr = menuPriceField.getText();

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "UPDATE menu SET price=? WHERE food_name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, Integer.parseInt(menuPriceStr));
                preparedStatement.setString(2, menuName);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "메뉴가 수정되었습니다.");
                    
                    
                    // 현재 창 닫기
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "메뉴 수정에 실패했습니다.");
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "메뉴 수정에 실패했습니다.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ModifyMenuFrame modifyMenuFrame = new ModifyMenuFrame();
            modifyMenuFrame.setVisible(true);
        });
    }
}
