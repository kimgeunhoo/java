package cinemaSys;

import java.util.Scanner;

public class Main {
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("====== Welcome to DDG Cinema Management System ======");
        
        while (true) {
            System.out.println("\n1. 영화 관리");
            System.out.println("2. 고객 관리");
            System.out.println("3. 매출 관리");
            System.out.println("0. 나가기");
            System.out.print("수행할 업무를 선택하세요: ");

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
                    System.out.println("시스템을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("잘못된 선택입니다.다시 선택해 주세요.");
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
    