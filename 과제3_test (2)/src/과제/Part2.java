package ����;

public class Part2 {
    private String menuName;
    private String[] sides;
    private int[] sidePrices;
    private int price;
    private int[] mainPrices;

    public Part2(String menuName, String[] sides, int[] sidePrices, int[] mainPrices) {
        this.menuName = menuName;
        this.sides = sides;
        this.sidePrices = sidePrices;
        this.price = mainPrices[0]; // ù ��° ���̵� �޴��� ������ ���� �޴� �������� ����
        this.mainPrices = mainPrices;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getPrice() {
        return price;
    }

    public String[] getSides() {
        return sides;
    }

    public int[] getSidePrices() {
        return sidePrices;
    }
    
    public int[] getMainPrices() {
        return mainPrices;
    }
    
    public String getMenuType() {
        if (menuName.contains("����") || menuName.contains("����") || menuName.contains("�") || menuName.contains("����") || menuName.contains("��ⱸ��") || menuName.contains("������")) {
            return "�ѽ�";
        } else if (menuName.contains("¥���") || menuName.contains("«��") || menuName.contains("������") || menuName.contains("������")) {
            return "�߽�";
        } else if (menuName.contains("�ʹ�") || menuName.contains("���") || menuName.contains("���") || menuName.contains("�쵿") || menuName.contains("�Ե�")) {
            return "�Ͻ�";
        } else {
            return "���";
        }
    }
}