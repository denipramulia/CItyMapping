import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Checker {

    public static void Check(String pathToMapTxt) throws IOException {

        //System.out.println("done");
        FileReader reader = new FileReader(pathToMapTxt);
        BufferedReader mapReader = new BufferedReader(reader);
        StringTokenizer tokenizer = null;

        Path path = Paths.get("./" + pathToMapTxt);
        //Path outputPath = Paths.get("./"+ pathToMapTxt.replaceAll("map.txt","map2.txt"));
        long lineCount = Files.lines(path).count();

        String line = mapReader.readLine();
        //System.out.println(line);

        //System.out.println("done 1");
        String[][] map = new String[(int) lineCount][line.length()];
        ArrayList<Mall> mall = new ArrayList<>();
        ArrayList<River> river = new ArrayList<>();
        ArrayList<Park> park = new ArrayList<>();
        ArrayList<Apartemen> apartemen = new ArrayList<>();
        ArrayList<Parkir> parkir = new ArrayList<>();
        //System.out.println(lineCount);

        for (int i = 0; i < map.length; i++) {
            //line = line.replaceAll(" ", "");
            line = line.replaceAll("", " ");
            //System.out.println(line);
            tokenizer = new StringTokenizer(line, " ");
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = tokenizer.nextToken();
                if (map[i][j].equalsIgnoreCase("M")) {
                    mall.add(new Mall(i, j));
                } else if (map[i][j].equalsIgnoreCase("S")) {
                    river.add(new River(i, j));
                }
                //System.out.print(map[i][j]);
            }
            //System.out.println();
            line = mapReader.readLine();
        }
        //System.out.println();
        for (River i : river) {
            if (i.getX() - 1 > 0 && i.getX() - 2 >= 0) {
                if (i.getY() + 1 < map[0].length) {
                    if (map[i.getX() - 1][i.getY()].equalsIgnoreCase("#")
                            && map[i.getX() - 2][i.getY()].equalsIgnoreCase("#")) {
                        InsertPark(i.getX() - 2, i.getY(), park, map);
                    }
                }
            }
            if (i.getX() + 1 < map.length - 1 && i.getX() + 2 < map.length) {
                if (i.getY() + 1 < map[0].length) {
                    if (map[i.getX() + 1][i.getY()].equalsIgnoreCase("#")
                            && map[i.getX() + 2][i.getY()].equalsIgnoreCase("#")) {
                        InsertPark(i.getX() + 1, i.getY(), park, map);
                    }
                }
            }
            if (i.getY() - 1 > 0 && i.getY() - 2 >= 0) {
                if (i.getX() + 1 < map.length) {
                    if (map[i.getX()][i.getY() - 2].equalsIgnoreCase("#")
                            && map[i.getX()][i.getY() - 1].equalsIgnoreCase("#")) {
                        InsertPark(i.getX(), i.getY() - 2, park, map);
                    }
                }
            }
            if (i.getY() + 1 < map[0].length - 1 && i.getY() + 2 < map.length) {
                if (i.getX() + 1 < map.length) {
                    if (map[i.getX()][i.getY() + 1].equalsIgnoreCase("#")
                            && map[i.getX()][i.getY() + 2].equalsIgnoreCase("#")) {
                        InsertPark(i.getX(), i.getY() + 1, park, map);
                    }
                }
            }
        }

        for (Mall i : mall) {
            //System.out.println("yey");
            if (i.getX() - 4 >= 0) {
                if (i.getY() + 4 < map[0].length) {
                    boolean safe = true;
                    for (int j = 1; j <= 4; j++) {
                        for (int k = 0; k < 4; k++) {
                            //System.out.println(map[i.getX() - j][i.getY() + k]);
                            if (map[i.getX() - j][i.getY() + k].equalsIgnoreCase("#")) {
                                safe = true;
                            } else {
                                safe = false;
                                break;
                            }
                        }
                        if (!safe) {
                            break;
                        }
                    }
                    if (safe) {
                        //System.out.println("masuk1");
                        InsertApartment(i.getX() - 4, i.getY(), apartemen, map);
                    }
                }
            }
            if (i.getX() + 4 < map.length) {
                if (i.getY() + 4 < map[0].length) {
                    boolean safe = true;
                    for (int j = 1; j <= 4; j++) {
                        for (int k = 0; k < 4; k++) {
                            //System.out.println(map[i.getX() + j][i.getY() + k]);
                            if (map[i.getX() + j][i.getY() + k].equalsIgnoreCase("#")) {
                                safe = true;
                            } else {
                                safe = false;
                                break;
                            }
                        }
                        if (!safe) {
                            break;
                        }
                    }
                    if (safe) {
                        //System.out.println("masuk2");
                        InsertApartment(i.getX() + 2, i.getY(), apartemen, map);
                    }
                }
            }
            if (i.getY() - 4 >= 0) {
                if (i.getX() + 4 < map.length) {
                    boolean safe = true;
                    for (int j = 0; j < 4; j++) {
                        for (int k = 1; k <= 4; k++) {
                            //System.out.println(map[i.getX() + j][i.getY() - k]);
                            if (map[i.getX() + j][i.getY() - k].equalsIgnoreCase("#")) {
                                safe = true;
                            } else {
                                safe = false;
                                break;
                            }
                        }
                        if (!safe) {
                            break;
                        }
                    }
                    if (safe) {
                        //System.out.println("masuk3");
                        InsertApartment(i.getX(), i.getY() - 4, apartemen, map);
                    }
                }
            }
            if (i.getY() + 4 < map[0].length) {
                if (i.getX() + 1 < map.length) {
                    boolean safe = true;
                    for (int j = 0; j < 4; j++) {
                        for (int k = 1; k <= 4; k++) {
                            //System.out.println(map[i.getX() + j][i.getY() + k]);
                            if (map[i.getX() + j][i.getY() + k].equalsIgnoreCase("#")) {
                                safe = true;
                            } else {
                                safe = false;
                                break;
                            }
                        }
                        if (!safe) {
                            break;
                        }
                    }
                    if (safe) {
                        //System.out.println("masuk4");
                        InsertApartment(i.getX(), i.getY() + 2, apartemen, map);
                    }
                }
            }
        }

        for (Apartemen i : apartemen) {
            for (int x : i.getX()) {
                for (int y : i.getY()) {
                    if (x - 1 >= 0) {
                        if (map[x - 1][y].equalsIgnoreCase("#")) {
                            InsertParkingLot(x - 1, y, parkir, map);
                        }
                        if (y - 1 >= 0) {
                            if (map[x][y - 1].equalsIgnoreCase("#")) {
                                InsertParkingLot(x, y - 1, parkir, map);
                            }
                            if (map[x - 1][y - 1].equalsIgnoreCase("#")) {
                                InsertParkingLot(x - 1, y - 1, parkir, map);
                            }
                        }
                        if (y + 1 < map[0].length) {
                            if (map[x - 1][y + 1].equalsIgnoreCase("#")) {
                                InsertParkingLot(x - 1, y + 1, parkir, map);
                            }
                            if (map[x][y + 1].equalsIgnoreCase("#")) {
                                InsertParkingLot(x, y + 1, parkir, map);
                            }
                        }
                    }
                    if (x + 1 < map.length) {
                        if (map[x + 1][y].equalsIgnoreCase("#")) {
                            InsertParkingLot(x + 1, y, parkir, map);
                        }
                        if (y - 1 >= 0) {
                            if (map[x + 1][y - 1].equalsIgnoreCase("#")) {
                                InsertParkingLot(x + 1, y - 1, parkir, map);
                            }
                        }
                        if (y + 1 < map[0].length) {
                            if (map[x + 1][y + 1].equalsIgnoreCase("#")) {
                                InsertParkingLot(x + 1, y + 1, parkir, map);
                            }
                        }
                    }
                }
            }
        }

        for (Apartemen i : apartemen) {
            for (int x : i.getX()) {
                for (int y : i.getY()) {
                    if (y + 1 < map[0].length) {
                        if (x - 2 > 0 && x - 3 >= 0) {
                            if (map[x - 2][y].equalsIgnoreCase("#") && map[x - 3][y].equalsIgnoreCase("#")
                                    && map[x - 2][y + 1].equalsIgnoreCase("#")
                                    && map[x - 3][y + 1].equalsIgnoreCase("#")) {
                                InsertPark(x - 3, y, park, map);
                            }
                        }
                        if (x + 2 < map.length - 1 && x + 3 < map.length) {
                            if (map[x + 2][y].equalsIgnoreCase("#") && map[x + 3][y].equalsIgnoreCase("#")
                                    && map[x + 2][y + 1].equalsIgnoreCase("#")
                                    && map[x + 3][y + 1].equalsIgnoreCase("#")) {
                                InsertPark(x + 2, y, park, map);
                            }
                        }
                    }
                    if (x + 1 < map.length) {
                        if (y - 2 > 0 && y - 3 >= 0) {
                            if (map[x][y - 2].equalsIgnoreCase("#") && map[x][y - 3].equalsIgnoreCase("#")
                                    && map[x + 1][y - 2].equalsIgnoreCase("#")
                                    && map[x + 1][y - 3].equalsIgnoreCase("#")) {
                                InsertPark(x, y - 3, park, map);
                            }
                        }
                        if (y + 2 < map[0].length - 1 && y + 3 < map[0].length) {
                            if (map[x][y + 2].equalsIgnoreCase("#") && map[x][y + 3].equalsIgnoreCase("#")
                                    && map[x + 1][y + 2].equalsIgnoreCase("#")
                                    && map[x + 1][y + 3].equalsIgnoreCase("#")) {
                                InsertPark(x, y + 2, park, map);
                            }
                        }
                    }
                }
            }
        }

        PrintWriter writer = new PrintWriter(pathToMapTxt.replaceAll("map.txt", "map_result.txt"), "UTF-8");
        if (park.size() != 0 || apartemen.size() != 0) {
            for (int i = 0; i < map.length; i++) {
                for (int y = 0; y < map[0].length; y++) {
                    writer.print(map[i][y]);
                }
                writer.println();
            }
        } else {
            
            writer.println("Unsolvable");
        }
        writer.close();
        reader.close();
        mapReader.close();
    }

    public static void InsertPark(int x, int y, ArrayList<Park> park, String[][] map) {
        map[x][y] = "T";
        map[x + 1][y] = "T";
        map[x][y + 1] = "T";
        map[x + 1][y + 1] = "T";
        park.add(new Park(x, x + 1, y, y + 1));
    }

    public static void InsertApartment(int x, int y, ArrayList<Apartemen> apartemen, String[][] map) {
        map[x][y] = "A";
        map[x + 1][y] = "A";
        map[x + 2][y] = "A";

        map[x][y + 1] = "A";
        map[x + 1][y + 1] = "A";
        map[x + 2][y + 1] = "A";

        map[x][y + 2] = "A";
        map[x + 1][y + 2] = "A";
        map[x + 2][y + 2] = "A";

        boolean hasPark = false;

        /* if (map[x - 1][y].equalsIgnoreCase("T") || map[x][y - 1].equalsIgnoreCase("T")
                || map[x - 1][y + 1].equalsIgnoreCase("T") || map[x + 1][y - 1].equalsIgnoreCase("T")
                || map[x - 1][y + 2].equalsIgnoreCase("T") || map[x + 2][y - 1].equalsIgnoreCase("T")
                || map[x + 3][y].equalsIgnoreCase("T") || map[x][y + 3].equalsIgnoreCase("T")
                || map[x + 3][y + 1].equalsIgnoreCase("T") || map[x + 1][y + 3].equalsIgnoreCase("T")
                || map[x + 3][y + 2].equalsIgnoreCase("T") || map[x + 2][y + 3].equalsIgnoreCase("T")) {
            hasPark = true;
        } */
        if (x - 1 >= 0) {
            if (map[x - 1][y].equalsIgnoreCase("T")) {
                hasPark = true;
            } else if (y + 1 < map[0].length) {
                if (map[x - 1][y + 1].equalsIgnoreCase("T")) {
                    hasPark = true;
                }
            } else if (y + 2 < map[0].length) {
                if (map[x - 1][y + 2].equalsIgnoreCase("T")) {
                    hasPark = true;
                }
            }
        }
        if (x + 3 < map.length) {
            if (map[x + 3][y].equalsIgnoreCase("T")) {
                hasPark = true;
            } else if (y + 1 < map[0].length) {
                if (map[x + 3][y + 1].equalsIgnoreCase("T")) {
                    hasPark = true;
                }
            } else if (y + 2 < map[0].length) {
                if (map[x + 3][y + 2].equalsIgnoreCase("T")) {
                    hasPark = true;
                }
            }
        }
        if (y - 1 >= 0) {
            if (map[x][y - 1].equalsIgnoreCase("T")) {
                hasPark = true;
            } else if (x + 1 < map.length) {
                if (map[x + 1][y - 1].equalsIgnoreCase("T")) {
                    hasPark = true;
                }
            } else if (x + 2 < map.length) {
                if (map[x + 2][y - 1].equalsIgnoreCase("T")) {
                    hasPark = true;
                }
            }
        }
        if (y + 3 < map[0].length) {
            if (map[x][y + 3].equalsIgnoreCase("T")) {
                hasPark = true;
            } else if (x + 1 < map.length) {
                if (map[x + 1][y + 3].equalsIgnoreCase("T")) {
                    hasPark = true;
                }
            } else if (x + 2 < map.length) {
                if (map[x + 2][y + 3].equalsIgnoreCase("T")) {
                    hasPark = true;
                }
            }
        }

        apartemen.add(new Apartemen(x, x + 1, x + 2, y, y + 1, y + 2, hasPark));

    }

    public static void InsertParkingLot(int x, int y, ArrayList<Parkir> parkir, String[][] map) {
        map[x][y] = "P";
        parkir.add(new Parkir(x, y));
    }
}

