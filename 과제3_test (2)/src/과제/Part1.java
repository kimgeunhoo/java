package 과제;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Part1 {
    private Part2[] menuList;
    private Part2[] mainList;
        public Part1() {
            menuList = new Part2[]{
                new Part2("족발", new String[]{"추가고기", "보쌈김치", "공기밥", "쌈채소", "쫄면"}, new int[]{9000, 3000, 1500, 3000, 5000}, new int[]{30000}),
                new Part2("보쌈", new String[]{"추가고기", "보쌈김치", "공기밥", "쌈채소", "쫄면"}, new int[]{9000, 3000, 1500, 3000, 5000}, new int[]{30000}),
                new Part2("짜장면", new String[]{"곱빼기", "탕수육1인분", "깐풍기1인분", "단무지", "군만두"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[]{7000}),
                new Part2("짬뽕", new String[]{"곱빼기", "탕수육1인분", "깐풍기1인분", "단무지", "군만두"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[]{8000}),
                new Part2("볶음밥", new String[]{"곱빼기", "탕수육1인분", "깐풍기1인분", "단무지", "군만두"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[]{8000}),
                new Part2("마라탕", new String[]{"고기추가", "단무지", "군만두", "면추가", "국물추가", "꼬지추가"}, new int[]{3000, 500, 5000, 2000, 1000, 3000}, new int[]{15000}),
                new Part2("찌개", new String[]{"토핑추가", "곱빼기", "김치"}, new int[]{2000, 1000, 500}, new int[]{8000}),
                new Part2("덮밥", new String[]{"토핑추가", "곱빼기", "김치"}, new int[]{2000, 1000, 500}, new int[]{8000}),
                new Part2("고기구이", new String[]{"쌈채소", "장국", "공기밥", "추가고기", "김치"}, new int[]{3000, 1500, 1500, 9000, 500}, new int[]{18000}),
                new Part2("떡볶이", new String[]{"튀김", "떡추가", "오뎅추가", "치즈", "라면사리"}, new int[]{3000, 1000, 1000, 2000, 2000}, new int[]{9000}),
                new Part2("초밥", new String[]{"와사비", "간장소스", "초밥추가", "새우튀김", "감자고로케", "맛살튀김"}, new int[]{300, 300, 3000, 3500, 2500, 3500}, new int[]{16000}),
                new Part2("돈까스", new String[]{"소스", "매운소스", "장국", "공기밥", "추가돈까스", "샐러드"}, new int[]{500, 500, 1500, 1500, 6000, 3000}, new int[]{8000}),
                new Part2("라멘", new String[]{"면추가", "국물추가", "차슈추가", "공기밥"}, new int[]{1500, 1000, 3000, 1500}, new int[]{8000}),
                new Part2("우동", new String[]{"면추가", "국물추가", "차슈추가", "공기밥"}, new int[]{1500, 1000, 3000, 1500}, new int[]{7500}),
                new Part2("규동", new String[]{"추가고기", "노른자", "공기밥", "장국"}, new int[]{3000, 1500, 1500, 1500}, new int[]{8500}),
                new Part2("치킨", new String[]{"치즈볼", "감자튀김", "양념소스", "소떡소떡"}, new int[]{3000, 2500, 500, 3000}, new int[]{20000}),
                new Part2("피자", new String[]{"스파게티", "핫윙"}, new int[]{7000, 6500}, new int[]{25000}),
                new Part2("햄버거", new String[]{"감자튀김", "치킨너겟"}, new int[]{2500, 4000}, new int[]{5500})
            };
            
        }
    
    

        public void printMenu() {
        	Scanner scanner = new Scanner(System.in);
            int totalPrice = 0;
            List<Part2> cart = new ArrayList<>();
        	while (true) {
	            
	            System.out.println("음식 종류를 선택하세요:");
	            System.out.println("1. 한식");
	            System.out.println("2. 중식");
	            System.out.println("3. 일식");
	            System.out.println("4. 패스트푸드");
	            System.out.println("5. 장바구니");
	            System.out.print("선택: ");
	            int cuisineChoice = scanner.nextInt();
	            scanner.nextLine();
	
	            Part2[] cuisineMenuList;
	            String cuisineName;
	            Part2 selectedMenu = null;
	            Part2 selectedMain = null;
	
	            switch (cuisineChoice) {
	                case 1:
	                    cuisineMenuList = getKoreanMenuList();
	                    cuisineName = "한식";
	                    break;
	                case 2:
	                    cuisineMenuList = getChineseMenuList();
	                    cuisineName = "중식";
	                    break;
	                case 3:
	                    cuisineMenuList = getJapaneseMenuList();
	                    cuisineName = "일식";
	                    break;
	                case 4:
	                    cuisineMenuList = getWesternMenuList();
	                    cuisineName = "패스트푸드";
	                    break;
	                case 5:
	                	if (totalPrice != 0) {
	                        System.out.println("현재 주문내역입니다.");
	                        for (Part2 item : cart) {
	                            System.out.println(item.getMenuName());
	                        }
	                        System.out.println("1. 결제하기");
	                        System.out.println("2. 메뉴로 돌아가기");
	                        System.out.print("선택 : ");
	                        int choice = scanner.nextInt();  
	                        switch (choice) {
	                        	case 1:
	                        		System.out.print("배송받을 주소를 입력하세요: ");
	    	                        String address = scanner.next();
	    	                        System.out.println("현재 구매한 음식의 총 가격은 " + totalPrice + "원 입니다.");
	    	                        System.out.print("금액을 입력하세요: ");
	    	                        int money = scanner.nextInt();
	    	                        scanner.nextLine();
	    	                        if (money >= totalPrice) {
	    	                            System.out.println("주문이 완료되었습니다.");
	    	                            System.out.println("거스름돈 : " + (money - totalPrice));
	    	                            System.out.println("배송받을 주소 : " + address);
	    	                            return;
	    	                        } else {
	    	                            System.out.println("금액이 부족합니다. 메뉴로 돌아갑니다.");
	    	                            continue;
	    	                        }
	                        	case 2:
	                        		System.out.println("메뉴로 돌아갑니다.");
	                        		continue;
	                        }	                        
	                    } else {
	                        System.out.println("장바구니가 비었습니다.");
	                        continue;
	                    }
	                default:
	                    System.out.println("잘못된 선택입니다.");
	                    return;
	            }
	
	            System.out.println(cuisineName + " 메뉴 목록:");
	            for (int i = 0; i < cuisineMenuList.length; i++) {
	                System.out.println((i + 1) + ". " + cuisineMenuList[i].getMenuName());
	            }
	
	            System.out.print("메뉴를 선택하세요: ");
	            int menuIndex = scanner.nextInt();
	            scanner.nextLine(); // 개행 문자 제거
	
	            if (menuIndex >= 1 && menuIndex <= cuisineMenuList.length) {
	            	
	                selectedMenu = cuisineMenuList[menuIndex - 1];
	                selectedMain = cuisineMenuList[menuIndex - 1];
	                System.out.println("메인 메뉴: " + selectedMenu.getMenuName());
	                System.out.println("타입: " + cuisineName);
	                System.out.println("가격: " + Arrays.toString(selectedMain.getMainPrices()) + "원");
	                
	                
	
	                String[] sides = selectedMenu.getSides();
	                int[] sidePrices = selectedMenu.getSidePrices();
	                int[] MainPrices = selectedMenu.getMainPrices();
	                
	                int sideIndex = 0;
	                totalPrice += selectedMenu.getPrice();
	                while (true) {
	                    System.out.println("사이드 메뉴 목록:");
	                    for (int i = 0; i < sides.length; i++) {
	                        System.out.println((i + 1) + ". " + sides[i]);
	                        System.out.println("   가격: " + sidePrices[i] + "원");
	                    }
	                    System.out.print("사이드 메뉴를 선택하세요 (0: 선택하지 않음): ");
	                    sideIndex = scanner.nextInt();
	                    scanner.nextLine();

	                    if (sideIndex == 0) {
	                        System.out.println("사이드 메뉴를 선택하지 않았습니다.");
	                        break;
	                    } else if (sideIndex < 1 || sideIndex > sides.length) {
	                        System.out.println("잘못된 메뉴 번호입니다.");
	                        continue; // 반복문의 처음으로 돌아감
	                    }

	                    String selectedSide = sides[sideIndex - 1];
	                    int selectedSidePrice = sidePrices[sideIndex - 1];

	                    System.out.println("선택한 사이드 메뉴: " + selectedSide);
	                    System.out.println("가격: " + selectedSidePrice + "원");

	                    totalPrice += selectedSidePrice;
	                    System.out.println("총 가격: " + totalPrice + "원");
	                   

	                }
	            } else {
	                System.out.println("잘못된 메뉴 번호입니다.");
	            }
	            
	            if (selectedMenu != null) {
	                cart.add(selectedMenu);
	                System.out.println("장바구니에 " + selectedMenu.getMenuName() + "이(가) 추가되었습니다.");
	            }	         
           
	        }
        }


        private Part2[] getKoreanMenuList() {
            return new Part2[]{
                    new Part2("족발", new String[]{"추가고기", "보쌈김치", "공기밥", "쌈채소", "쫄면"}, new int[]{9000, 3000, 1500, 3000, 5000}, new int[] {30000}),
                    new Part2("보쌈", new String[]{"추가고기", "보쌈김치", "공기밥", "쌈채소", "쫄면"}, new int[]{9000, 3000, 1500, 3000, 5000}, new int[] {30000}),
                    new Part2("찌개", new String[]{"토핑추가", "곱빼기", "김치"}, new int[]{2000, 1000, 500}, new int[] {8000}),
                    new Part2("덮밥", new String[]{"토핑추가", "곱빼기", "김치"}, new int[]{2000, 1000, 500}, new int[] {8000}),
                    new Part2("고기구이", new String[]{"쌈채소", "장국", "공기밥", "추가고기", "김치"}, new int[]{3000, 1500, 1500, 9000, 500}, new int[] {18000}),
                    new Part2("떡볶이", new String[]{"튀김", "떡추가", "오뎅추가", "치즈", "라면사리"}, new int[]{3000, 1000, 1000, 2000, 2000}, new int[] {9000})
            };
        }

        private Part2[] getChineseMenuList() {
            return new Part2[]{
                    new Part2("짜장면", new String[]{"곱빼기", "탕수육1인분", "깐풍기1인분", "단무지", "군만두"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[] {7000}),
                    new Part2("짬뽕", new String[]{"곱빼기", "탕수육1인분", "깐풍기1인분", "단무지", "군만두"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[] {8000}),
                    new Part2("볶음밥", new String[]{"곱빼기", "탕수육1인분", "깐풍기1인분", "단무지", "군만두"}, new int[]{1000, 9000, 95000, 500, 5000}, new int[] {8000}),
                    new Part2("마라탕", new String[]{"고기추가", "단무지", "군만두", "면추가", "국물추가", "꼬지추가"}, new int[]{3000, 500, 5000, 2000, 1000, 3000}, new int[] {15000})
            };
        }

        private Part2[] getJapaneseMenuList() {
            return new Part2[]{
                    new Part2("초밥", new String[]{"와사비", "간장소스", "초밥추가", "새우튀김", "감자고로케", "맛살튀김"}, new int[]{300, 300, 3000, 3500, 2500, 3500}, new int[] {16000}),
                    new Part2("돈까스", new String[]{"소스", "매운소스", "장국", "공기밥", "추가돈까스", "샐러드"}, new int[]{500, 500, 1500, 1500, 6000, 3000}, new int[] {8000}),
                    new Part2("라멘", new String[]{"면추가", "국물추가", "차슈추가", "공기밥"}, new int[]{1500, 1000, 3000, 1500}, new int[] {8000}),
                    new Part2("우동", new String[]{"면추가", "국물추가", "차슈추가", "공기밥"}, new int[]{1500, 1000, 3000, 1500}, new int[] {7500}),
                    new Part2("규동", new String[]{"추가고기", "노른자", "공기밥추가", "장국"}, new int[]{3000, 1500, 1500, 1500}, new int[] {8500})
            };
        }

        private Part2[] getWesternMenuList() {
            return new Part2[]{
                    new Part2("치킨", new String[]{"치즈볼", "감자튀김", "양념소스", "소떡소떡"}, new int[]{3000, 2500, 500, 3000}, new int[] {20000}),
                    new Part2("피자", new String[]{"스파게티", "핫윙"}, new int[]{7000, 6500}, new int[] {25000}),
                    new Part2("햄버거", new String[]{"감자튀김", "치킨너겟"}, new int[]{2500, 4000}, new int[] {5500})
            };
        }

    public static void main(String[] args) {
	    Part1 part1 = new Part1();
	    part1.printMenu();
	}
}

