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
        
        totalAmountLabel = new JLabel("�� �ݾ�: " + calculateTotalAmount() + "��");

        JButton cashButton = new JButton("���� ����");
        JButton cardButton = new JButton("ī�� ����");
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
        totalAmountLabel.setText("�� �ݾ�: " + calculateTotalAmount() + "��");
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

            // �� �ݾ� ���̺� ����
            updateTotalAmountLabel();
        }
    }

    private void handleCashPayment() {
        String input = JOptionPane.showInputDialog("���� �ݾ��� �Է��ϼ���:");
        try {
            int paymentAmount = Integer.parseInt(input);
            int totalAmount = calculateTotalAmount();

            if (paymentAmount >= totalAmount) {
                int change = paymentAmount - totalAmount;
                JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.\n�Ž�����: " + change + "��", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
                kioskGui.clearCart();
                kioskGui.updateTotalPrice(0);
                updateTotalAmountLabel();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "�ܾ��� �����մϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "��ȿ���� ���� �ݾ��Դϴ�.", "����", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleCardPayment() {
        String input = JOptionPane.showInputDialog("���� �ݾ��� �Է��ϼ���:");
        try {
            int paymentAmount = Integer.parseInt(input);
            int totalAmount = calculateTotalAmount();

            if (paymentAmount >= totalAmount) {
                int remainingBalance = paymentAmount - totalAmount;
                JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.\n���� �ܾ�: " + remainingBalance + "��", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
                kioskGui.clearCart();
                kioskGui.updateTotalPrice(0);
                updateTotalAmountLabel();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "�ܾ��� �����մϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "��ȿ���� ���� �ݾ��Դϴ�.", "����", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int calculateTotalAmount() {
    	String[] cartItems = cartTextArea.getText().split("\n");
        int totalAmount = 0;

        for (String cartItem : cartItems) {
            String[] itemParts = cartItem.split(" - ");

            if (itemParts.length == 2) {
                totalAmount += Integer.parseInt(itemParts[1].replace("��", ""));
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

				new PaymentFrame(kioskGui, "��ٱ��� ���� ����");
				
            }
        });
    }
}