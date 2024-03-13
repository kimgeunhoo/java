package cinemaSys;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class CustomerManagement {

    private Scanner scan = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>();
    private File file = new File("C:\\DDGCinema_data\\고객정보.txt");

    public void customerList() {
        loadCustomerInfo();

        int page = 1;
        movePage(page, list);

        while (true) {
            System.out.println("==============================");
            System.out.println("1. 다음 페이지");
			System.out.println("2. 이전 페이지");
			System.out.println("3. 회원 정보 검색");
			System.out.println("4. 회원 정보 삭제");
			System.out.println("5. 뒤로가기");

            System.out.println("==============================");
            System.out.print("Select: ");

            String select = scan.nextLine();

            System.out.println("==============================");

            switch (select) {
                case "1":
                    page++;
                    movePage(page, list);
                    break;
                case "2":
                    page--;
                    movePage(page, list);
                    break;
                case "3":
                    customerSearch();
                    break;
                case "4":
                    customerDelete();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해 주세요");
                    break;
            }
        }
    }

    private void customerSearch() {
        System.out.println("Select a field to search.");
        System.out.println("1. 소비자 번호");
        System.out.println("2. ID");
        System.out.println("3. 이름");
        System.out.println("4. 등급");

        System.out.println("==============================");
        System.out.print("선택: ");
        String searchInput = scan.nextLine();

        System.out.println("==============================");
        System.out.print("검색 키워드: ");
        String search = scan.nextLine();

        ArrayList<String> searchResult = new ArrayList<>();

        for (String s : list) {
            String[] line = s.split("■");

            switch (searchInput) {
                case "1":
                    if (line[0].equals(search)) {
                        searchResult.add(s);
                    }
                    break;
                case "2":
                    if (line[1].equals(search)) {
                        searchResult.add(s);
                    }
                    break;
                case "3":
                    if (line[3].contains(search)) {
                        searchResult.add(s);
                    }
                    break;
                case "4":
                    if (line[9].contains(search)) {
                        searchResult.add(s);
                    }
                    break;
            }
        }

        int page = 1;
        movePage(page, searchResult);

        while (true) {
            System.out.println("==============================");
            System.out.println("1. 다음 페이지");
            System.out.println("2. 이전 페이지");
            System.out.println("3. 삭제");
            System.out.println("4. 뒤로 가기");
            System.out.println("==============================");
            System.out.print("선택: ");
            String num = scan.nextLine();

            switch (num) {
                case "1":
                    page++;
                    movePage(page, searchResult);
                    break;
                case "2":
                    page--;
                    movePage(page, searchResult);
                    break;
                case "3":
                    customerDelete();
                    return;
                case "4":
                    return;
            }
        }
    }

    private void customerDelete() {
        System.out.println("Select the customer number to delete.");
        System.out.println("==============================");
        System.out.print("번호: ");
        String delNum = scan.nextLine();

        ArrayList<String> delList = new ArrayList<>();

        for (String s : list) {
            String[] line = s.split("■");

            if (line[0].contains(delNum)) {
                continue;
            }

            delList.add(s);
        }

        list = delList;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (String s : delList) {
                writer.write(s);
                writer.newLine();
            }

            System.out.println("삭제가 완료되었습니다.");

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean movePage(int pageNum, List<String> list) {
        if (list.size() == 0) {
            System.out.println("검색 결과가 없습니다");
            return true;
        } else {
            int maxPage = list.size() % 10 > 0 ? list.size() / 10 + 1 : list.size() / 10;

            if (pageNum <= 0) {
                pageNum = maxPage;
            } else if (pageNum > maxPage) {
                pageNum = 1;
            }

            System.out.printf("Current page is %d.\n", pageNum);
            System.out.println("==============================");
            System.out.println("[소비자 번호]\t[ID]\t[이름]\t[나이]\t[성별]\t[주소]\t\t\t\t[휴대폰 번호]\t[Email]\t\t[마일리지]\t[등급]");

            for (int i = (pageNum - 1) * 10; i < pageNum * 10; i++) {
                if (i >= list.size()) {
                    break;
                } else {
                    String[] s = list.get(i).split("■");

                    String year = s[4].substring(0, 2);
                    Calendar c = Calendar.getInstance();
                    int age = 0;

                    if (year.indexOf("0") == 0) {
                        age = c.get(Calendar.YEAR) - Integer.parseInt("20" + year) + 1;
                    } else {
                        age = c.get(Calendar.YEAR) - Integer.parseInt("19" + year) + 1;
                    }

                    int index = s[4].indexOf("-");
                    String gender = s[4].charAt(index + 1) == '1' || s[4].charAt(index + 1) == '3' ? "Male" : "Female";

                    System.out.printf("%6s\t\t%5s \t%-3s\t%3d\t%-1s\t%-15s\t%s\t%s\t%d\t\t%s\n",
                            s[0], s[1], s[3], age, gender, s[5], s[6], s[7], Integer.parseInt(s[8]), s[9]);
                }
            }
        }
        return false;
    }

    private void loadCustomerInfo() {
        list.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;

            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
