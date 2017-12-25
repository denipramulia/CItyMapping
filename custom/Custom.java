package custom;

import java.util.Scanner;
import java.io.*;

public class Custom {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println();
        System.out.println("    --------------------- Size Your Map ---------------------");
        System.out.print("    Input your map size! (10x10 --> 10 10) ");
        int x = in.nextInt();
        int y = in.nextInt();

        System.out.println("    --------------------- End of Generate Map ---------------------");
        System.out.println();

        String[][] map = new String[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                map[i][j] = ".";
            }
        }

        Mall_Gen(map, x, y);
        River_Gen(map, x, y);

        try {
            File map_out = new File("custom/map/map.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(map_out));
            //PrintWriter writer = new PrintWriter(new FileWriter(map_out));
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {

                    writer.write(map[i][j]);

                }
                if (i < x - 1) {
                    writer.newLine();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void River_Gen(String[][] map, int x, int y) {
        Scanner in = new Scanner(System.in);

        System.out.println();
        System.out.println("    --------------------- Size and Locate Your River ---------------------");

        System.out.print("    Input your river size! (only width) ");
        int river_size = in.nextInt();
        System.out.print("    Input your river location! ");
        int river_location = (in.nextInt()) - 1;
        in.nextLine();
        System.out.print("    Which direct river you want? (horizontal / vertical) ");
        String river_direct = in.nextLine().toLowerCase();

        int current_location = river_location;
        if (river_direct.equals("horizontal")) {
            for (int size = 0; size < river_size; size++) {
                for (int i = 0; i < y; i++) {
                    
                    map[river_location][i] = "S";
                }
                if (river_location + 1 < x) {
                    river_location++;
                }
            }
        } else if (river_direct.equals("vertical")) {
            for (int size = 0; size < river_size; size++) {
                for (int i = 0; i < x; i++) {

                    map[i][river_location] = "S";
                }
                if (river_location + 1 < y) {
                    river_location++;
                }
            }
        } else {
            System.out.println("    River mapping error");
        }

        System.out.println("    --------------------- End of Generate River ---------------------");
        System.out.println();
    }

    public static void Mall_Gen(String[][] map, int x, int y) {
        Scanner in = new Scanner(System.in);

        System.out.println();
        System.out.println("    --------------------- Size and Locate Your Mall ---------------------");

        System.out.print("    Input your mall size! (10x10 --> 10 10) ");
        int x_mall_size = in.nextInt();
        int y_mall_size = in.nextInt();

        System.out.print("    Input your mall location! (height x width --> height width) ");
        int x_mall_location = in.nextInt();
        int y_mall_location = in.nextInt();
        for (int i = x_mall_location; i < x_mall_location + x_mall_size; i++) {
            if (i <= map.length) {
                for (int j = y_mall_location; j < y_mall_size + y_mall_location; j++) {
                    if (j <= map[x - 1].length) {
                        map[i - 1][j - 1] = "M";
                    }
                }
            }
        }

        System.out.println("    --------------------- End of Generate Mall ---------------------");
        System.out.println();
    }
}