import java.util.Scanner;
import custom.Custom;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class CityMapping {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        boolean start_program = true;
        boolean already_create_map = false;
        boolean mapping_check = false;

        System.out.println();
        open_file("txt/welcome.txt");
        System.out.println();

        while (start_program) {
            if (!already_create_map) {
                System.out.println("    1. Create your own map");
                System.out.println("    9. About");
                System.out.println("    0. End Program");
                System.out.println("    type 'help' if you want tutorial");
                System.out.print("    Your input : ");
                String input = in.nextLine();

                switch (input.toLowerCase()) {
                case "1":
                    Custom.main(args);
                    already_create_map = true;
                    break;
                case "9":
                    open_file("./txt/about.txt");
                    break;
                case "0":
                    start_program = exit_que();
                    break;
                case "help":
                    open_file("txt/help.txt");
                    break;
                default:
                    System.out.println("    Input Error! Please try again with correct input.");
                }
            } else if(!mapping_check) {
                System.out.println("    1. Retry build your own map");
                System.out.println("    2. See your own map");
                System.out.println("    3. Start Mapping!");
                System.out.println("    9. About");
                System.out.println("    0. End Program");
                System.out.println("    type 'help' if you want tutorial");
                System.out.print("    Your input : ");
                String input = in.nextLine();

                switch (input.toLowerCase()) {
                case "1":
                    Custom.main(args);
                    break;
                case "2":
                    open_file("./custom/map/map.txt");
                    break;
                case "3":
                    Checker.Check("./custom/map/map.txt");
                    System.out.println("    Done Mapping!");
                    mapping_check = true;
                    break;
                case "9":
                    open_file("./txt/about.txt");
                    break;
                case "0":
                    start_program = exit_que();
                    break;
                case "help":
                    open_file("txt/help.txt");
                    break;
                default:
                    System.out.println("    Input Error! Please try again with correct input.");
                }
            }
            else {
                System.out.println("    1. Retry build your own map");
                System.out.println("    2. See Result");
                System.out.println("    9. About");
                System.out.println("    0. End Program");
                System.out.println("    type 'help' if you want tutorial");
                System.out.print("    Your input : ");
                String input = in.nextLine();

                switch (input.toLowerCase()) {
                case "1":
                    Custom.main(args);
                    mapping_check = false;
                    break;
                case "2":
                    open_file("./custom/map/map_result.txt");
                    break;
                case "9":
                    open_file("./txt/about.txt");
                    break;
                case "0":
                    start_program = exit_que();
                    break;
                case "help":
                    open_file("txt/help.txt");
                    break;
                default:
                    System.out.println("    Input Error! Please try again with correct input.");
                }
            }
            System.out.println();
        }
        System.out.println("    --------------------- Thanks for using our program! ---------------------");
        in.close();
    }

    public static boolean exit_que() {
        Scanner in = new Scanner(System.in);
        System.out.print("    Are you sure want to exit the program? (Y/N) : ");
        String ans = in.nextLine().toLowerCase();
        if (ans.equals("y")) {
            in.close();
            return false;
        } else if (ans.equals("n")) {
            return true;
        } else {
            System.out.println("    Your input is wrong! Please try again with correct input.");
            in.close();
            return true;
        }
    }

    public static void open_file(String input) {
        try (BufferedReader in = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("    " + line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}