package kiosk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteMenuFrame extends JFrame {
    private JTextField menuNameField;

    public DeleteMenuFrame() {
        setTitle("메뉴 삭제");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel nameLabel = new JLabel("메뉴 이름:");
        menuNameField = new JTextField();
        //JButton searchButton = new JButton("검색");
        JButton deleteButton = new JButton("삭제");

//      searchButton.addActionListener(new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {
//              searchMenu();
//          }
//      });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMenuFromDatabase();
            }
        });

        panel.add(nameLabel);
        panel.add(menuNameField);
//      panel.add(searchButton);
        panel.add(deleteButton);

        add(panel);
    }

    private void searchMenu() {
       
    }

    private void deleteMenuFromDatabase() {
        String menuName = menuNameField.getText();

        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "DELETE FROM menu WHERE food_name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, menuName);

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(this, "메뉴가 삭제되었습니다.");
                } else {
                    JOptionPane.showMessageDialog(this, "메뉴 삭제에 실패했습니다.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "메뉴 삭제에 실패했습니다.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeleteMenuFrame deleteMenuFrame = new DeleteMenuFrame();
            deleteMenuFrame.setVisible(true);
        });
    }
}