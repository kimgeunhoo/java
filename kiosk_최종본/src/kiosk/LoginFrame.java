package kiosk;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField idTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("로그인");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel idLabel = new JLabel("ID:");
        JLabel passwordLabel = new JLabel("Password:");

        idTextField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("로그인");

        add(idLabel);
        add(idTextField);
        add(passwordLabel);
        add(passwordField);
        add(new JPanel());
        add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    private void login() {
        String id = idTextField.getText();
        String password = String.valueOf(passwordField.getPassword());

        if ("admin".equals(id) && "1111".equals(password)) {
            showAdminFrame(); 
        } else {
            JOptionPane.showMessageDialog(this, "ID 또는 비밀번호가 잘못되었습니다.");
        }
    }

    private void showAdminFrame() {
        SwingUtilities.invokeLater(() -> {
            AdminFrame adminFrame = new AdminFrame();
            adminFrame.setVisible(true);
            dispose(); // 로그인 창 닫기
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}