package cinemaSys;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SySalesManagement {

    private Scanner scan = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>(); // 전체 파일 리스트
    private ArrayList<String> regionList = new ArrayList<>(); // 지역 리스트 - 서울, 경기 ...
    private ArrayList<String> cinemaList = new ArrayList<>(); // 선택 지역의 상영관 리스트 - 잠실, 건대...
    private List<String[]> salesList = new ArrayList<>(); // 순위대로 정보 저장할 배열

    private File cinemaFile = new File("C:\\DDGCinema_data\\영화관목록.txt");
    private File salesFile = new File("C:\\DDGCinema_data\\시스템_극장별 매출 데이터.txt");

    public void salesPrint() {
        loadCinemaInfo();

        String[] regions = {"서울특별시", "경기도", "인천광역시", "강원도", "대전/충청시", "대구광역시", "부산광역시"
                , "경상도", "광주광역시", "전라도", "제주도"};

        regionList.addAll(Arrays.asList(regions));

        while (true) {
            System.out.println("Select a region.");
            System.out.println("==============================");

            for (int i = 0; i < regionList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, regionList.get(i));
            }

            System.out.println("==============================");
            System.out.println("0. Go back");
            System.out.println("==============================");
            System.out.print("Input: ");

            String input = scan.nextLine();

            if (input.equals("0")) {
                break;
            } else if (Integer.parseInt(input) > 0 && Integer.parseInt(input) < regionList.size()) {
                for (String region : regionList) {
                    boolean flag = false;
                    for (int i = 0; i < list.size(); i++) {
                        String[] array = list.get(i).split("■");

                        if (region.equals(regionList.get(Integer.parseInt(input) - 1))) {
                            salesOutput(array[1]);
                            flag = true;
                            break;
                        }
                    }
                    if (flag) break;
                }
            } else {
                System.out.println("Please enter a valid selection.");
            }
        }
    }

    private void salesSearch() {
        while (true) {
            System.out.println("==============================");
            System.out.println("Enter the branch name to search.");
            System.out.println("==============================");
            System.out.println("0. Go back");
            System.out.println("==============================");
            System.out.print("Input: ");
            String input = scan.nextLine();

            if (input.equals("0")) {
                break;
            }

            for (String cinema : cinemaList) {
                if (cinema.contains(input)) {
                    System.out.println("=========================================================================================");
                    System.out.println("Branch\tMovie Sales\tProduct Sales\tTotal Sales\n");

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(salesFile));
                        String line = null;

                        String[] array;
                        while ((line = reader.readLine()) != null) {
                            array = line.split("■");
                            if (array[1].equals(input)) {
                                int movieSales = Integer.parseInt(array[2]);
                                int foodSales = Integer.parseInt(array[3]);
                                int totalSales = movieSales + foodSales;
                                System.out.printf("%-7s\t%,5d\t%,5d\t%,5d\n", array[1], movieSales, foodSales, totalSales);
                            }
                        }

                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    System.out.println("=========================================================================================");
                    System.out.println("Search results for the branch.");
                    break;

                } else {
                    System.out.println("No such branch.");
                }
            }
        }
    }

    private void salesOutput(String region) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(salesFile));
            String line = null;

            while ((line = reader.readLine()) != null) {
                String[] array = line.split("■");
                salesList.add(array);
            }

            salesList = salesList.stream().sorted((n1, n2) ->
                    Integer.parseInt(n2[2]) + Integer.parseInt(n2[3]) - (Integer.parseInt(n1[2]) + Integer.parseInt(n1[3])))
                    .collect(Collectors.toList());
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        movePage();

        while (true) {
            System.out.println("==============================");
            System.out.println("0. Go back");
            System.out.println("==============================");
            System.out.print("Selection: ");
            String num = scan.nextLine();

            switch (num) {
                case "0":
                    System.out.println("Go back");
                    return;
            }
        }
    }

    private void movePage() {
        System.out.println("==============================");
        System.out.println("[Sales Rank]\t[Branch]\t\t[Movie Sales]\t[Product Sales]\t[Total Sales]");

        for (int i = 0; i < salesList.size(); i++) {
            System.out.printf("%3d\t\t%-7s\t\t%,10d\t%,10d\t%,10d\n", i + 1, salesList.get(i)[1]
                    , Integer.parseInt(salesList.get(i)[2])
                    , Integer.parseInt(salesList.get(i)[3])
                    , Integer.parseInt(salesList.get(i)[2]) + Integer.parseInt(salesList.get(i)[3]));
        }
    }

    private void loadCinemaInfo() {
        list.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(cinemaFile));

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
