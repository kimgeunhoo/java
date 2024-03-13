package kiosk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KioskGUI extends JFrame {
    private JTextArea cartTextArea;
    private JLabel cartTotalLabel;
    private JPanel foodPanel;
    private int currentTotal;

    public KioskGUI() {
        setTitle("Ű����ũ");
        setSize(760, 410);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(1, 2)); 

        cartTextArea = new JTextArea(50, 15);
        cartTextArea.setEditable(false);
        add(new JScrollPane(cartTextArea));
        
        
        foodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(foodPanel);        
        loadMenuFromDatabase();         
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton initializeMenuButton = new JButton("���� �޴� �ʱ�ȭ");
        
        cartTotalLabel = new JLabel("�� ����: 0��");
        buttonPanel.add(cartTotalLabel);
        
        JButton purchaseButton = new JButton("����");
        JButton clearButton = new JButton("��ٱ��� ����");
        JButton adminButton = new JButton("���� ����");
        buttonPanel.add(initializeMenuButton);
        add(buttonPanel); 
        
        
        
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (cartTextArea.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(KioskGUI.this, 
                    		"������ �������ּ���.", "�˸�", JOptionPane.WARNING_MESSAGE);
                } else {
                    openPaymentFrame();
                }
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartTextArea.setText("");
                clearCart();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginFrame();
            }
        });
        initializeMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	clearMenuButtons();
                loadMenuFromDatabase();
            }
        });

        buttonPanel.add(purchaseButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(adminButton);
    }
    
    private void openPaymentFrame() {
        String cartContent = cartTextArea.getText();
        PaymentFrame paymentFrame = new PaymentFrame(this, cartContent);
        paymentFrame.setVisible(true);
    }

    private void showLoginFrame() {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
    protected void clearMenuButtons() {
        // ������ �ִ� ��ư���� ����
        foodPanel.removeAll();
        validate();
        repaint();
    }
    protected void clearCart() {
    	cartTextArea.setText("");
    	updateTotalPrice(-currentTotal);
    }

    public void handlePaymentComplete() {
        clearCart();
        updateTotalPrice(0);
    }
    

	private void loadMenuFromDatabase() {
        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM menu")) {

            List<JButton> menuButtons = new ArrayList<>();

            while (resultSet.next()) {
                String foodName = resultSet.getString("food_name");
                int price = resultSet.getInt("price");

                JButton foodButton = new JButton(foodName + " (" + price + "��)");
                
                foodButton.setPreferredSize(new Dimension(150, 30));
                
                foodButton.addActionListener(new FoodButtonListener(foodName, price));
                menuButtons.add(foodButton);
            }

            for (JButton button : menuButtons) {
                foodPanel.add(button);
            }

            validate();
            repaint();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "�޴��� �ҷ����� �� �����߽��ϴ�.");
        }
    }
    

    private class FoodButtonListener implements ActionListener {
        private String foodName;
        private int price;

        public FoodButtonListener(String foodName, int price) {
            this.foodName = foodName;
            this.price = price;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            cartTextArea.append(foodName + " - " + price + "��\n");
            
            
            updateTotalPrice(price);
        }
    }
    
    protected void updateTotalPrice(int addedPrice) {
        String currentTotalText = cartTotalLabel.getText();
        String currentTotalValue = currentTotalText.substring(6, 
        		currentTotalText.length() - 1);
        currentTotal = Integer.parseInt(currentTotalValue);        
        currentTotal += addedPrice;       
        cartTotalLabel.setText("�� ����: " + currentTotal + "��");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KioskGUI kioskGUI = new KioskGUI();
            kioskGUI.setVisible(true);
        });
    }

	
} 