package cinemaSys;

import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("====== Welcome to DDG Cinema Management System ======");
        
        while (true) {
            System.out.println("\n1. ��ȭ ����");
            System.out.println("2. �� ����");
            System.out.println("3. ���� ����");
            System.out.println("0. ������");
            System.out.print("������ ������ �����ϼ���: ");

            String choice = scan.nextLine();

            switch (choice) {
                case "1":
                    cinemaManagement();
                    break;
                case "2":
                    customerManagement();
                    break;
                case "3":
                    salesManagement();
                    break;
                case "0":
                    System.out.println("�ý����� �����մϴ�.");
                    System.exit(0);
                default:
                    System.out.println("�߸��� �����Դϴ�.�ٽ� ������ �ּ���.");
            }
        }
    }

    private static void cinemaManagement() {
        CinemaManagement cinemaManagement = new CinemaManagement();
        cinemaManagement.cinemaList();
    }

    private static void customerManagement() {
        CustomerManagement customerManagement = new CustomerManagement();
        customerManagement.customerList();
    }

    private static void salesManagement() {
        SySalesManagement salesManagement = new SySalesManagement();
        salesManagement.salesPrint();
    }
}
    