class Mall {
    private int x;
    private int y;

    public Mall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
}

class River {
    private int x;
    private int y;

    public River(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
}

class Park {
    private int[] x;
    private int[] y;

    public Park(int x1, int x2, int y1, int y2) {
        this.x = new int[] { x1, x2 };
        this.y = new int[] { y1, y2 };
    }

    /**
     * @return the x
     */
    public int[] getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int[] getY() {
        return y;
    }
}

class Apartemen {
    private int[] x;
    private int[] y;
    private boolean hasPark;

    public Apartemen(int x1, int x2, int x3, int y1, int y2, int y3) {
        x = new int[] { x1, x2, x3 };
        y = new int[] { y1, y2, y3 };
        this.hasPark = false;
    }

    public Apartemen(int x1, int x2, int x3, int y1, int y2, int y3, boolean hasPark) {
        x = new int[] { x1, x2, x3 };
        y = new int[] { y1, y2, y3 };
        this.hasPark = hasPark;
    }

    /**
     * @return the x
     */
    public int[] getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int[] getY() {
        return y;
    }

    public boolean hasPark() {
        return hasPark;
    }

    public boolean setHasPark(boolean args) {
        hasPark = args;
        return hasPark;
    }
}

class Parkir {
    private int x;
    private int y;

    public Parkir(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
}
