package 과제;

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
        this.price = mainPrices[0]; // 첫 번째 사이드 메뉴의 가격을 메인 메뉴 가격으로 설정
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
        if (menuName.contains("족발") || menuName.contains("보쌈") || menuName.contains("찌개") || menuName.contains("덮밥") || menuName.contains("고기구이") || menuName.contains("떡볶이")) {
            return "한식";
        } else if (menuName.contains("짜장면") || menuName.contains("짬뽕") || menuName.contains("볶음밥") || menuName.contains("마라탕")) {
            return "중식";
        } else if (menuName.contains("초밥") || menuName.contains("돈까스") || menuName.contains("라멘") || menuName.contains("우동") || menuName.contains("규동")) {
            return "일식";
        } else {
            return "양식";
        }
    }
}