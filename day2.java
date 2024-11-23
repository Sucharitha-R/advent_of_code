import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.util.Scanner; // Import the Scanner class to read text files

public class day2 {
    public static void main(String[] args) {
        System.out.println("hello world");
        try {

            int depth_pos = 0;
            int horizontal_pos = 0;
            int aim = 0;
            
            File f = new File("input.txt");
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {

                String in = sc.nextLine();
                String[] split = in.split(" ");
                int x = Integer.parseInt(split[1]);

                switch (split[0]) {
                    case "forward":
                        horizontal_pos += x;
                        depth_pos += (aim * x);
                        System.out.println("Updated horizontal pos, " + horizontal_pos);
                        break;
                    case "down":
                        // depth_pos += Integer.parseInt(split[1]);
                        aim += x;
                        System.out.println("Went down " + depth_pos);
                        break;
                    case "up":
                        aim -= x;
                        System.out.println("Went up " + depth_pos);
                        break;
                }
                               
            }
            // System.out.println(ct);
            sc.close();
            System.out.println("Final depth: " + depth_pos);
            System.out.println("Final horizontal: " + horizontal_pos);
            System.out.println("Final aim: " + aim);
            System.out.println(depth_pos * horizontal_pos);
            // System.out.println(horizontal_pos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}