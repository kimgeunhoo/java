package ����;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    private Part2[] menuList;
    private Part2[] mainList;
        public Part1() {
            menuList = new Part2[]{
                new Part2("����", new String[]{"�߰����", "���ӱ�ġ", "�����", "��ä��", "�̸�"}, new int[]{9000, 3000, 1500, 3000, 5000}, new int[]{30000}),
                new Part2("����", new String[]{"�߰����", "���ӱ�ġ", "�����", "��ä��", "�̸�"}, new int[]{9000, 3000, 1500, 3000, 5000}, new int[]{30000}),
                new Part2("¥���", new String[]{"������", "������1�κ�", "��ǳ��1�κ�", "�ܹ���", "������"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[]{7000}),
                new Part2("«��", new String[]{"������", "������1�κ�", "��ǳ��1�κ�", "�ܹ���", "������"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[]{8000}),
                new Part2("������", new String[]{"������", "������1�κ�", "��ǳ��1�κ�", "�ܹ���", "������"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[]{8000}),
                new Part2("������", new String[]{"����߰�", "�ܹ���", "������", "���߰�", "�����߰�", "�����߰�"}, new int[]{3000, 500, 5000, 2000, 1000, 3000}, new int[]{15000}),
                new Part2("�", new String[]{"�����߰�", "������", "��ġ"}, new int[]{2000, 1000, 500}, new int[]{8000}),
                new Part2("����", new String[]{"�����߰�", "������", "��ġ"}, new int[]{2000, 1000, 500}, new int[]{8000}),
                new Part2("��ⱸ��", new String[]{"��ä��", "�屹", "�����", "�߰����", "��ġ"}, new int[]{3000, 1500, 1500, 9000, 500}, new int[]{18000}),
                new Part2("������", new String[]{"Ƣ��", "���߰�", "�����߰�", "ġ��", "���縮"}, new int[]{3000, 1000, 1000, 2000, 2000}, new int[]{9000}),
                new Part2("�ʹ�", new String[]{"�ͻ��", "����ҽ�", "�ʹ��߰�", "����Ƣ��", "���ڰ����", "����Ƣ��"}, new int[]{300, 300, 3000, 3500, 2500, 3500}, new int[]{16000}),
                new Part2("���", new String[]{"�ҽ�", "�ſ�ҽ�", "�屹", "�����", "�߰����", "������"}, new int[]{500, 500, 1500, 1500, 6000, 3000}, new int[]{8000}),
                new Part2("���", new String[]{"���߰�", "�����߰�", "�����߰�", "�����"}, new int[]{1500, 1000, 3000, 1500}, new int[]{8000}),
                new Part2("�쵿", new String[]{"���߰�", "�����߰�", "�����߰�", "�����"}, new int[]{1500, 1000, 3000, 1500}, new int[]{7500}),
                new Part2("�Ե�", new String[]{"�߰����", "�븥��", "�����", "�屹"}, new int[]{3000, 1500, 1500, 1500}, new int[]{8500}),
                new Part2("ġŲ", new String[]{"ġ�", "����Ƣ��", "���ҽ�", "�Ҷ��Ҷ�"}, new int[]{3000, 2500, 500, 3000}, new int[]{20000}),
                new Part2("����", new String[]{"���İ�Ƽ", "����"}, new int[]{7000, 6500}, new int[]{25000}),
                new Part2("�ܹ���", new String[]{"����Ƣ��", "ġŲ�ʰ�"}, new int[]{2500, 4000}, new int[]{5500})
            };
            
        }
    
    

        public void printMenu() {
        	Scanner scanner = new Scanner(System.in);
            int totalPrice = 0;
            List<Part2> cart = new ArrayList<>();
        	while (true) {
	            
	            System.out.println("���� ������ �����ϼ���:");
	            System.out.println("1. �ѽ�");
	            System.out.println("2. �߽�");
	            System.out.println("3. �Ͻ�");
	            System.out.println("4. �н�ƮǪ��");
	            System.out.println("5. ��ٱ���");
	            System.out.print("����: ");
	            int cuisineChoice = scanner.nextInt();
	            scanner.nextLine();
	
	            Part2[] cuisineMenuList;
	            String cuisineName;
	            Part2 selectedMenu = null;
	            Part2 selectedMain = null;
	
	            switch (cuisineChoice) {
	                case 1:
	                    cuisineMenuList = getKoreanMenuList();
	                    cuisineName = "�ѽ�";
	                    break;
	                case 2:
	                    cuisineMenuList = getChineseMenuList();
	                    cuisineName = "�߽�";
	                    break;
	                case 3:
	                    cuisineMenuList = getJapaneseMenuList();
	                    cuisineName = "�Ͻ�";
	                    break;
	                case 4:
	                    cuisineMenuList = getWesternMenuList();
	                    cuisineName = "�н�ƮǪ��";
	                    break;
	                case 5:
	                	if (totalPrice != 0) {
	                        System.out.println("���� �ֹ������Դϴ�.");
	                        for (Part2 item : cart) {
	                            System.out.println(item.getMenuName());
	                        }
	                        System.out.println("1. �����ϱ�");
	                        System.out.println("2. �޴��� ���ư���");
	                        System.out.print("���� : ");
	                        int choice = scanner.nextInt();  
	                        switch (choice) {
	                        	case 1:
	                        		System.out.print("��۹��� �ּҸ� �Է��ϼ���: ");
	    	                        String address = scanner.next();
	    	                        System.out.println("���� ������ ������ �� ������ " + totalPrice + "�� �Դϴ�.");
	    	                        System.out.print("�ݾ��� �Է��ϼ���: ");
	    	                        int money = scanner.nextInt();
	    	                        scanner.nextLine();
	    	                        if (money >= totalPrice) {
	    	                            System.out.println("�ֹ��� �Ϸ�Ǿ����ϴ�.");
	    	                            System.out.println("�Ž����� : " + (money - totalPrice));
	    	                            System.out.println("��۹��� �ּ� : " + address);
	    	                            return;
	    	                        } else {
	    	                            System.out.println("�ݾ��� �����մϴ�. �޴��� ���ư��ϴ�.");
	    	                            continue;
	    	                        }
	                        	case 2:
	                        		System.out.println("�޴��� ���ư��ϴ�.");
	                        		continue;
	                        }	                        
	                    } else {
	                        System.out.println("��ٱ��ϰ� ������ϴ�.");
	                        continue;
	                    }
	                default:
	                    System.out.println("�߸��� �����Դϴ�.");
	                    return;
	            }
	
	            System.out.println(cuisineName + " �޴� ���:");
	            for (int i = 0; i < cuisineMenuList.length; i++) {
	                System.out.println((i + 1) + ". " + cuisineMenuList[i].getMenuName());
	            }
	
	            System.out.print("�޴��� �����ϼ���: ");
	            int menuIndex = scanner.nextInt();
	            scanner.nextLine(); // ���� ���� ����
	
	            if (menuIndex >= 1 && menuIndex <= cuisineMenuList.length) {
	            	
	                selectedMenu = cuisineMenuList[menuIndex - 1];
	                selectedMain = cuisineMenuList[menuIndex - 1];
	                System.out.println("���� �޴�: " + selectedMenu.getMenuName());
	                System.out.println("Ÿ��: " + cuisineName);
	                System.out.println("����: " + Arrays.toString(selectedMain.getMainPrices()) + "��");
	                
	                
	
	                String[] sides = selectedMenu.getSides();
	                int[] sidePrices = selectedMenu.getSidePrices();
	                int[] MainPrices = selectedMenu.getMainPrices();
	                
	                int sideIndex = 0;
	                totalPrice += selectedMenu.getPrice();
	                while (true) {
	                    System.out.println("���̵� �޴� ���:");
	                    for (int i = 0; i < sides.length; i++) {
	                        System.out.println((i + 1) + ". " + sides[i]);
	                        System.out.println("   ����: " + sidePrices[i] + "��");
	                    }
	                    System.out.print("���̵� �޴��� �����ϼ��� (0: �������� ����): ");
	                    sideIndex = scanner.nextInt();
	                    scanner.nextLine();

	                    if (sideIndex == 0) {
	                        System.out.println("���̵� �޴��� �������� �ʾҽ��ϴ�.");
	                        break;
	                    } else if (sideIndex < 1 || sideIndex > sides.length) {
	                        System.out.println("�߸��� �޴� ��ȣ�Դϴ�.");
	                        continue; // �ݺ����� ó������ ���ư�
	                    }

	                    String selectedSide = sides[sideIndex - 1];
	                    int selectedSidePrice = sidePrices[sideIndex - 1];

	                    System.out.println("������ ���̵� �޴�: " + selectedSide);
	                    System.out.println("����: " + selectedSidePrice + "��");

	                    totalPrice += selectedSidePrice;
	                    System.out.println("�� ����: " + totalPrice + "��");
	                   

	                }
	            } else {
	                System.out.println("�߸��� �޴� ��ȣ�Դϴ�.");
	            }
	            
	            if (selectedMenu != null) {
	                cart.add(selectedMenu);
	                System.out.println("��ٱ��Ͽ� " + selectedMenu.getMenuName() + "��(��) �߰��Ǿ����ϴ�.");
	            }	         
           
	        }
        }


        private Part2[] getKoreanMenuList() {
            return new Part2[]{
                    new Part2("����", new String[]{"�߰����", "���ӱ�ġ", "�����", "��ä��", "�̸�"}, new int[]{9000, 3000, 1500, 3000, 5000}, new int[] {30000}),
                    new Part2("����", new String[]{"�߰����", "���ӱ�ġ", "�����", "��ä��", "�̸�"}, new int[]{9000, 3000, 1500, 3000, 5000}, new int[] {30000}),
                    new Part2("�", new String[]{"�����߰�", "������", "��ġ"}, new int[]{2000, 1000, 500}, new int[] {8000}),
                    new Part2("����", new String[]{"�����߰�", "������", "��ġ"}, new int[]{2000, 1000, 500}, new int[] {8000}),
                    new Part2("��ⱸ��", new String[]{"��ä��", "�屹", "�����", "�߰����", "��ġ"}, new int[]{3000, 1500, 1500, 9000, 500}, new int[] {18000}),
                    new Part2("������", new String[]{"Ƣ��", "���߰�", "�����߰�", "ġ��", "���縮"}, new int[]{3000, 1000, 1000, 2000, 2000}, new int[] {9000})
            };
        }

        private Part2[] getChineseMenuList() {
            return new Part2[]{
                    new Part2("¥���", new String[]{"������", "������1�κ�", "��ǳ��1�κ�", "�ܹ���", "������"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[] {7000}),
                    new Part2("«��", new String[]{"������", "������1�κ�", "��ǳ��1�κ�", "�ܹ���", "������"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[] {8000}),
                    new Part2("������", new String[]{"������", "������1�κ�", "��ǳ��1�κ�", "�ܹ���", "������"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[] {8000}),
                    new Part2("������", new String[]{"����߰�", "�ܹ���", "������", "���߰�", "�����߰�", "�����߰�"}, new int[]{3000, 500, 5000, 2000, 1000, 3000}, new int[] {15000})
            };
        }

        private Part2[] getJapaneseMenuList() {
            return new Part2[]{
                    new Part2("�ʹ�", new String[]{"�ͻ��", "����ҽ�", "�ʹ��߰�", "����Ƣ��", "���ڰ����", "����Ƣ��"}, new int[]{300, 300, 3000, 3500, 2500, 3500}, new int[] {16000}),
                    new Part2("���", new String[]{"�ҽ�", "�ſ�ҽ�", "�屹", "�����", "�߰����", "������"}, new int[]{500, 500, 1500, 1500, 6000, 3000}, new int[] {8000}),
                    new Part2("���", new String[]{"���߰�", "�����߰�", "�����߰�", "�����"}, new int[]{1500, 1000, 3000, 1500}, new int[] {8000}),
                    new Part2("�쵿", new String[]{"���߰�", "�����߰�", "�����߰�", "�����"}, new int[]{1500, 1000, 3000, 1500}, new int[] {7500}),
                    new Part2("�Ե�", new String[]{"�߰����", "�븥��", "������߰�", "�屹"}, new int[]{3000, 1500, 1500, 1500}, new int[] {8500})
            };
        }

        private Part2[] getWesternMenuList() {
            return new Part2[]{
                    new Part2("ġŲ", new String[]{"ġ�", "����Ƣ��", "���ҽ�", "�Ҷ��Ҷ�"}, new int[]{3000, 2500, 500, 3000}, new int[] {20000}),
                    new Part2("����", new String[]{"���İ�Ƽ", "����"}, new int[]{7000, 6500}, new int[] {25000}),
                    new Part2("�ܹ���", new String[]{"����Ƣ��", "ġŲ�ʰ�"}, new int[]{2500, 4000}, new int[] {5500})
            };
        }

    public static void main(String[] args) {
	    Part1 part1 = new Part1();
	    part1.printMenu();
	}
}

