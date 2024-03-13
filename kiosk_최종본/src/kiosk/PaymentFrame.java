package kiosk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentFrame extends JFrame {
    private JTextArea cartTextArea;
    private JLabel totalAmountLabel;
    private static KioskGUI kioskGui;

    public PaymentFrame(KioskGUI kioskGui, String cartContent) {
        this.cartTextArea = new JTextArea(cartContent);
        this.kioskGui = kioskGui;
        cartTextArea.setEditable(false);
        
        totalAmountLabel = new JLabel("총 금액: " + calculateTotalAmount() + "원");

        JButton cashButton = new JButton("현금 결제");
        JButton cardButton = new JButton("카드 결제");
        cashButton.setPreferredSize(new Dimension(150, 120));
        cardButton.setPreferredSize(new Dimension(150, 120));
        
        cashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCashPayment();
            }
        });

        cardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCardPayment();
            }
        });

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        panel.setLayout(new GridLayout(3, 1));
        panel.add(totalAmountLabel);
        panel.add(cartTextArea);
        panel.add(cashButton);
        panel.add(cardButton);
        

        this.add(panel);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    


	private void updateTotalAmountLabel() {
        totalAmountLabel.setText("총 금액: " + calculateTotalAmount() + "원");
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
            cartTextArea.append(foodName + " - " + price + "원\n");

            // 총 금액 레이블 갱신
            updateTotalAmountLabel();
        }
    }

    private void handleCashPayment() {
        String input = JOptionPane.showInputDialog("투입 금액을 입력하세요:");
        try {
            int paymentAmount = Integer.parseInt(input);
            int totalAmount = calculateTotalAmount();

            if (paymentAmount >= totalAmount) {
                int change = paymentAmount - totalAmount;
                JOptionPane.showMessageDialog(this, "결제가 완료되었습니다.\n거스름돈: " + change + "원", "결제 완료", JOptionPane.INFORMATION_MESSAGE);
                kioskGui.clearCart();
                kioskGui.updateTotalPrice(0);
                updateTotalAmountLabel();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "잔액이 부족합니다.", "결제 실패", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "유효하지 않은 금액입니다.", "에러", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleCardPayment() {
        String input = JOptionPane.showInputDialog("투입 금액을 입력하세요:");
        try {
            int paymentAmount = Integer.parseInt(input);
            int totalAmount = calculateTotalAmount();

            if (paymentAmount >= totalAmount) {
                int remainingBalance = paymentAmount - totalAmount;
                JOptionPane.showMessageDialog(this, "결제가 완료되었습니다.\n남은 잔액: " + remainingBalance + "원", "결제 완료", JOptionPane.INFORMATION_MESSAGE);
                kioskGui.clearCart();
                kioskGui.updateTotalPrice(0);
                updateTotalAmountLabel();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "잔액이 부족합니다.", "결제 실패", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "유효하지 않은 금액입니다.", "에러", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int calculateTotalAmount() {
    	String[] cartItems = cartTextArea.getText().split("\n");
        int totalAmount = 0;

        for (String cartItem : cartItems) {
            String[] itemParts = cartItem.split(" - ");

            if (itemParts.length == 2) {
                totalAmount += Integer.parseInt(itemParts[1].replace("원", ""));
            }
        }

        return totalAmount;
    }

    private void clearCart() {
        cartTextArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

				new PaymentFrame(kioskGui, "장바구니 내용 예시");
				
            }
        });
    }
}