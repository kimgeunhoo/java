package cinemaSys;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CinemaManagement {
    private Scanner scan = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> regionList = new ArrayList<>();
    private ArrayList<String> cinemaList = new ArrayList<>();

    private File cinemaFile = new File("C:\\DDGCinema_data\\영화관목록.txt");
    private File movieCinemaFile = new File("C:\\DDGCinema_data\\시스템_극장별 현재 상영 영화 목록.txt");
    private File movieFile = new File("C:\\DDGCinema_data\\시스템_현재 상영 영화 목록.txt");

    public void cinemaList() {
        loadCinemaInfo();

        String[] regions = {"서울특별시", "경기도", "인천광역시", "강원도", "대전/충청시", "대구광역시", "부산광역시"
                                     ,"경상도", "광주광역시", "전라도", "제주도"};

        for (String region : regions) {
            regionList.add(region);
        }

        while (true) {
            System.out.println("====== Cinema Management ======");
            System.out.println("Select a region:");
            printMenu(regionList);
            System.out.println("0. Go back");
            System.out.print("Input: ");

            String input = scan.nextLine();

            switch (input) {
                case "0":
                    return;
                default:
                    cinemaSelect(regionList.get(Integer.parseInt(input) - 1));
                    break;
            }
        }
    }

    private void cinemaSelect(String selRegion) {
        cinemaList.clear();
        for (String cinemaInfo : list) {
            String[] array = cinemaInfo.split("■");
            if (array[0].equals(selRegion)) {
                cinemaList.add(array[1]);
            }
        }

        while (true) {
            System.out.println("====== Select Cinema ======");
            printMenu(cinemaList);
            System.out.println("0. Go back");
            System.out.print("Input: ");
            String input = scan.nextLine();

            if (input.equals("0")) {
                break;
            } else {
                cinemaMovieList(cinemaList.get(Integer.parseInt(input) - 1));
            }
        }
    }

    private void cinemaMovieList(String cinema) {
        while (true) {
            System.out.println("====== Current Movies at " + cinema + " ======");
            printMovies(movieCinemaFile);
            System.out.println("0. Go back");
            System.out.println("1. Add Movie");
            System.out.println("2. Delete Movie");
            System.out.print("Input: ");
            String input = scan.nextLine();

            switch (input) {
                case "0":
                    return;
                case "1":
                    cinemaAddMovie();
                    return;
                case "2":
                    cinemaDeleteMovie();
                    return;
            }
        }
    }

    private void cinemaAddMovie() {
        while (true) {
            System.out.println("====== Add Movie ======");
            ArrayList<String> movieList = loadMovies(movieFile);
            ArrayList<String> addMovieList = getAddableMovies(movieList);

            System.out.println("Select a movie to add:");
            printMenu(addMovieList);
            System.out.println("0. Go back");
            System.out.print("Input: ");
            String input = scan.nextLine();

            if (input.equals("0")) {
                break;
            } else if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= addMovieList.size()) {
                addMovieToCinema(movieList, addMovieList.get(Integer.parseInt(input) - 1));
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void cinemaDeleteMovie() {
        while (true) {
            System.out.println("====== Delete Movie ======");
            ArrayList<String> movieList = loadMovies(movieCinemaFile);

            System.out.println("Select a movie to delete:");
            printMenu(movieList);
            System.out.println("0. Go back");
            System.out.print("Input: ");
            String input = scan.nextLine();

            if (input.equals("0")) {
                break;
            } else if (Integer.parseInt(input) > 0 && Integer.parseInt(input) <= movieList.size()) {
                deleteMovie(movieList, Integer.parseInt(input));
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void deleteMovie(ArrayList<String> movieList, int index) {
        movieList.remove(index - 1);
        saveMovies(movieCinemaFile, movieList);
        System.out.println("Movie deleted successfully.");
    }

    private void addMovieToCinema(ArrayList<String> movieList, String selectedMovie) {
        String cinema = cinemaList.get(Integer.parseInt(scan.nextLine()) - 1);
        movieList.add(cinema + "■" + selectedMovie);
        saveMovies(movieCinemaFile, movieList);
        System.out.println("Movie added successfully.");
    }

    private ArrayList<String> getAddableMovies(ArrayList<String> movieList) {
        ArrayList<String> addableMovies = new ArrayList<>();

        for (String movie : movieList) {
            String[] array = movie.split("■");
            if (!cinemaList.contains(array[0])) {
                addableMovies.add(array[1]);
            }
        }

        return addableMovies;
    }

    private void printMovies(File file) {
        ArrayList<String> movies = loadMovies(file);
        printMenu(movies);
    }

    private void printMenu(ArrayList<String> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, items.get(i));
        }
    }

    private ArrayList<String> loadMovies(File file) {
        ArrayList<String> movieList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                movieList.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieList;
    }

    private void saveMovies(File file, ArrayList<String> movies) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String movie : movies) {
                writer.write(movie);
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCinemaInfo() {
        list.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(cinemaFile));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
